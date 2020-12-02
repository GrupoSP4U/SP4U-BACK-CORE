package com.bandtec.sp4u.services.impl;

import com.bandtec.sp4u.application.responses.DetailResponse;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.interfaces.dao.EstabelecimentoRepository;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.EstabelecimentoService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    private EstabelecimentoRepository repository;

    public EstabelecimentoServiceImpl(EstabelecimentoRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public DetailResponse getDetailsPlace(Long id) {
        DetailResponse response = new DetailResponse();

        try {
            Optional<Estabelecimento> estabelecimento = repository.findById(id);

            if (estabelecimento.isPresent()) {
                response.setPlace(estabelecimento.get());
            } else {
                response.fail("Estabelecimento não encontrado!");
            }
        } catch (NullPointerException ex) {
            response.fail(ex.toString());
        }
        return response;
    }

    @Override
    public void savePlace(Estabelecimento estabelecimento) {
        repository.save(estabelecimento);
    }

    @Override
    public List<Estabelecimento> getPlaces(Caracteristicas statusDia, Acompanhamento acompanhado,
                                    List<TipoEstabelecimento> estiloRole, List<EstiloMusica> estiloMusica) {

        List<Estabelecimento> listaFiltrada = null;

        try {
            List<Estabelecimento> listaEstabelecimentoCompleta = repository.findAll();

            listaFiltrada = listaEstabelecimentoCompleta.stream().filter(
                    estabelecimento -> estabelecimento.getTagsEstabelecimento().getCaracteristicas().equals(statusDia) ||
                    estabelecimento.getTagsEstabelecimento().getTipoAcompanhamento().equals(acompanhado) ||
                    estabelecimento.getTagsEstabelecimento().getEstiloMusica().stream().anyMatch(estiloMusica::contains) ||
                    estabelecimento.getTagsEstabelecimento().getTipoEstabelecimento().stream().anyMatch(estiloRole::contains))
                    .collect(Collectors.toList());

            if (listaFiltrada.isEmpty()) {
                return listaEstabelecimentoCompleta;
            }

        } catch (Exception ex) {
            return new ArrayList<>();
        }

        return listaFiltrada;
    }

    @Override
    public Estabelecimento getPlaceByUserId(Long userId) {
        return repository.findByUsuarioId(userId);
    }

    @Override
    public Response updateEstabelecimento(Estabelecimento newEstabelecimentoInfo, Long id) {
        Response response = new Response();
        if(newEstabelecimentoInfo == null)
            return response.fail("Esbelecimento inválido!");

        Optional<Estabelecimento> estabelecimento = repository.findById(id);
        if(!estabelecimento.isPresent()) {
            response.fail("Estabelecimento não encontrado!");
            return response;
        }

        Estabelecimento estabelecimentoSalvo = estabelecimento.get();

        try{
            for(Field fn : newEstabelecimentoInfo.getClass().getDeclaredFields()){
                fn.setAccessible(true);
                if(fn.get(newEstabelecimentoInfo) != null){
                    for(Field fu : estabelecimentoSalvo.getClass().getDeclaredFields()){
                        fu.setAccessible(true);
                        if(fu.getName().equals(fn.getName())){
                            fu.set(estabelecimentoSalvo, fn.get(newEstabelecimentoInfo));
                        }
                    }
                }
            }
        }catch (Exception e){
            response.fail("Algo deu errado ao trocar os dados.");
        }

        try{
            repository.save(estabelecimentoSalvo);
        }catch (Exception e){
            response.fail("Algo deu errado ao salvar o usuário.");
        }

        return response;
    }

}
