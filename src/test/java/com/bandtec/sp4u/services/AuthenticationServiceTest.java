package com.bandtec.sp4u.services;

import com.bandtec.sp4u.api.requests.AuthenticationRequest;
import com.bandtec.sp4u.api.security.JwtTokenUtil;
import com.bandtec.sp4u.application.responses.AuthenticationResponse;
import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import com.bandtec.sp4u.services.impl.AuthenticationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.bandtec.sp4u.fakedatas.UsuarioFakeData.*;

@ContextConfiguration
@RunWith(SpringRunner.class)
public class AuthenticationServiceTest{

    @Mock
    private UserRepository repository;

    @InjectMocks
    private AuthenticationService service = new AuthenticationServiceImpl(repository);

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void authenticateWhenUserDataIsInvalid() {
        //Cenario
        AuthenticationRequest requestInvalid = new AuthenticationRequest(null,null);

        //Ação
        AuthenticationResponse response = service.authenticate(requestInvalid);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Dados invalidos", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).findByEmail(Mockito.anyString());
    }

    @Test
    public void authenticateWhenUserDataNull() {
        //Cenario
        AuthenticationRequest requestNull = null;

        //Ação
        AuthenticationResponse response = service.authenticate(requestNull);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Dados invalidos", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).findByEmail(Mockito.anyString());
    }

    @Test
    public void authenticateWhenPasswordIsWrong() {
        //Cenario
        AuthenticationRequest mockedRequest = new AuthenticationRequest(EMAIL,"senhaErrada");
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(USUARIO_VALID);

        //Ação
        AuthenticationResponse response = service.authenticate(mockedRequest);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Senha incorreta", response.getMessages().get(0));
    }

    @Test
    public void authenticateWhenReturnIsNull() {
        //Cenario
        AuthenticationRequest mockedRequest = new AuthenticationRequest(EMAIL,SENHA);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(null);

        //Ação
        AuthenticationResponse response = service.authenticate(mockedRequest);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Usuário não encontrado", response.getMessages().get(0));
    }

    @Test
    public void authenticateWhenTokenIsInvalid() {
        //Cenario
        AuthenticationRequest mockedRequest = new AuthenticationRequest(EMAIL,SENHA);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(USUARIO_VALID);
        Mockito.when(jwtTokenUtil.generateToken(Mockito.any(UserDetails.class))).thenReturn(null);

        //Ação
        AuthenticationResponse response = service.authenticate(mockedRequest);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals(null,response.getNomeSocial());
        Assert.assertEquals("Houve um problema na geração do token", response.getMessages().get(0));
    }

    @Test
    public void authenticateWhenAllDataIsValid() {
        //Cenario
        AuthenticationRequest mockedRequest = new AuthenticationRequest(EMAIL,SENHA);
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(USUARIO_VALID);
        Mockito.when(jwtTokenUtil.generateToken(Mockito.any(UserDetails.class))).thenReturn("token");

        //Ação
        AuthenticationResponse response = service.authenticate(mockedRequest);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals(NOME_SOCIAL,response.getNomeSocial());
        Assert.assertEquals("token",response.getJwttoken());
    }
}