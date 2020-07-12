package net.jogueonline.repository;

import net.jogueonline.domain.Revendedor;

import net.jogueonline.domain.Terminal;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Revendedor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RevendedorRepository extends JpaRepository<Revendedor, Long> {
}
