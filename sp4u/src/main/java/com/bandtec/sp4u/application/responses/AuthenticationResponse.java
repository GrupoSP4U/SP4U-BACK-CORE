package com.bandtec.sp4u.application.responses;

import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class AuthenticationResponse extends Response implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;
    private String nomeSocial;
    private String statusDia;
    private String acompanhado;
    private Collection<TipoEstabelecimento> tipoEstabelecimentos;
    private Collection<EstiloMusica> estiloMusicas;

    public AuthenticationResponse(String jwttoken) {
        super();
        this.jwttoken = jwttoken;
    }

    public AuthenticationResponse() {
        super();
    }

}