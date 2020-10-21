package com.bandtec.sp4u.services;

import com.bandtec.sp4u.api.requests.PasswordRequest;
import com.bandtec.sp4u.application.responses.UserResponse;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.notifications.Response;

public interface UsuarioService {
	
	Response saveUser(Usuario user);

	Iterable<Usuario> getAllUsers();

	UserResponse getById(Long id);

	boolean validateEmail(String email);

    Response changePassword(PasswordRequest request, String token);

	Response changeEmail(String newEmail, String token);
}
