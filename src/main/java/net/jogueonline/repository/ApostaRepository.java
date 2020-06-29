package net.jogueonline.repository;

import net.jogueonline.domain.Aposta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Aposta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {
}
