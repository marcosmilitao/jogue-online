package net.jogueonline.repository;

import net.jogueonline.domain.LimiteVenda;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LimiteVenda entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LimiteVendaRepository extends JpaRepository<LimiteVenda, Long> {
}
