package com.bandtec.sp4u.domain.entities;

import javax.persistence.*;

import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "INTERESSES")
public class Interesse extends AbstractIdentity<Long> {

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS_DIA")
	private Caracteristicas statusDia;

	@Enumerated(EnumType.STRING)
	@Column(name = "ACOMPANHADO")
	private Acompanhamento acompanhado;

	@ElementCollection(targetClass= TipoEstabelecimento.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name="TIPO_ESTABELECIMENTO_USUARIO")
	@Column(name = "ESTILO_ROLE")
	private Collection<TipoEstabelecimento> estiloRole;

	@ElementCollection(targetClass= EstiloMusica.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name="TIPO_MUSICA_USUARIO")
	@Column(name = "ESTILO_MUSICA")
	private Collection<EstiloMusica> estiloMusica;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "FK_USUARIO")
	private Usuario usuario;

}
