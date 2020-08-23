package com.bandtec.sp4u.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity(name = "COMENTARIO")
public class Comentarios extends AbstractIdentity<Long>{

    @Column(name = "COMENTARIO")
    private String comentario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FK_ESTABELECIMENTO", nullable = false)
    private Estabelecimento estabelecimento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FK_USUARIO", nullable = false)
    private Usuario usuario;

}
