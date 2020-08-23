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

    @Query(value = "select * from estabelecimento as e inner join tags_estabelecimento as t on t.fk_estabelecimento = e.id " +
            "inner join acompanhamento_estabelecimento as a on a.tags_estabelecimento_id = t.id where a.tipo_acompanhamento = ?1", nativeQuery = true)
    List<Estabelecimento> getEstabelecimentoByAcompanhado(String acompanhado);

    @Query(value = "select * from estabelecimento as e inner join tags_estabelecimento as t on t.fk_estabelecimento = e.id " +
            "inner join caracteristica_estabelecimento as c on c.tags_estabelecimento_id = t.id where c.caracteristicas= ?1", nativeQuery = true)
    List<Estabelecimento> getEstabelecimentoByCaracteristicas(String caracteristicas);

    @Query(value = "select * from estabelecimento as e inner join tags_estabelecimento as t on t.fk_estabelecimento = e.id " +
            "inner join tipo_estabelecimento as r on r.tags_estabelecimento_id = t.id where r.tipo_estabelecimento = ?1", nativeQuery = true)
    List<Estabelecimento> getEstabelecimentoByTipoEstabelecimento(String tipoEstabelecimento);

    @Query(value = "select * from estabelecimento as e inner join tags_estabelecimento as t on t.fk_estabelecimento = e.id " +
            "inner join tipo_musica_estabelecimento as m on m.tags_estabelecimento_id = t.id where m.estilo_musica = ?1", nativeQuery = true)
    List<Estabelecimento> getEstabelecimentoByEstiloMusica(String estiloMusica);


}

