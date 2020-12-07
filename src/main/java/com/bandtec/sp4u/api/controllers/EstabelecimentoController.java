package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.EstabelecimentoRequest;
import com.bandtec.sp4u.application.responses.DetailResponse;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.EstabelecimentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        return response.isFailure() ? notFound().build() : ok(response);
    }

    @GetMapping
    public ResponseEntity getPlaces(@RequestParam(required = false) List<Caracteristicas> statusDia,
                                    @RequestParam(required = false) Acompanhamento acompanhado,
                                    @RequestParam(required = false) List<TipoEstabelecimento> estiloRole,
                                    @RequestParam(required = false) List<EstiloMusica> estiloMusica) {

        List<Estabelecimento> response = service.getPlaces(statusDia, acompanhado, estiloRole, estiloMusica);

        return response.isEmpty() ? status(500).build() : ok(response);
    }

    @PostMapping
    public ResponseEntity savePlace(
            @Valid
            @RequestBody EstabelecimentoRequest estabelecimento) {
        try {
            service.savePlace(Estabelecimento.toEntity(estabelecimento));
            return status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.err.printf("Falha ao salvar o estabelecimento %s", estabelecimento.getRazaoSocial());
            System.err.println(e);
            return status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity getPlaceByUser(@PathVariable Long userId) {
        Estabelecimento estabelecimento = service.getPlaceByUserId(userId);

        return estabelecimento == null ? status(204).build() : status(200).body(estabelecimento);
    }

    @PutMapping("/update")
    public ResponseEntity updateEstabelecimento(@RequestBody Estabelecimento newEstabelecimentoInfo, Long id) throws IllegalAccessException {

        Response response = service.updateEstabelecimento(newEstabelecimentoInfo, id);
        if(response.isSuccess())
            return ok(response);
        else
            return badRequest().body(response);
    }
}
