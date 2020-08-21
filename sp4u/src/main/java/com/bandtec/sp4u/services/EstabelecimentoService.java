package com.bandtec.sp4u.services;

import java.util.List;

import com.bandtec.sp4u.application.responses.FilterResponse;
import com.bandtec.sp4u.domain.notifications.Response;

import com.bandtec.sp4u.application.responses.DetailResponse;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;

public interface EstabelecimentoService {
	
	DetailResponse getDetailsPlace(Long id);
	FilterResponse getPlaces(
			Caracteristicas statusDia,
            Acompanhamento acompanhado,
            List<TipoEstabelecimento> estiloRole,
            List<EstiloMusica> estiloMusica
            );
	
	Response savePlace(Estabelecimento estabelecimento);
	
}
