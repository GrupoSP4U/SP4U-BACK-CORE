package com.bandtec.sp4u.domain.interfaces.dao;

import com.bandtec.sp4u.domain.entities.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
    List<Estabelecimento> findAllByUsuarioId(Long userId);
}

