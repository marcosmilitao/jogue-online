package net.jogueonline.repository;

import net.jogueonline.domain.Revendedor;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Revendedor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RevendedorRepository extends JpaRepository<Revendedor, Long> {
}
