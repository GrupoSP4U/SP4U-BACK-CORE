package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.api.requests.AuthenticationRequest;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InteressesControllerTest {

    @Autowired
    private InteressesController controller;
    private Class classe = InteressesController.class;

    @Test
    void interesseControllerConfiguration(){
        assertTrue(this.classe.isAnnotationPresent(RequestMapping.class));
        assertTrue(this.classe.isAnnotationPresent(RestController.class));
    }

    @Test
    void createInteresseConfiguration() throws NoSuchMethodException {

        Method interesse = classe.getDeclaredMethod("createInteresse", EstiloMusica.class,
                TipoEstabelecimento.class, String.class);

        assertTrue(interesse.isAnnotationPresent(PostMapping.class));
    }

    @Test
    void updateInteresseConfiguration() throws NoSuchMethodException {

        Method update = classe.getDeclaredMethod("updateInteresse", Caracteristicas.class,
                Acompanhamento.class, String.class);

        assertTrue(update.isAnnotationPresent(PutMapping.class));

    }
}