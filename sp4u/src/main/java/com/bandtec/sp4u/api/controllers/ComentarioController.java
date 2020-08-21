package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.ComentarioRequest;
import com.bandtec.sp4u.application.responses.ComentarioResponse;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.ComentarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class ComentarioController {

    ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity getComentarios(@PathVariable("id") Integer estabelecimentoId){
        ComentarioResponse response = service.getComentario(estabelecimentoId);
        if(response.isFailure())
            return badRequest().body(response);
        return ok(response);
    }

    @PostMapping
    public ResponseEntity postComentario(@RequestBody ComentarioRequest request,
                                         @RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Response response = service.postComentario(request, token);
        return ok(response);
    }
}
