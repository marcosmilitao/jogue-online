package net.jogueonline.repository;

import net.jogueonline.domain.DiasFuncionamento;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DiasFuncionamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiasFuncionamentoRepository extends JpaRepository<DiasFuncionamento, Long> {
}
