package com.bandtec.sp4u.domain.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity(name = "ESTABELECIMENTO")
public class Estabelecimento extends AbstractIdentity<Long> {

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "MEDIA_PRECO")
    private String mediaPreco;

    @Column(name = "ESTA_ABERTO")
    private Boolean estaAberto;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "NOTA")
    private Double nota;

    @Column(name = "FOTO")
    private String foto;

    @Column(name = "DESCRICAO", length = 400)
    private String descricao;

    @OneToOne(mappedBy = "estabelecimento")
    private TagsEstabelecimento tagsEstabelecimento;

    @JsonIgnore
    @OneToMany(mappedBy = "estabelecimento")
    private Set<Evento> eventos;

    @JsonIgnore
    @ManyToMany(mappedBy = "estabelecimentos")
    private Set<Usuario> usuarios = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "estabelecimento")
    private Set<Comentarios> comentarios = new HashSet<>();

}
