package com.bandtec.sp4u.services;

import com.bandtec.sp4u.api.requests.ComentarioRequest;
import com.bandtec.sp4u.application.responses.ComentarioResponse;
import com.bandtec.sp4u.domain.notifications.Response;

public interface ComentarioService {
    ComentarioResponse getComentario(Integer estabelecimentoId);

    Response postComentario(ComentarioRequest request, String token);
}
