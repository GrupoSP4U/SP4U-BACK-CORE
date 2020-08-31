package com.bandtec.sp4u.domain.interfaces.dao;

import com.bandtec.sp4u.domain.entities.Estabelecimento;
import com.bandtec.sp4u.domain.models.enums.Acompanhamento;
import com.bandtec.sp4u.domain.models.enums.Caracteristicas;
import com.bandtec.sp4u.domain.models.enums.EstiloMusica;
import com.bandtec.sp4u.domain.models.enums.TipoEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
    }

