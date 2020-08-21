package com.bandtec.sp4u.services.impl;

import com.bandtec.sp4u.api.security.JwtTokenUtil;
import com.bandtec.sp4u.domain.entities.Interesse;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.interfaces.dao.InteresseRepository;
import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.InteresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InteresseServiceImpl implements InteresseService {

    @Autowired
    private InteresseRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Response createInteresse(EstiloMusica estiloMusica, TipoEstabelecimento tipoEstabelecimento, String token) {

        Response response = new Response();

        if(estiloMusica == null || tipoEstabelecimento == null || token == null){
            response.fail("Request nulo!");
            return response;
        }

        String email;
        Usuario usuario;

        try{
            email = jwtTokenUtil.getUsernameFromToken(token);
            usuario = userRepository.findByEmail(email);
        }catch (Exception e){
            response.fail(e.toString());
            return response;
        }

        Interesse interesse = new Interesse();

        List<EstiloMusica> estiloMusicas = Arrays.asList(estiloMusica);
        List<TipoEstabelecimento> tipoEstabelecimentos = Arrays.asList(tipoEstabelecimento);

        interesse.setEstiloMusica(estiloMusicas);
        interesse.setEstiloRole(tipoEstabelecimentos);
        interesse.setUsuario(usuario);

        try{
            repository.save(interesse);
        } catch (Exception ex){
            response.fail(ex.toString());
        }


        return response;
    }

    @Override
    public Response updateInteresse(Caracteristicas caracteristica, Acompanhamento acompanhamento, String token) {
        Response response = new Response();

        if(caracteristica == null || acompanhamento == null || token == null){
            response.fail("Request nulo!");
            return response;
        }

        String email;
        Usuario usuario;

        try{
            email = jwtTokenUtil.getUsernameFromToken(token);
            usuario = userRepository.findByEmail(email);
        }catch (Exception e){
            response.fail(e.toString());
            return response;
        }

        int result = repository.updateInteresse(caracteristica.toString(), acompanhamento.toString(), usuario.getId());

        if(result != 1){
            response.fail("Algo deu Errado!");
        }

        return response;
    }
}
