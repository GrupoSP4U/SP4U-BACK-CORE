package com.bandtec.sp4u.domain.entities;

import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity(name = "TAGS_ESTABELECIMENTO")
@Data
public class TagsEstabelecimento extends AbstractIdentity<Long> {

    @ElementCollection(targetClass = Caracteristicas.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "CARACTERISTICA_ESTABELECIMENTO")
    @Column(name = "CARACTERISTICAS")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private Collection<Caracteristicas> caracteristicas;

    @ElementCollection(targetClass = TipoEstabelecimento.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "TIPO_ESTABELECIMENTO")
    @Column(name = "TIPO_ESTABELECIMENTO")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private Collection<TipoEstabelecimento> tipoEstabelecimento;

    @ElementCollection(targetClass = Acompanhamento.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "ACOMPANHAMENTO_ESTABELECIMENTO")
    @Column(name = "TIPO_ACOMPANHAMENTO")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private Collection<Acompanhamento> tipoAcompanhamento;

    @ElementCollection(targetClass = EstiloMusica.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "TIPO_MUSICA_ESTABELECIMENTO")
    @Column(name = "ESTILO_MUSICA")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private Collection<EstiloMusica> estiloMusica;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "FK_ESTABELECIMENTO")
    private Estabelecimento estabelecimento;
}
