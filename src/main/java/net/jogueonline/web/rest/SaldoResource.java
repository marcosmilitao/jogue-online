package net.jogueonline.web.rest;

import net.jogueonline.domain.Saldo;
import net.jogueonline.repository.SaldoRepository;
import net.jogueonline.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link net.jogueonline.domain.Saldo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SaldoResource {

    private final Logger log = LoggerFactory.getLogger(SaldoResource.class);

    private static final String ENTITY_NAME = "saldo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SaldoRepository saldoRepository;

    public SaldoResource(SaldoRepository saldoRepository) {
        this.saldoRepository = saldoRepository;
    }

    /**
     * {@code POST  /saldos} : Create a new saldo.
     *
     * @param saldo the saldo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new saldo, or with status {@code 400 (Bad Request)} if the saldo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/saldos")
    public ResponseEntity<Saldo> createSaldo(@Valid @RequestBody Saldo saldo) throws URISyntaxException {
        log.debug("REST request to save Saldo : {}", saldo);
        if (saldo.getId() != null) {
            throw new BadRequestAlertException("A new saldo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Saldo result = saldoRepository.save(saldo);
        return ResponseEntity.created(new URI("/api/saldos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /saldos} : Updates an existing saldo.
     *
     * @param saldo the saldo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated saldo,
     * or with status {@code 400 (Bad Request)} if the saldo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the saldo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/saldos")
    public ResponseEntity<Saldo> updateSaldo(@Valid @RequestBody Saldo saldo) throws URISyntaxException {
        log.debug("REST request to update Saldo : {}", saldo);
        if (saldo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Saldo result = saldoRepository.save(saldo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, saldo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /saldos} : get all the saldos.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of saldos in body.
     */
    @GetMapping("/saldos")
    public List<Saldo> getAllSaldos(@RequestParam(required = false) String filter) {
        if ("revendedor-is-null".equals(filter)) {
            log.debug("REST request to get all Saldos where revendedor is null");
            return StreamSupport
                .stream(saldoRepository.findAll().spliterator(), false)
                .filter(saldo -> saldo.getRevendedor() == null)
                .collect(Collectors.toList());
        }
        if ("banca-is-null".equals(filter)) {
            log.debug("REST request to get all Saldos where banca is null");
            return StreamSupport
                .stream(saldoRepository.findAll().spliterator(), false)
                .filter(saldo -> saldo.getBanca() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Saldos");
        return saldoRepository.findAll();
    }

    /**
     * {@code GET  /saldos/:id} : get the "id" saldo.
     *
     * @param id the id of the saldo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the saldo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/saldos/{id}")
    public ResponseEntity<Saldo> getSaldo(@PathVariable Long id) {
        log.debug("REST request to get Saldo : {}", id);
        Optional<Saldo> saldo = saldoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(saldo);
    }

    /**
     * {@code DELETE  /saldos/:id} : delete the "id" saldo.
     *
     * @param id the id of the saldo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/saldos/{id}")
    public ResponseEntity<Void> deleteSaldo(@PathVariable Long id) {
        log.debug("REST request to delete Saldo : {}", id);
        saldoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    /**
     * {@code GET  /saldos/:id} : get the "id" saldo.
     *
     * @param id the id of the saldo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the saldo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/saldos/mobile/{id}")
    public ResponseEntity<Saldo> getSaldoMobile(@PathVariable Long id) {
        log.debug("REST request to get Saldo : {}", id);
        Optional<Saldo> saldo = saldoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(saldo);
    }
}
