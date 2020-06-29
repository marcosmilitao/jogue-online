package net.jogueonline.repository;

import net.jogueonline.domain.Modalidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Modalidade entity.
 */
@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {

    @Query(value = "select distinct modalidade from Modalidade modalidade left join fetch modalidade.premios",
        countQuery = "select count(distinct modalidade) from Modalidade modalidade")
    Page<Modalidade> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct modalidade from Modalidade modalidade left join fetch modalidade.premios")
    List<Modalidade> findAllWithEagerRelationships();

    @Query("select modalidade from Modalidade modalidade left join fetch modalidade.premios where modalidade.id =:id")
    Optional<Modalidade> findOneWithEagerRelationships(@Param("id") Long id);

    @Query(value = "SELECT * FROM modalidade mo\n" +
        "LEFT JOIN banca_modalidade bm ON mo.id = bm.modalidade_id\n" +
        "LEFT JOIN banca ba ON ba.id = bm.banca_id\n" +
        "where ba.id =:id",
        nativeQuery = true)
    List<Modalidade> findByBanca(@Param("id") Long id);

    @Query("select distinct modalidade from Modalidade modalidade left join fetch modalidade.premios left join fetch modalidade.bancas b where b.id =:id")
    List<Modalidade> findByBancaPremio(@Param("id") Long id);
}
