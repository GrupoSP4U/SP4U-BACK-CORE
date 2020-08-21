package com.bandtec.sp4u.services;

import com.bandtec.sp4u.api.requests.ComentarioRequest;
import com.bandtec.sp4u.api.security.JwtTokenUtil;
import com.bandtec.sp4u.application.responses.ComentarioResponse;
import com.bandtec.sp4u.domain.entities.Comentarios;
import com.bandtec.sp4u.domain.interfaces.dao.ComentarioRepository;
import com.bandtec.sp4u.domain.interfaces.dao.EstabelecimentoRepository;
import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.impl.ComentarioServiceImpl;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.bandtec.sp4u.fakedatas.EstabelecimentoFakeData.ESTABELECIMENTO_VALID;
import static com.bandtec.sp4u.fakedatas.UsuarioFakeData.USUARIO_VALID;

@ContextConfiguration
@RunWith(SpringRunner.class)
public class ComentariosServiceTest extends TestCase {

    @Mock
    private ComentarioRepository repository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;
    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private ComentarioService service = new ComentarioServiceImpl(repository,userRepository,estabelecimentoRepository);

    private static final List<Comentarios> VALID_LIST = Arrays.asList(Comentarios.builder()
            .comentario("Estabelecimento muito legal").usuario(USUARIO_VALID).build());

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getComentarioWhenEstabelecimentoIdIsNull(){
        //Cenario
        Integer idNull = null;

        //Ação
        ComentarioResponse response = service.getComentario(idNull);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Id nulo!", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).findAllByEstabelecimentoId(Mockito.anyLong());
    }
    @Test
    public void getComentarioWhenComentarioIsNull(){
        //Cenario
        Integer id = 5;
        Mockito.when(repository.findAllByEstabelecimentoId(Mockito.anyLong())).thenReturn(null);

        //Ação
        ComentarioResponse response = service.getComentario(id);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Erro de comunicação com o Banco de Dados", response.getMessages().get(0));

    }
    @Test
    public void getComentarioWhenComentarioNotFound(){
        //Cenario
        Integer id = 5;
        Mockito.when(repository.findAllByEstabelecimentoId(Mockito.anyLong())).thenReturn(Collections.emptyList());

        //Ação
        ComentarioResponse response = service.getComentario(id);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertTrue(response.getComentarios().isEmpty());
    }
    @Test
    public void getComentarioWhenComentarioIsFound(){
        //Cenario
        Integer id = 5;
        Mockito.when(repository.findAllByEstabelecimentoId(Mockito.anyLong())).thenReturn(VALID_LIST);

        //Ação
        ComentarioResponse response = service.getComentario(id);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertEquals("Estabelecimento muito legal",response.getComentarios().peek().getComentario());
        Assert.assertEquals("TESTE",response.getComentarios().peek().getNomeSocial());
    }

    @Test
    public void postComentarioWhenComentarioIsNull(){
        //Cenario
        String comentario = null;
        ComentarioRequest request = new ComentarioRequest(comentario, 1L);
        String token = "token";

        //Ação
        Response response = service.postComentario(request,token);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Dados incompletos!", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void postComentarioWhenComentarioIsEmpty(){
        //Cenario
        String comentario = "";
        ComentarioRequest request = new ComentarioRequest(comentario, 1L);
        String token = "token";

        //Ação
        Response response = service.postComentario(request,token);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("O Comentário não pode ser vazio!", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void postComentarioWhenTokenIsInvalid(){
        //Cenario
        String comentario = "comentario";
        ComentarioRequest request = new ComentarioRequest(comentario, 1L);
        String token = null;

        //Ação
        Response response = service.postComentario(request,token);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.Exception: Estabelecimento não encontrado.", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void postComentarioWhenUserIsNotFound(){
        //Cenario
        String comentario = "comentario";
        ComentarioRequest request = new ComentarioRequest(comentario, 1L);
        String token = "token";
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

        //Ação
        Response response = service.postComentario(request,token);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.Exception: Estabelecimento não encontrado.", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void postComentarioWhenEstabelecimentoIsNotFound(){
        //Cenario
        String comentario = "comentario";
        ComentarioRequest request = new ComentarioRequest(comentario, 1L);
        String token = "token";
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(USUARIO_VALID);
        Mockito.when(estabelecimentoRepository.findById(Mockito.anyLong())).thenReturn(null);

        //Ação
        Response response = service.postComentario(request,token);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.NullPointerException", response.getMessages().get(0));
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void postComentarioWhenSaveThrowsError(){
        //Cenario
        String comentario = "comentario";
        ComentarioRequest request = new ComentarioRequest(comentario, 1L);
        String token = "token";
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(USUARIO_VALID);
        Mockito.when(estabelecimentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ESTABELECIMENTO_VALID));
        Mockito.when(repository.save(Mockito.any())).thenThrow(new NullPointerException());

        //Ação
        Response response = service.postComentario(request,token);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.NullPointerException", response.getMessages().get(0));
    }

    @Test
    public void postComentarioWhenDataIsValid(){
        //Cenario
        String comentario = "comentario";
        ComentarioRequest request = new ComentarioRequest(comentario, 1L);
        String token = "token";
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(USUARIO_VALID);
        Mockito.when(estabelecimentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ESTABELECIMENTO_VALID));

        //Ação
        Response response = service.postComentario(request,token);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
        Assert.assertTrue(response.getMessages().isEmpty());
    }
}