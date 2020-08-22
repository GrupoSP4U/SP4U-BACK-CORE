package com.bandtec.sp4u.fakedatas;

import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.entities.Usuario;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;

import java.util.Arrays;
import java.util.List;

public class EstabelecimentoFakeData {

    private static final String NOME = "Recanto";
    private static final String CNPJ = "89498498489";
    private static final String CEP = "01416-001";
    private static final String MEDIA_PRECO = "$";
    private static final boolean ESTA_ABERTO = true;
    private static final String ENDERECO = "R. da Consolação, 2659 - Cerqueira César, São Paulo - SP";
    private static final double NOTA = 3.0;
    private static final String FOTO = "https://lh3.googleusercontent.com/p/AF1QipPfPwOsr4RQ8EtRZpApQGtvgwl9AsrMiO5YSRQR=s1600-w1920";
    private static final String DESCRICAO = "Bar no Centro de SP";

    public static final Estabelecimento ESTABELECIMENTO_VALID = Estabelecimento.builder().nome(NOME).cnpj(CNPJ).cep(CEP).
            mediaPreco(MEDIA_PRECO).estaAberto(ESTA_ABERTO).endereco(ENDERECO).nota(NOTA).foto(FOTO).descricao(DESCRICAO).
            build();

    public static final List<TipoEstabelecimento> TIPO_ESTABELECIMENTOS = Arrays.asList(TipoEstabelecimento.BALADA,
            TipoEstabelecimento.BAR);

    public static final List<EstiloMusica> ESTILO_MUSICAS = Arrays.asList(EstiloMusica.ELETRONICA,
            EstiloMusica.HIP_HOP);

    public static final List<Estabelecimento> ESTABELECIMENTO_LIST = Arrays.asList(
            ESTABELECIMENTO_VALID,
            new Estabelecimento(
                    "Selva Club",
                    "08004177000178",
                    "01304-001",
                    "$",
                    true,
                    "R. Augusta, 501 - Consolação, São Paulo - SP",
                    3.0,
                    "",
                    "",
                    null, null, null, null,
                    Usuario.builder().build()
            ),
            new Estabelecimento(
                    "Blitz Haus",
                    "17274858000147",
                    "01305-000",
                    "$",
                    true,
                    "R. Augusta, 657 - Consolação, São Paulo - SP",
                    3.0,
                    "",
                    "",
                    null, null, null, null,
                    Usuario.builder().build()
            ),
            new Estabelecimento(
                    "La Sabrosa",
                    "19909380000100",
                    "01304-001",
                    "$",
                    true,
                    "R. Augusta, 1474 - Consolação, São Paulo - SP",
                    1.0,
                    "",
                    "",
                    null, null, null, null,
                    Usuario.builder().build()
            )
    );
}
