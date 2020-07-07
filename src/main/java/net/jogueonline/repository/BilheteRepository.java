package net.jogueonline.repository;

import net.jogueonline.domain.Bilhete;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bilhete entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BilheteRepository extends JpaRepository<Bilhete, Long> {
}
