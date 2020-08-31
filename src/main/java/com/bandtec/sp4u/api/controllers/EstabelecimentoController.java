package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.application.responses.DetailResponse;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.EstabelecimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

	private EstabelecimentoService service;

	public EstabelecimentoController(EstabelecimentoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/{estabelecimentoId}")
	public ResponseEntity getDetailsPlace(@PathVariable("estabelecimentoId") Long estabelecimentoId) {

		DetailResponse response = service.getDetailsPlace(estabelecimentoId);

		if (response.isFailure())
			return notFound().build();
		return ok(response);
	}

	@GetMapping
	public ResponseEntity getPlaces(@RequestParam(required = false) Caracteristicas statusDia,
			@RequestParam(required = false) Acompanhamento acompanhado,
			@RequestParam(required = false) List<TipoEstabelecimento> estiloRole,
			@RequestParam(required = false) List<EstiloMusica> estiloMusica) {

		List<Estabelecimento> response = service.getPlaces(statusDia, acompanhado, estiloRole, estiloMusica);

		if(response.isEmpty())
			return status(500).build();
		return ok(response);

	}

	@PostMapping
	public ResponseEntity savePlace(@RequestBody Estabelecimento estabelecimento) {
		Response response = service.savePlace(estabelecimento);
		if(response.isSuccess())
			return ok(response);
		return badRequest().body(response);
	}

}
