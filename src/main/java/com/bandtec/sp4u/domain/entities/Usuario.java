package com.bandtec.sp4u.domain.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "USUARIO")
public class Usuario extends AbstractIdentity<Long> {

	@NotNull
	private String nomeCompleto;
	@NotNull
	private String telefone;
	@NotNull
	private String nomeSocial;
	@NotNull
	private Date dataNascimento;
	@NotNull
	private String genero;
	@NotNull
	private String email;
	@NotNull
	private String senha;

	@OneToOne(mappedBy = "usuario")
	private Interesse interesses;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private Set<Comentarios> comentarios = new HashSet<>();

}
