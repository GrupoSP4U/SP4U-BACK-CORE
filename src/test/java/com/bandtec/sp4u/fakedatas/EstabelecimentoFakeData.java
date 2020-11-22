package com.bandtec.sp4u.fakedatas;

import com.bandtec.sp4u.api.requests.EstabelecimentoRequest;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.entities.TagsEstabelecimento;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.models.enums.Dias;

import java.util.Arrays;
import java.util.List;

public class EstabelecimentoFakeData {

    private static final String NOME_FANTASIA = "Recanto";
    private static final String RAZAO_SOCIAL = "Recanto Bar e Restaurante";
    private static final String EMAIL_CONTATO = "recanto@gmail.com";
    private static final String CNPJ = "89498498489";
    private static final String CEP = "01416-001";
    private static final String MEDIA_PRECO = "$";
    private static final Double HORARIO_ABRE = 10.30;
    private static final Double HORARIO_FECHA = 23.00;
    private static final String ENDERECO = "R. da Consolação - Cerqueira César, São Paulo - SP";
    private static final Integer NUMERO_ENDERECO = 1233;
    private static final String COMPLEMENTO = "";
    private static final double NOTA = 3.0;
    private static final String FOTO = "https://lh3.googleusercontent.com/p/AF1QipPfPwOsr4RQ8EtRZpApQGtvgwl9AsrMiO5YSRQR=s1600-w1920";
    private static final String DESCRICAO = "Bar no Centro de SP";
    private static final boolean PARA_MAIORES = true;

    public static final List<TipoEstabelecimento> TIPO_ESTABELECIMENTOS = Arrays.asList(TipoEstabelecimento.BALADA,
            TipoEstabelecimento.BAR);

    public static final List<EstiloMusica> ESTILO_MUSICAS = Arrays.asList(EstiloMusica.ELETRONICA,
            EstiloMusica.HIP_HOP);

    public static final TagsEstabelecimento TAGS_ESTABELECIMENTO = TagsEstabelecimento.builder()
            .tipoEstabelecimento(TIPO_ESTABELECIMENTOS)
            .estiloMusica(ESTILO_MUSICAS)
            .caracteristicas(Arrays.asList(Caracteristicas.ANIMADO))
            .tipoAcompanhamento(Arrays.asList(Acompanhamento.AMIGOS)).build();

    public static final Estabelecimento ESTABELECIMENTO_VALID = Estabelecimento.builder().nomeFantasia(NOME_FANTASIA)
            .razaoSocial(RAZAO_SOCIAL).emailContato(EMAIL_CONTATO).cnpj(CNPJ).cep(CEP).mediaPreco(MEDIA_PRECO)
            .horarioAbre(HORARIO_ABRE).horarioFecha(HORARIO_FECHA).endereco(ENDERECO)
            .numeroEndereco(NUMERO_ENDERECO).complemento(COMPLEMENTO).nota(NOTA).foto(FOTO).descricao(DESCRICAO)
            .paraMaiores(PARA_MAIORES).tagsEstabelecimento(TAGS_ESTABELECIMENTO)
            .build();


    public static final List<Dias> DIAS = Arrays.asList(Dias.SEGUNDA, Dias.TERCA);

    public static final EstabelecimentoRequest ESTABELECIMENTO_REQUEST = new EstabelecimentoRequest(
            1L,
            "Selva Club",
            "Selva Club ltda",
            "selva@gmail.com",
            "08004177000178",
            "01304-001",
            "$",
            10.00,
            22.00,
            DIAS,
            "R. Augusta - Consolação, São Paulo - SP",
            501,
            "",
            3.0,
            "",
            "",
            true,
            TagsEstabelecimento.builder().build()
    );

    public static final List<Estabelecimento> ESTABELECIMENTO_LIST = Arrays.asList(
            ESTABELECIMENTO_VALID,
            new Estabelecimento(
                    "Selva Club",
                    "Selva Club ltda",
                    "selva@gmail.com",
                    "08004177000178",
                    "01304-001",
                    "$",
                    10.00,
                    22.00,
                    "R. Augusta - Consolação, São Paulo - SP",
                    501,
                    "",
                    3.0,
                    "",
                    "",
                    true,
                    null, null, null, null,
                    DIAS,
                    Usuario.builder().build()
            ),
            new Estabelecimento(
                    "Blitz Haus",
                    "Blits Haus ltda",
                    "blitz@gmail.com",
                    "17274858000147",
                    "01305-000",
                    "$",
                    10.00,
                    22.00,
                    "R. Augusta - Consolação, São Paulo - SP",
                    657,
                    "",
                    3.0,
                    "",
                    "",
                    true,
                    null, null, null, null,
                    DIAS,
                    Usuario.builder().build()
            ),
            new Estabelecimento(
                    "La Sabrosa",
                    "La Sabrosa ltda",
                    "sabrosa@gmail.com",
                    "19909380000100",
                    "01304-001",
                    "$",
                    10.00,
                    22.00,
                    "R. Augusta - Consolação, São Paulo - SP",
                    1474,
                    "",
                    1.0,
                    "",
                    "",
                    true,
                    null, null, null, null,
                    DIAS,
                    Usuario.builder().build()
            )
    );
}
