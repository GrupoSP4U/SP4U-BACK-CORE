package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.AuthenticationRequest;
import com.bandtec.sp4u.api.requests.PasswordRequest;
import com.bandtec.sp4u.domain.entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioControllerTest {

    @Autowired
    private UsuarioController controller;
    private Class classe = UsuarioController.class;

    @Test
    void usuarioControllerConfiguration(){
        assertTrue(this.classe.isAnnotationPresent(RequestMapping.class));
        assertTrue(this.classe.isAnnotationPresent(RestController.class));
    }

    @Test
    void getAllUsersConfiguration() throws NoSuchMethodException {

        Method users = classe.getDeclaredMethod("getAllUsers");

        assertTrue(users.isAnnotationPresent(GetMapping.class));
    }

    @Test
    void getByIdConfiguration() throws NoSuchMethodException {

        Method getById = classe.getDeclaredMethod("getById", Long.class);

        assertTrue(getById.isAnnotationPresent(GetMapping.class));

        String uriEsperada = "/{id}";

        assertEquals(uriEsperada, getById.getDeclaredAnnotation(GetMapping.class).value()[0]);
    }

    @Test
    void saveUserConfiguration() throws NoSuchMethodException {

        Method saveUser = classe.getDeclaredMethod("saveUser", Usuario.class);

        assertTrue(saveUser.isAnnotationPresent(PostMapping.class));

        String uriEsperada = "/register";

        assertEquals(uriEsperada, saveUser.getDeclaredAnnotation(PostMapping.class).value()[0]);
    }

    @Test
    void changePasswordConfiguration() throws NoSuchMethodException {

        Method changePassword = classe.getDeclaredMethod("changePassword", PasswordRequest.class, String.class);

        assertTrue(changePassword.isAnnotationPresent(PutMapping.class));

        String uriEsperada = "/password";

        assertEquals(uriEsperada, changePassword.getDeclaredAnnotation(PutMapping.class).value()[0]);
    }

    @Test
    void changeEmailConfiguration() throws NoSuchMethodException {

        Method changeEmail = classe.getDeclaredMethod("changeEmail", String.class, String.class);

        assertTrue(changeEmail.isAnnotationPresent(PostMapping.class));

        String uriEsperada = "/email";

        assertEquals(uriEsperada, changeEmail.getDeclaredAnnotation(PostMapping.class).value()[0]);
    }
}