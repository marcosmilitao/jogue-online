package net.jogueonline.repository;

import net.jogueonline.domain.Banca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Banca entity.
 */
@Repository
public interface BancaRepository extends JpaRepository<Banca, Long> {

    @Query(value = "select distinct banca from Banca banca left join fetch banca.modalidades left join fetch banca.loterias left join fetch banca.customUsers",
        countQuery = "select count(distinct banca) from Banca banca")
    Page<Banca> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct banca from Banca banca left join fetch banca.modalidades left join fetch banca.loterias left join fetch banca.customUsers")
    List<Banca> findAllWithEagerRelationships();

    @Query("select banca from Banca banca left join fetch banca.modalidades left join fetch banca.loterias left join fetch banca.customUsers where banca.id =:id")
    Optional<Banca> findOneWithEagerRelationships(@Param("id") Long id);

    @Query(value = "select * from Banca b\n" +
        "left join custom_user cu on cu.banca = b.nome\n" +
        "where cu.login =:login",
        nativeQuery = true)
    List<Banca> findOneByUser(@Param("login") String login);

    @Query(value = "select * from banca ba\n" +
        "left join terminal te on te.codigo_banca = ba.id\n" +
        "where te.imei =:imei and te.telefone_chipe =:telefone and te.situacao = true",
        nativeQuery = true)
    Optional<Banca> loginTerminal(@Param("imei") String imei, @Param("telefone") Long telefone);
}
