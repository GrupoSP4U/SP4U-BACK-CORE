package com.bandtec.sp4u.services.impl;

import com.bandtec.sp4u.application.responses.DetailResponse;
import com.bandtec.sp4u.application.responses.FilterResponse;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.interfaces.dao.EstabelecimentoRepository;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.EstabelecimentoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Response savePlace(Estabelecimento estabelecimento) {
        Response response = new Response();
        if (estabelecimento != null)
            if (validateEstabelecimento(estabelecimento))
                repository.save(estabelecimento);
            else
                response.fail("Dados Incompletos!");
        else
            response.fail("Dados Inválidos!");
        return response;
    }

    private boolean validateEstabelecimento(Estabelecimento estabelecimento) {
        return estabelecimento.getNomeFantasia() != null && estabelecimento.getRazaoSocial()!= null &&
                estabelecimento.getEmailContato()!= null && estabelecimento.getHorarioAbre()!= null &&
                estabelecimento.getHorarioFecha()!= null && estabelecimento.getNumeroEndereco()!= null &&
                estabelecimento.getComplemento()!= null && estabelecimento.getParaMaiores()!= null &&
                estabelecimento.getEstaAberto() != null && estabelecimento.getCep() != null &&
                estabelecimento.getCnpj() != null && estabelecimento.getDescricao() != null &&
                estabelecimento.getEndereco() != null && estabelecimento.getMediaPreco() != null &&
                estabelecimento.getNota() != null && estabelecimento.getFoto() != null;
    }

    @Override
    public FilterResponse getPlaces(Caracteristicas statusDia, Acompanhamento acompanhado,
                                    List<TipoEstabelecimento> estiloRole, List<EstiloMusica> estiloMusica) {

        FilterResponse response = new FilterResponse();

        List<Estabelecimento> listaEstabelecimento = null;
        List<Estabelecimento> listaAuxiliar = new ArrayList<>();

        try {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        if (statusDia == null) {
                            break;
                        }
                        listaEstabelecimento = repository.getEstabelecimentoByCaracteristicas(statusDia.toString());
                        if (!listaEstabelecimento.isEmpty()) {
                            for (int j = 0; j < listaEstabelecimento.size(); j++) {
                                if (!listaAuxiliar.contains(listaEstabelecimento.get(j))) {
                                    listaAuxiliar.add(listaEstabelecimento.get(j));
                                }
                            }
                        }
                        break;
                    case 1:
                        if (acompanhado == null) {
                            break;
                        }
                        listaEstabelecimento = repository.getEstabelecimentoByAcompanhado(acompanhado.toString());
                        if (!listaEstabelecimento.isEmpty()) {
                            for (int j = 0; j < listaEstabelecimento.size(); j++) {
                                if (!listaAuxiliar.contains(listaEstabelecimento.get(j))) {
                                    listaAuxiliar.add(listaEstabelecimento.get(j));
                                }
                            }
                        }
                        break;
                    case 2:
                        if (estiloRole == null) {
                            break;
                        }
                        listaEstabelecimento = repository.getEstabelecimentoByTipoEstabelecimento(estiloRole.get(0).toString());
                        if (!listaEstabelecimento.isEmpty()) {
                            for (int j = 0; j < listaEstabelecimento.size(); j++) {
                                if (!listaAuxiliar.contains(listaEstabelecimento.get(j))) {
                                    listaAuxiliar.add(listaEstabelecimento.get(j));
                                }
                            }
                        }
                        break;
                    case 3:
                        if (estiloMusica == null) {
                            break;
                        }
                        listaEstabelecimento = repository.getEstabelecimentoByEstiloMusica(estiloMusica.get(0).toString());
                        if (!listaEstabelecimento.isEmpty()) {
                            for (int j = 0; j < listaEstabelecimento.size(); j++) {
                                if (!listaAuxiliar.contains(listaEstabelecimento.get(j))) {
                                    listaAuxiliar.add(listaEstabelecimento.get(j));
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
                response.setEstabelecimentos(listaAuxiliar);
            }

            if (response.getEstabelecimentos().isEmpty()) {
                response.setEstabelecimentos(repository.findAll());
            }

        } catch (Exception ex) {
            response.fail(ex.toString());
        }

        return response;
    }


}
