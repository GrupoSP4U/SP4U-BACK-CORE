package com.bandtec.sp4u.application.responses;

import com.bandtec.sp4u.domain.models.ComentarioUsuarioMerged;
import com.bandtec.sp4u.domain.notifications.Response;
import com.bandtec.sp4u.util.PilhaObj;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ComentarioResponse extends Response {

    private PilhaObj<ComentarioUsuarioMerged> comentarios;

}
