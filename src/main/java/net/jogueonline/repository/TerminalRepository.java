package net.jogueonline.repository;

import net.jogueonline.domain.Banca;
import net.jogueonline.domain.Terminal;

import net.jogueonline.domain.TerminalUserInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Terminal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {


}
