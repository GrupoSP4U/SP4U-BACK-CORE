package com.bandtec.sp4u.services;

import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;

public interface InteresseService {

    Response createInteresse(EstiloMusica estiloMusica, TipoEstabelecimento tipoEstabelecimento, String token);

    Response updateInteresse(Caracteristicas caracteristica, Acompanhamento acompanhamento, String token);
}
