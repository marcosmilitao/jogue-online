package net.jogueonline.repository;

import net.jogueonline.domain.Loteria;

import net.jogueonline.domain.Modalidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Loteria entity.
 */
@Repository
public interface LoteriaRepository extends JpaRepository<Loteria, Long> {

    @Query(value = "select distinct loteria from Loteria loteria left join fetch loteria.diasFuncionamentos",
        countQuery = "select count(distinct loteria) from Loteria loteria")
    Page<Loteria> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct loteria from Loteria loteria left join fetch loteria.diasFuncionamentos")
    List<Loteria> findAllWithEagerRelationships();

    @Query("select loteria from Loteria loteria left join fetch loteria.diasFuncionamentos where loteria.id =:id")
    Optional<Loteria> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select distinct loteria from Loteria loteria left join fetch loteria.bancas b where b.id =:id")
    List<Loteria> findByBanca(@Param("id") Long id);
}
