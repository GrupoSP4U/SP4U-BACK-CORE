package com.bandtec.sp4u.fakedatas;

import com.bandtec.sp4u.domain.entities.Interesse;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class UsuarioFakeData {

    public static final String NOME_COMPLETO = "TESTES TESTES TESTES";
    public static final String CPF = "43713814801";
    public static final String NOME_SOCIAL = "TESTE";
    public static final Date DATA_NASCIMENTO = new Date();
    public static final String GENERO = "f";
    public static final String EMAIL = "teste@gmail.com";
    public static final String SENHA = "123";

    public static final Usuario USUARIO_VALID = Usuario.builder()
            .nomeCompleto(NOME_COMPLETO)
            .cpf(CPF)
            .nomeSocial(NOME_SOCIAL)
            .dataNascimento(DATA_NASCIMENTO)
            .genero(GENERO)
            .email(EMAIL)
            .senha(SENHA)
            .interesses(getInteresses())
            .build();

    private static Interesse getInteresses(){
        Interesse interesse = new Interesse();
        interesse.setAcompanhado(Acompanhamento.AMIGOS);
        interesse.setEstiloMusica(Arrays.asList(EstiloMusica.ELETRONICA, EstiloMusica.SAMBA));
        interesse.setEstiloRole(Arrays.asList(TipoEstabelecimento.BALADA,TipoEstabelecimento.BAR));
        interesse.setStatusDia(Caracteristicas.ANIMADO);
        return interesse;
    }
}
