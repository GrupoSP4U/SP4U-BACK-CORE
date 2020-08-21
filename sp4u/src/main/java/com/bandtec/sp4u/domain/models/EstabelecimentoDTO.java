package com.bandtec.sp4u.domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstabelecimentoDTO {
    private Long id;
    private String tipoEstabelecimento;
    private String nome;
    private String cnpj;
    private String cep;
    private String mediaPreco;
    private String endereco;
    private Double nota;
}
