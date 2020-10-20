package com.bandtec.sp4u.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity(name = "COMENTARIO")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comentarios extends AbstractIdentity<Long>{

    private String comentario;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FK_ESTABELECIMENTO", nullable = false)
    private Estabelecimento estabelecimento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "FK_USUARIO", nullable = false)
    private Usuario usuario;

}
