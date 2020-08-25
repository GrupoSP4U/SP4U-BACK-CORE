package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.AuthenticationRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthenticationControllerTest {

    @Autowired
    private AuthenticationController controller;
    private static Class classe = AuthenticationController.class;

    @Test
    public void authenticationControllerConfiguration(){
        assertTrue(this.classe.isAnnotationPresent(RestController.class));
    }


    @Test
    public void authenticationConfiguration() throws NoSuchMethodException {

        Method authentication = classe.getDeclaredMethod("authentication", AuthenticationRequest.class);

        assertTrue(authentication.isAnnotationPresent(PostMapping.class));

        String uriEsperada = "/authenticate";

        assertEquals(uriEsperada, authentication.getDeclaredAnnotation(PostMapping.class).value()[0]);
    }

    @Test
    public void logoutConfiguration() throws NoSuchMethodException {

        Method logout = classe.getDeclaredMethod("logout", String.class);

        assertTrue(logout.isAnnotationPresent(PostMapping.class));

        String uriEsperada = "/logoff";

        assertEquals(uriEsperada, logout.getDeclaredAnnotation(PostMapping.class).value()[0]);

    }
}