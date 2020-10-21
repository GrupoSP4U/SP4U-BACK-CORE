package com.bandtec.sp4u.services;

import com.bandtec.sp4u.api.clients.AuthClient;
import com.bandtec.sp4u.api.security.JwtTokenUtil;
import com.bandtec.sp4u.application.responses.UserResponse;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.interfaces.dao.UserRepository;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.services.impl.UsuarioServiceImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.bandtec.sp4u.fakedatas.UsuarioFakeData.*;

@ContextConfiguration
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

	@SpyBean
	private UsuarioServiceImpl service;

	@MockBean
	private UserRepository repository;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	private AuthClient client;

	@Test
	public void getAllUsers() {
		
		//cenário
		Usuario maria = new Usuario();
		Usuario pedro = new Usuario();

		List<Usuario> listaUsuario = new ArrayList<>();
		listaUsuario.add(maria);
		listaUsuario.add(pedro);
		
		//ação
		Mockito.when(repository.findAll()).thenReturn(listaUsuario);
		Iterable<Usuario> usuarios = service.getAllUsers();
		
		//verificação
		Assert.assertEquals(listaUsuario, usuarios);

	}

	@Test
	public void validadateEmailWhenExist() {

		//cenário
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

		//ação
		Boolean existe = service.validateEmail("adriana@gmail.com");

		//verificação
		Assert.assertEquals(true, existe);

	}

	@Test
	public void validadateEmailWhenNotExist() {

		//cenário
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

		//ação
		Boolean existe = service.validateEmail("antonio@gmail.com");

		//verificação
		Assert.assertEquals(false, existe);

	}

	@Test(expected = Exception.class)
	public void saveUserWhenThrowException() {

		//cenário
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenThrow(Exception.class);

		//ação
		Response response = new Response();
		response = service.saveUser(USUARIO_VALID);

		//verificação
		Assert.assertNotNull(response);
		Assert.assertTrue(response.isFailure());

	}

	@Test(expected = Test.None.class)
	public void saveUserWhenSuccess() {

		//cenario
		Mockito.doReturn(true).when(service).validateEmail(Mockito.anyString());
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(USUARIO_VALID);

		//ação
		Response response = service.saveUser(new Usuario());

		//verificação
		Assert.assertNotNull(response);
		Assert.assertTrue(response.isSuccess());

	}
	
	@Test
	public void getByIdWhenReturnUser() {

		//cenário
        Optional<Usuario> user = Optional.of(USUARIO_VALID);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(user);

		//ação
        UserResponse response = service.getById(Mockito.anyLong());

		//verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isSuccess());
		Assert.assertEquals(USUARIO_VALID, response.getUsuario());
		Assert.assertEquals(SENHA, response.getUsuario().getSenha());
		Assert.assertEquals(TELEFONE, response.getUsuario().getTelefone());
		Assert.assertEquals(DATA_NASCIMENTO, response.getUsuario().getDataNascimento());
		Assert.assertEquals(EMAIL, response.getUsuario().getEmail());
		Assert.assertEquals(NOME_COMPLETO, response.getUsuario().getNomeCompleto());
		Assert.assertEquals(NOME_SOCIAL, response.getUsuario().getNomeSocial());
		Assert.assertEquals(GENERO, response.getUsuario().getGenero());

	}
	
	@Test(expected = Test.None.class)
	public void getByIdWhenReturnEmpty() {
        //Cenario
        Optional<Usuario> emptyUsuario = Optional.empty();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(emptyUsuario);

        //Ação
        UserResponse response = service.getById(Mockito.anyLong());

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("Usuário não encontrado!", response.getMessages().get(0));
	}
	
	@Test
	public void getByIdWhenReturnNull() {
        //Cenario
        Optional<Usuario> usuarioNull = null;
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(usuarioNull);

        //Ação
        UserResponse response = service.getById(1L);

        //Verificação
        Assert.assertNotNull(response);
        Assert.assertTrue(response.isFailure());
        Assert.assertEquals("java.lang.NullPointerException", response.getMessages().get(0));
	}
}
