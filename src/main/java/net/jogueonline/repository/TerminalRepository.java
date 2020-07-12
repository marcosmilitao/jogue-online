package net.jogueonline.repository;

import net.jogueonline.domain.Banca;
import net.jogueonline.domain.Terminal;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Terminal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TerminalRepository extends JpaRepository<Terminal, Long> {

    @Query("select terminal from Terminal terminal left join fetch terminal.revendedor where terminal.imei =:imei and terminal.telefoneChipe =:telefone")
    Optional<Terminal> findOneByImei(@Param("imei") String imei,@Param("telefone") Long telefone);
}
