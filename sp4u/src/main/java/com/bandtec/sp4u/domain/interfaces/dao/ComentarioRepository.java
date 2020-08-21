package com.bandtec.sp4u.domain.interfaces.dao;

import com.bandtec.sp4u.domain.entities.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentarios, Long> {

    List<Comentarios> findAllByEstabelecimentoId(Long id);
}
