package com.bandtec.sp4u.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bandtec.sp4u.domain.models.EstabelecimentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.interfaces.dao.EstabelecimentoRepository;
import com.bandtec.sp4u.util.GravaEstabelecimento;
import com.bandtec.sp4u.util.ListaObj;


@CrossOrigin
@RestController
@RequestMapping(path = "/archive")
public class ArquivoController {

	private EstabelecimentoRepository repository;
	private ListaObj<Estabelecimento> lista = new ListaObj<>(20);
	private GravaEstabelecimento arquivo = new GravaEstabelecimento();

	public ArquivoController(EstabelecimentoRepository repository) {
		this.repository = repository;
	}
//
//	@GetMapping("/grava")
//	public ResponseEntity criarArquivo() {
//
//		if (lista.getTamanho() == 0) {
//			System.out.println("Lista está vazia. Não há nada a gravar");
//			return ResponseEntity.noContent().build();
//		} else {
//			arquivo.gravaLista(lista);
//			lista.limpa();
//			return ResponseEntity.ok().build();
//		}
//
//	}
//
//	@GetMapping("/adiciona/{id}")
//	public ResponseEntity AdicionarArquivo(@PathVariable Long id) {
//		Optional<Estabelecimento> consultaEstabelecimento = this.repository.findById(id);
//	
//		if (consultaEstabelecimento.isPresent()) {
//			lista.adiciona(consultaEstabelecimento.get());
//			return ResponseEntity.ok().build();
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
	
	@GetMapping
	public Iterable<EstabelecimentoDTO> createList() {

		List<Estabelecimento> estabelecimento = this.repository.findAll();

		List<EstabelecimentoDTO> estabelecimentoDTOS = new ArrayList<>();

		for (Estabelecimento e : estabelecimento) {
			estabelecimentoDTOS.add(EstabelecimentoDTO.builder().nome(e.getNome()).cep(e.getCep()).cnpj(e.getCnpj()).
					endereco(e.getEndereco()).id(e.getId()).mediaPreco(e.getMediaPreco()).nota(e.getNota()).
					tipoEstabelecimento(e.getTagsEstabelecimento().getTipoEstabelecimento().toString()).build());
		}
	
		if(estabelecimentoDTOS.isEmpty()) {
			return new ArrayList<>();
		}
		return estabelecimentoDTOS;
	}
}
