package com.bandtec.sp4u.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity(name = "ESTABELECIMENTO")
public class Estabelecimento extends AbstractIdentity<Long> {

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "RAZAO_SOCIAL")
    private String razaoSocial;

    @Column(name = "EMAIL_CONTATO")
    private String emailContato;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "MEDIA_PRECO")
    private String mediaPreco;

    @Column(name = "ESTA_ABERTO")
    private Boolean estaAberto;

    @Column(name = "HORARIO_ABRE")
    private String horarioAbre;

    @Column(name = "HORARIO_FECHA")
    private String horarioFecha;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "NUMERO_ENDERECO")
    private String numeroEndereco;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "NOTA")
    private Double nota;

    @Column(name = "FOTO")
    private String foto;

    @Column(name = "DESCRICAO", length = 400)
    private String descricao;

    @Column(name = "PARA_MAIORES")
    private Boolean paraMaiores;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;
}
