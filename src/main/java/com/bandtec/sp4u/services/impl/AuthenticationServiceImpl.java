package com.bandtec.sp4u.services.impl;

import com.bandtec.sp4u.api.requests.AuthenticationRequest;
import com.bandtec.sp4u.api.security.JwtTokenUtil;
import com.bandtec.sp4u.api.security.JwtUserData;
import com.bandtec.sp4u.application.responses.AuthenticationResponse;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import com.bandtec.sp4u.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private UserRepository repository;

    public AuthenticationServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        AuthenticationResponse response = new AuthenticationResponse();

        if (validateData(request)) {
            response.fail("Dados invalidos");
            return response;
        }

        Usuario user;

        try {
            user = findUser(request.getEmail(), request.getPassword());
            if (user == null) {
                response.fail("Senha incorreta");
                return response;
            } else {
                JwtUserData.UserData.fillData(user);
            }

        } catch (Exception e) {
            response.fail("Usuário não encontrado");
            return response;
        }

        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(request.getEmail(), request.getPassword(),
                new ArrayList<>());

        final String token = jwtTokenUtil.generateToken(userDetails);

        try {
            if (token == null) throw new NullPointerException();
            response.setJwttoken(token);
        } catch (Exception e) {
            response.setStatusDia("none");
            response.setAcompanhado("none");
            response.setTipoEstabelecimentos(new ArrayList<>());
            response.setEstiloMusicas(new ArrayList<>());
            response.fail("Houve um problema na geração do token");
            return response;
        }
        try {
            response.setNomeSocial(user.getNomeSocial());
            response.setStatusDia(user.getInteresses().getStatusDia().toString());
            response.setAcompanhado(user.getInteresses().getAcompanhado().toString());
            response.setTipoEstabelecimentos(user.getInteresses().getEstiloRole());
            response.setEstiloMusicas(user.getInteresses().getEstiloMusica());
        } catch (Exception e) {
            response.setStatusDia("none");
            response.setAcompanhado("none");
            response.setTipoEstabelecimentos(new ArrayList<>());
            response.setEstiloMusicas(new ArrayList<>());
        }


        JwtUserData.UserData.clearData();

        return response;
    }

    private Usuario findUser(String email, String password) {

        Usuario result = repository.findByEmail(email);

        if (result.getSenha().equals(password)) {
            return result;
        } else {
            return null;
        }
    }

    private boolean validateData(AuthenticationRequest request) {
        return request == null || request.getEmail() == null || request.getPassword() == null;
    }
}
