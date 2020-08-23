package com.bandtec.sp4u.domain.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {

    private String nomeCompleto;

    private String nomeSocial;

    private String email;

    private String senha;

}
