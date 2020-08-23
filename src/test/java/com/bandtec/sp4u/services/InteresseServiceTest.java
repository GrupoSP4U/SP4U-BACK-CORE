package com.bandtec.sp4u.services;

import com.bandtec.sp4u.api.security.JwtTokenUtil;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.interfaces.dao.InteresseRepository;
import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.impl.InteresseServiceImpl;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@ContextConfiguration
@RunWith(SpringRunner.class)
public class InteresseServiceTest extends TestCase {

    @Mock
    private InteresseRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private InteresseService service = new InteresseServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createInteresseWhenRequestIsNull(){
        Response response = service.createInteresse(null,null,null);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Request nulo!", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void createInteresseWhenRepositoryFail(){
        Mockito.when(jwtTokenUtil.getUsernameFromToken(Mockito.anyString())).thenReturn("email");
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new Usuario());
        Mockito.when(repository.save(Mockito.any())).thenThrow(new NullPointerException());

        Response response = service.createInteresse(EstiloMusica.AXE, TipoEstabelecimento.BALADA,"token");

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.NullPointerException", response.getMessages().get(0));
    }

    @Test
    public void createInteresseWhenUserIsNotFound(){
        Mockito.when(jwtTokenUtil.getUsernameFromToken(Mockito.anyString())).thenReturn("email");
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenThrow(new NullPointerException());

        Response response = service.createInteresse(EstiloMusica.AXE, TipoEstabelecimento.BALADA,"token");

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.NullPointerException", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void createInteresseWhenInteresseIsSaved(){
        Mockito.when(jwtTokenUtil.getUsernameFromToken(Mockito.anyString())).thenReturn("email");
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new Usuario());

        Response response = service.createInteresse(EstiloMusica.AXE, TipoEstabelecimento.BALADA,"token");

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void updateInteresseWhenUserIsNotFound(){
        Mockito.when(jwtTokenUtil.getUsernameFromToken(Mockito.anyString())).thenReturn("email");
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenThrow(new NullPointerException());

        Response response = service.updateInteresse(Caracteristicas.ANIMADO, Acompanhamento.AMIGOS,"token");

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.NullPointerException", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).updateInteresse(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong());
    }

    @Test
    public void updateInteresseWhenRepositoryFail(){
        Mockito.when(jwtTokenUtil.getUsernameFromToken(Mockito.anyString())).thenReturn("email");
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new Usuario());
        Mockito.when(repository.updateInteresse(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong())).thenThrow(new NullPointerException());

        Response response = service.updateInteresse(Caracteristicas.ANIMADO, Acompanhamento.AMIGOS,"token");

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Algo deu Errado!", response.getMessages().get(0));
    }

    @Test
    public void updateInteresseWhenRequestIsNull(){

        Response response = service.updateInteresse(null,null,null);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Request nulo!", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).updateInteresse(Mockito.anyString(),Mockito.anyString(),Mockito.anyLong());
    }



}