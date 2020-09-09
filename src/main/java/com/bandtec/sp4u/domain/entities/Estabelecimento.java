package com.bandtec.sp4u.domain.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.bandtec.sp4u.api.requests.EstabelecimentoRequest;
import com.bandtec.sp4u.domain.models.enums.Dias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity()
public class Estabelecimento extends AbstractIdentity<Long> {

    private String nomeFantasia;
    private String razaoSocial;
    private String emailContato;
    private String cnpj;
    private String cep;
    private String mediaPreco;
    private Double horarioAbre;
    private Double horarioFecha;
    private String endereco;
    private Integer numeroEndereco;
    private String complemento;
    private Double nota;
    private String foto;

    @Column(length = 400)
    private String descricao;

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

    @ElementCollection(targetClass= Dias.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="DIAS_FUNCIONAMENTO")
    private Collection<Dias> dias;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;

    public static Estabelecimento toEntity(EstabelecimentoRequest dto) {
        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.nomeFantasia = dto.getNomeFantasia();
        estabelecimento.razaoSocial = dto.getRazaoSocial();
        estabelecimento.emailContato = dto.getEmailContato();
        estabelecimento.cnpj = dto.getCnpj();
        estabelecimento.cep = dto.getCep();
        estabelecimento.mediaPreco = dto.getMediaPreco();
        estabelecimento.horarioAbre = dto.getHorarioAbre();
        estabelecimento.horarioFecha = dto.getHorarioFecha();
        estabelecimento.endereco = dto.getEndereco();
        estabelecimento.numeroEndereco = dto.getNumeroEndereco();
        estabelecimento.complemento = dto.getComplemento();
        estabelecimento.nota = dto.getNota();
        estabelecimento.foto = dto.getFoto();
        estabelecimento.descricao = dto.getDescricao();
        estabelecimento.paraMaiores = dto.getParaMaiores();
        estabelecimento.dias = dto.getDiasDeFuncionamento();

        if(estabelecimento.tagsEstabelecimento != null) {
            estabelecimento.tagsEstabelecimento = dto.getTagsEstabelecimento();
        }

        return estabelecimento;
    }
}
