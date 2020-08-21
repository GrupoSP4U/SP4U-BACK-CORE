package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationControllerTest {

    @Autowired
    private AuthenticationController controller;
    private static Class classe = AuthenticationController.class;

    @Test
    void authenticationControllerConfiguration(){
        assertTrue(this.classe.isAnnotationPresent(RestController.class));
    }


    @Test
    void authenticationConfiguration() throws NoSuchMethodException {

        Method authentication = classe.getDeclaredMethod("authentication", AuthenticationRequest.class);

        assertTrue(authentication.isAnnotationPresent(PostMapping.class));

        String uriEsperada = "/authenticate";

        assertEquals(uriEsperada, authentication.getDeclaredAnnotation(PostMapping.class).value()[0]);
    }

    @Test
    void logoutConfiguration() throws NoSuchMethodException {

        Method logout = classe.getDeclaredMethod("logout", String.class);

        assertTrue(logout.isAnnotationPresent(PostMapping.class));

        String uriEsperada = "/logoff";

        assertEquals(uriEsperada, logout.getDeclaredAnnotation(PostMapping.class).value()[0]);

    }
}