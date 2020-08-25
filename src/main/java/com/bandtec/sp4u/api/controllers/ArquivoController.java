package com.bandtec.sp4u.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.interfaces.dao.EstabelecimentoRepository;
import com.bandtec.sp4u.util.ListaObj;


@CrossOrigin
@RestController
@RequestMapping(path = "/archive")
public class ArquivoController {

	private EstabelecimentoRepository repository;
	private ListaObj<Estabelecimento> lista = new ListaObj<>(20);

	public ArquivoController(EstabelecimentoRepository repository) {
		this.repository = repository;
	}

}
