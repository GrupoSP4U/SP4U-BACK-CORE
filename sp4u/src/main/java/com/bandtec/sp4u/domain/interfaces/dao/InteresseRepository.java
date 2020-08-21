package com.bandtec.sp4u.domain.interfaces.dao;

import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bandtec.sp4u.domain.entities.Interesse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InteresseRepository extends JpaRepository<Interesse, Long>{

   @Transactional
   @Modifying(clearAutomatically = true)
   @Query(value = "UPDATE interesses SET status_dia = ?1, acompanhado = ?2 where fk_usuario = ?3", nativeQuery = true)
    int updateInteresse(String caracteristicas, String acompanhamento, Long id);

    @Query(value = "select * from interesses where fk_usuario = ?1", nativeQuery = true)
    List<Interesse> getInteresse(Long id);

}
