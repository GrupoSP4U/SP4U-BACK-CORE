package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.InteresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin
@RequestMapping("/interesse")
public class InteressesController {

    @Autowired
    private InteresseService service;


    @PostMapping
    public ResponseEntity createInteresse(@RequestParam(required = true) EstiloMusica estiloMusica,
                                          @RequestParam(required = true) TipoEstabelecimento tipoEstabelecimento,
                                          @RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Response response = service.createInteresse(estiloMusica, tipoEstabelecimento, token);

        if(response.isFailure())
            return badRequest().body(response);
        return ok(response);
    }

    @PutMapping
    public ResponseEntity updateInteresse(@RequestParam(required = true) Caracteristicas caracteristica,
                                          @RequestParam(required = true) Acompanhamento acompanhamento,
                                          @RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        Response response = service.updateInteresse(caracteristica, acompanhamento, token);

        if(response.isFailure())
            return badRequest().body(response);
        return ok(response);
    }

}
