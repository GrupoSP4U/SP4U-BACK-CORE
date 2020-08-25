package com.bandtec.sp4u.api.controllers;

import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
public class EstabelecimentoControllerTest {

    @Autowired
    private EstabelecimentoController controller;
    private static Class classe = EstabelecimentoController.class;

    @Test
    public void estabelecimentoControllerConfiguration() {

        assertTrue(classe.isAnnotationPresent(RestController.class));
        assertTrue(classe.isAnnotationPresent(RequestMapping.class));

    }

    @Test
    public void getDetailsPlaceConfiguration() throws NoSuchMethodException {

        Method details = classe.getDeclaredMethod("getDetailsPlace", Long.class);

        assertTrue(details.isAnnotationPresent(GetMapping.class));

        String uriEsperada = "/{estabelecimentoId}";

        assertEquals(uriEsperada, details.getDeclaredAnnotation(GetMapping.class).value()[0]);
    }

    @Test
    public void getPlacesConfiguration() throws NoSuchMethodException {

        Method places = classe.getDeclaredMethod("getPlaces", Caracteristicas.class,
                Acompanhamento.class, List.class, List.class);

        assertTrue(places.isAnnotationPresent(GetMapping.class));
    }

    @Test
    public void savePlaceConfiguration() throws NoSuchMethodException {

        Method save = classe.getDeclaredMethod("savePlace", Estabelecimento.class);

        assertTrue(save.isAnnotationPresent(PostMapping.class));
    }
}