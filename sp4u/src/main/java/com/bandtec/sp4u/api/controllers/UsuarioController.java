package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.PasswordRequest;
import com.bandtec.sp4u.application.responses.UserResponse;
import com.bandtec.sp4u.domain.entities.Usuario;

import com.bandtec.sp4u.domain.notifications.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bandtec.sp4u.services.UsuarioService;

import static org.springframework.http.ResponseEntity.*;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private UsuarioService service;

	public UsuarioController(UsuarioService service) {
		super();
		this.service = service;
	}

	// endpoint para testes
	@GetMapping
	public Iterable<Usuario> getAllUsers() {
		return service.getAllUsers();
	}

	
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable("id") Long id) {
			
		UserResponse response = service.getById(id);

		if (response.isFailure())
			return notFound().build();
		return ok(response);
	}

	@PostMapping("/register")
	public ResponseEntity saveUser(@RequestBody Usuario novoCadastro) {
		Response response = service.saveUser(novoCadastro);
		if(response.isSuccess())
			return status(HttpStatus.CREATED).build();
		else
			return badRequest().body(response);
	}

	@PutMapping("/password")
	public ResponseEntity changePassword(@RequestBody PasswordRequest request,
										 @RequestHeader("Authorization") String token){
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		Response response = service.changePassword(request, token);
		if(response.isSuccess())
			return ok(response);
		else
			return badRequest().body(response);

	}

	@PostMapping("/email")
	public ResponseEntity changeEmail(@RequestParam(required = true) String newEmail,
										 @RequestHeader("Authorization") String token){
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		Response response = service.changeEmail(newEmail, token);
		if(response.isSuccess())
			return ok(response);
		else
			return badRequest().body(response);

	}



}
