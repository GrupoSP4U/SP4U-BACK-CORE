package com.bandtec.sp4u.domain.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.bandtec.sp4u.fakedatas.EstabelecimentoFakeData.DIAS;
import static com.bandtec.sp4u.fakedatas.EstabelecimentoFakeData.ESTABELECIMENTO_REQUEST;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EstabelecimentoTest {

    @Test
    public void toEntity() {
        Estabelecimento estabelecimento = Estabelecimento.toEntity(ESTABELECIMENTO_REQUEST);
        Assert.assertEquals(estabelecimento.getNomeFantasia(), ESTABELECIMENTO_REQUEST.getNomeFantasia());
        Assert.assertEquals(estabelecimento.getRazaoSocial(), ESTABELECIMENTO_REQUEST.getRazaoSocial());
        Assert.assertEquals(estabelecimento.getEmailContato(), ESTABELECIMENTO_REQUEST.getEmailContato());
        Assert.assertEquals(estabelecimento.getCnpj(), ESTABELECIMENTO_REQUEST.getCnpj());
        Assert.assertEquals(estabelecimento.getCep(), ESTABELECIMENTO_REQUEST.getCep());
        Assert.assertEquals(estabelecimento.getMediaPreco(), ESTABELECIMENTO_REQUEST.getMediaPreco());
        Assert.assertEquals(estabelecimento.getHorarioAbre(), ESTABELECIMENTO_REQUEST.getHorarioAbre());
        Assert.assertEquals(estabelecimento.getHorarioFecha(), ESTABELECIMENTO_REQUEST.getHorarioFecha());
        Assert.assertEquals(estabelecimento.getEndereco(), ESTABELECIMENTO_REQUEST.getEndereco());
        Assert.assertEquals(estabelecimento.getNumeroEndereco(), ESTABELECIMENTO_REQUEST.getNumeroEndereco());
        Assert.assertEquals(estabelecimento.getComplemento(), ESTABELECIMENTO_REQUEST.getComplemento());
        Assert.assertEquals(estabelecimento.getFoto(), ESTABELECIMENTO_REQUEST.getFoto());
        Assert.assertEquals(estabelecimento.getDescricao(), ESTABELECIMENTO_REQUEST.getDescricao());
        Assert.assertEquals(estabelecimento.getParaMaiores(), ESTABELECIMENTO_REQUEST.getParaMaiores());
        Assert.assertEquals(estabelecimento.getDias(), DIAS);
    }
}
