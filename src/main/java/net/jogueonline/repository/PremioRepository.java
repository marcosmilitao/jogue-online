package net.jogueonline.repository;

import net.jogueonline.domain.Premio;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Premio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PremioRepository extends JpaRepository<Premio, Long> {
}
