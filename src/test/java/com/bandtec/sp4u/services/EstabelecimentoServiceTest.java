package com.bandtec.sp4u.services;

import com.bandtec.sp4u.application.responses.DetailResponse;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.interfaces.dao.EstabelecimentoRepository;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.impl.EstabelecimentoServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.QueryTimeoutException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.bandtec.sp4u.fakedatas.EstabelecimentoFakeData.*;

@ContextConfiguration
@RunWith(SpringRunner.class)
public class EstabelecimentoServiceTest {

    private EstabelecimentoService service;

    @MockBean
    private EstabelecimentoRepository repository;

    @Before
    public void setUp() {
        service = new EstabelecimentoServiceImpl(repository);
    }

    @Test
    public void getDetailsPlaceWhenOptionalIsEmpty() {
        //Cenario
        Optional<Estabelecimento> emptyEstabelecimento = Optional.empty();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(emptyEstabelecimento);

        //Ação
        DetailResponse response = service.getDetailsPlace(1L);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Estabelecimento não encontrado!", response.getMessages().get(0));
    }

    @Test
    public void getDetailsPlaceWhenOptionalIsNull() {
        //Cenario
        Optional<Estabelecimento> estabelecimentoNull = null;
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(estabelecimentoNull);

        //Ação
        DetailResponse response = service.getDetailsPlace(1L);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.NullPointerException", response.getMessages().get(0));
    }

    @Test
    public void getDetailsPlaceWhenDataIsValid(){
        //Cenario
    Optional<Estabelecimento> estabelecimentoValid = Optional.of(ESTABELECIMENTO_VALID);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(estabelecimentoValid);

        //Ação
        DetailResponse response = service.getDetailsPlace(1L);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(ESTABELECIMENTO_VALID, response.getPlace());
    }

    @Test
    public void getPlacesWhenEstabelecimentosIsNull(){
        //Cenario
        Mockito.when(repository.findAll()).thenReturn(null);

        //Ação
        List<Estabelecimento> response = service.getPlaces(Caracteristicas.ANIMADO, Acompanhamento.AMIGOS,
                TIPO_ESTABELECIMENTOS, ESTILO_MUSICAS);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void getPlacesWhenRepositoryThrowsException(){
        //Cenario
        Mockito.when(repository.findAll()).thenThrow(new QueryTimeoutException());

        //Ação
        List<Estabelecimento> response = service.getPlaces(Caracteristicas.ANIMADO, Acompanhamento.AMIGOS,
                TIPO_ESTABELECIMENTOS, ESTILO_MUSICAS);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void getPlacesWhenFiltersAreValid(){
        //Cenario
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(ESTABELECIMENTO_VALID));

        //Ação
        List<Estabelecimento> response = service.getPlaces(Caracteristicas.ANIMADO, Acompanhamento.AMIGOS,
                TIPO_ESTABELECIMENTOS, ESTILO_MUSICAS);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertFalse(response.isEmpty());
        Assert.assertEquals(1,response.size());
        Assert.assertEquals("Recanto",response.get(0).getNomeFantasia());
    }

    @Test
    public void savePlaceWhenEstabelecimentoIsNull(){
        //Ação
        Response response = service.savePlace(null);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Dados Inválidos!", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(null);
    }

    @Test
    public void savePlaceWhenEstabelecimentoIsNotValid(){
        //Cenario
        Estabelecimento estabelecimento = new Estabelecimento();

        //Ação
        Response response = service.savePlace(estabelecimento);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Dados Incompletos!", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(null);
    }

    @Test
    public void savePlaceWhenEstabelecimentoIsValid(){
        //Ação
        Response response = service.savePlace(ESTABELECIMENTO_VALID);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
    }

}
