package com.bandtec.sp4u.services;

import com.bandtec.sp4u.application.responses.DetailResponse;
import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.bandtec.sp4u.domain.notifications.Response;

import java.util.List;

public interface EstabelecimentoService {
	
	DetailResponse getDetailsPlace(Long id);
	List<Estabelecimento> getPlaces(
			Caracteristicas statusDia,
            Acompanhamento acompanhado,
            List<TipoEstabelecimento> estiloRole,
            List<EstiloMusica> estiloMusica
            );
	
	void savePlace(Estabelecimento estabelecimento);

	List<Estabelecimento> getPlaceByUserId(Long userId);

    Response updateEstabelecimento(Estabelecimento newEstabelecimentoInfo, Long id);
}
