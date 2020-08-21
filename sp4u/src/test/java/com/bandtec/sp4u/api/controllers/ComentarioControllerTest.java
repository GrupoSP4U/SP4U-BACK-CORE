package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.ComentarioRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ComentarioControllerTest {

    @Autowired
    private ComentarioController controller;
    private static Class classe = ComentarioController.class;

    @Test
    void comentarioControllerConfiguration(){
       // Class classeComentario = classe.getDeclaringClass();
        assertTrue(classe.isAnnotationPresent(RestController.class));
        assertTrue(classe.isAnnotationPresent(RequestMapping.class));
        // String uriEsperada = "/comment";
      //   assertEquals(uriEsperada, classeComentario.getDeclaredAnnotation(RequestMapping.class));
    }

    @Test
    void getComentariosConfiguration() throws NoSuchMethodException {

        Method getComentarios = classe.getDeclaredMethod("getComentarios", Integer.class);

        assertTrue(getComentarios.isAnnotationPresent(GetMapping.class));

        String uriEsperada = "/{id}";

        assertEquals(uriEsperada, getComentarios.getDeclaredAnnotation(GetMapping.class).value()[0]);
    }

    @Test
    void postComentarioConfiguration() throws NoSuchMethodException {

        Method postComentario = classe.getDeclaredMethod("postComentario", ComentarioRequest.class,String.class);

        assertTrue(postComentario.isAnnotationPresent(PostMapping.class));

    }
}