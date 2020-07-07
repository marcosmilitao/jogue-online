package net.jogueonline.repository;

import net.jogueonline.domain.Modalidade;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Modalidade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {
    @Query("select distinct modalidade from Modalidade modalidade left join fetch modalidade.bancas b where b.id =:id")
    List<Modalidade> findByBancaPremio(@Param("id") Long id);
}
