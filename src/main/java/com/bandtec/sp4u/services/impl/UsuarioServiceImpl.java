package com.bandtec.sp4u.services.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import com.bandtec.sp4u.api.requests.PasswordRequest;
import com.bandtec.sp4u.api.security.JwtTokenUtil;
import com.bandtec.sp4u.domain.models.UsuarioDTO;
import com.bandtec.sp4u.domain.notifications.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bandtec.sp4u.application.responses.UserResponse;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import com.bandtec.sp4u.services.UsuarioService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UserRepository repository;


    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public UsuarioServiceImpl(UserRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Response saveUser(Usuario user) {
        Response response = new Response();
        if(validateEmail(user.getEmail())){
            response.fail("Email já cadastrado!");
            return response;
        }
        try{
            repository.save(user);
            UsuarioDTO usuarioDTO = UsuarioDTO.builder().nomeCompleto(user.getNomeCompleto()).email(user.getEmail()).
                    nomeSocial(user.getNomeSocial()).senha(user.getSenha()).build();
        } catch (Exception e){
            response.fail(e.toString());
        }
        return response;
    }

    @Override
    public Iterable<Usuario> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserResponse getById(Long id) {
    	
    	UserResponse response = new UserResponse();
    		
    	try {
    		Optional<Usuario> usuario = repository.findById(id);
    		
    		if(usuario.isPresent()) {
    			response.setUsuario(usuario.get());
    		}else {
    			response.fail("Usuário não encontrado!");
    		}
    		
		} catch (NullPointerException e) {
			response.fail(e.toString());
		}
    	
       return response;
    }

    @Override
    public boolean validateEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Transactional
    @Override
    public Response changePassword(PasswordRequest request, String token) {
        Response response = new Response();
        if(request.getNewPassword() == null || request.getNewPassword().equals("") ||
                request.getOldPassword() == null || request.getOldPassword().equals("")) {
            response.fail("Por favor preencha os campos!");
        }

        String email = jwtTokenUtil.getUsernameFromToken(token);
        List<Usuario> usuario = repository.getUsuarioByPassword(email, request.getOldPassword());
        if(usuario.size() != 1) {
            response.fail("Senha incorreta!");
            return response;
        }

        if(request.getNewPassword().equals(request.getOldPassword())){
            response.fail("Nova senha não pode ser igual a anterior!");
            return response;
        }

        int update = repository.updatePassword(request.getNewPassword(), email);
        if(update != 1){
            response.fail("Algo deu errado!");
        }

        return response;
    }

    @Transactional
    @Override
    public Response changeEmail(String newEmail, String token) {
        Response response = new Response();
        if(newEmail == null || newEmail.equals("")) {
            response.fail("Por favor preencha os campos!");
            return response;
        }

        String email = jwtTokenUtil.getUsernameFromToken(token);
        Usuario usuario = repository.findByEmail(email);
        if(usuario == null) {
            response.fail("Email incorreta!");
            return response;
        }

        if(newEmail.equals(email)){
            response.fail("Novo email não pode ser igual ao anterior!");
            return response;
        }

        int update = repository.updateEmail(newEmail, usuario.getId());
        if(update != 1){
            response.fail("Algo deu errado!");
        }

        return response;
    }

    @Override
    public Response updateUser(Usuario newUserInfo, String token) throws IllegalAccessException {
        Response response = new Response();
        if(newUserInfo == null)
            return response.fail("Usuário inválido!");

        String email = jwtTokenUtil.getUsernameFromToken(token);
        Usuario usuario = repository.findByEmail(email);
        if(usuario == null) {
            response.fail("Email incorreto!");
            return response;
        }

        try{
            for(Field fn : newUserInfo.getClass().getDeclaredFields()){
                fn.setAccessible(true);
                if(fn.get(newUserInfo) != null){
                    for(Field fu : usuario.getClass().getDeclaredFields()){
                        fu.setAccessible(true);
                        if(fu.getName().equals(fn.getName())){
                            fu.set(usuario, fn.get(newUserInfo));
                        }
                    }
                }
            }
        }catch (Exception e){
            response.fail("Algo deu errado ao trocar os dados.");
        }

        try{
            repository.save(usuario);
        }catch (Exception e){
            response.fail("Algo deu errado ao salvar o usuário.");
        }

        return response;
    }

}
