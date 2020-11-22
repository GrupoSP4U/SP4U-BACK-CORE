package com.bandtec.sp4u.api.requests;

import com.bandtec.sp4u.domain.entities.TagsEstabelecimento;
import com.bandtec.sp4u.domain.models.enums.Dias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class EstabelecimentoRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String nomeFantasia;

    @NotNull
    private String razaoSocial;

    @NotNull
    private String emailContato;

    @NotNull
    private String cnpj;

    @NotNull
    private String cep;

    @NotNull
    private String mediaPreco;

    @NotNull
    @Max(24)
    @Min(0)
    private Double horarioAbre;

    @Max(24)
    @Min(0)
    @NotNull
    private Double horarioFecha;

    @NotNull
    @Size(max = 7)
    private List<Dias> diasDeFuncionamento;

    @NotNull
    private String endereco;

    @NotNull
    private Integer numeroEndereco;

    @NotNull
    private String complemento;

    @NotNull
    private Double nota;

    @NotNull
    private String foto;

    @NotNull
    private String descricao;

    @NotNull
    private Boolean paraMaiores;

    private TagsEstabelecimento tagsEstabelecimento;

}
