package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.AuthenticationRequest;
import com.bandtec.sp4u.api.security.BlackList;
import com.bandtec.sp4u.application.responses.AuthenticationResponse;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.*;


@RestController
@CrossOrigin
public class AuthenticationController {

    private AuthenticationService service;

    private BlackList blackList;

    public AuthenticationController(AuthenticationService service) {
        super();
        this.service = service;
        this.blackList = new BlackList();
    }

    @PostMapping("/authenticate")
    public ResponseEntity authentication(@RequestBody AuthenticationRequest request){

        AuthenticationResponse response = service.authenticate(request);
        if (response.isSuccess())
            return status(HttpStatus.OK).body(response);
        else if(response.getMessages().get(0).equals("Senha incorreta") || response.getMessages().get(0).equals("Dados inválidos"))
            return status(HttpStatus.BAD_REQUEST).body(response);
        else
            return status(HttpStatus.NOT_FOUND).body(response);
    }

    @CrossOrigin
    @PostMapping("/logoff")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        blackList.addToList(token);
        Response response = new Response();
        return ok(response);
    }
}
