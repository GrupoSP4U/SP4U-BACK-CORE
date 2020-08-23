package com.bandtec.sp4u.services.impl;

import com.bandtec.sp4u.api.requests.ComentarioRequest;
import com.bandtec.sp4u.api.security.JwtTokenUtil;
import com.bandtec.sp4u.application.responses.ComentarioResponse;
import com.bandtec.sp4u.domain.entities.Comentarios;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.interfaces.dao.ComentarioRepository;
import com.bandtec.sp4u.domain.interfaces.dao.EstabelecimentoRepository;
import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import com.bandtec.sp4u.domain.models.ComentarioUsuarioMerged;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.ComentarioService;
import com.bandtec.sp4u.util.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    ComentarioRepository repository;
    UserRepository userRepository;
    EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;


    public ComentarioServiceImpl(ComentarioRepository repository,
                                 UserRepository userRepository,
                                 EstabelecimentoRepository estabelecimentoRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @Override
    public ComentarioResponse getComentario(Integer estabelecimentoId) {
        ComentarioResponse response = new ComentarioResponse();

        if (estabelecimentoId == null) {
            response.fail("Id nulo!");
            return response;
        }
        try {


            List<Comentarios> list = repository.findAllByEstabelecimentoId(estabelecimentoId.longValue());

            PilhaObj<ComentarioUsuarioMerged> mergedList = new PilhaObj<>(list.size());
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    mergedList.push(new ComentarioUsuarioMerged(
                            list.get(i).getComentario(),
                            list.get(i).getUsuario().getNomeSocial()
                    ));
                }
            }

            response.setComentarios(mergedList);
        } catch (Exception ex) {
            response.fail("Erro de comunicação com o Banco de Dados");
        }


        return response;
    }

    @Override
    public Response postComentario(ComentarioRequest request, String token) {
        Response response = new Response();
        if (request == null || request.getComentario() == null || request.getPlaceId() == null) {
            response.fail("Dados incompletos!");
            return response;
        }
        if (request.getComentario().isEmpty()) {
            response.fail("O Comentário não pode ser vazio!");
            return response;
        }
        try {
            Comentarios comentarios = createComentario(request, token);
            repository.save(comentarios);
        } catch (Exception ex) {
            response.fail(ex.toString());
        }
        return response;
    }

    private Comentarios createComentario(ComentarioRequest request, String token) throws Exception {
        Comentarios comentarios = new Comentarios();

        try {
            String email = jwtTokenUtil.getUsernameFromToken(token);
            comentarios.setUsuario(userRepository.findByEmail(email));
            Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(request.getPlaceId());
            if(estabelecimento.isPresent())
                comentarios.setEstabelecimento(estabelecimento.get());
            else
                throw new Exception("Estabelecimento não encontrado.");
            comentarios.setComentario(request.getComentario());
        } catch (Exception ex) {
            throw ex;
        }

        return comentarios;
    }
}
