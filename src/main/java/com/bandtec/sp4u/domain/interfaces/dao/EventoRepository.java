package com.bandtec.sp4u.domain.interfaces.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bandtec.sp4u.domain.entities.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{

}
