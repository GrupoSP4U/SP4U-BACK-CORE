package com.bandtec.sp4u.domain.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "EVENTO")
public class Evento extends AbstractIdentity<Long> {

    @Column(name = "NOME")
    private String nome;

    @Column(name = "HORARIO")
    @Temporal(TemporalType.TIME)
    private Date horario;

    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "TEMA")
    private String tema;

    @Column(name = "PRECO")
    private String preco;

    @Column(name = "ESTILO_MUSICA")
    private String estiloMusica;

    @ManyToOne
    @JoinColumn(name = "FK_ESTABELECIMENTO", nullable = false)
    private Estabelecimento estabelecimento;
}
