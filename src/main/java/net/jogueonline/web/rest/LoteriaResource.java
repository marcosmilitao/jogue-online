package net.jogueonline.web.rest;

import net.jogueonline.domain.Loteria;
import net.jogueonline.repository.LoteriaRepository;
import net.jogueonline.service.LoteriaService;
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

/**
 * REST controller for managing {@link net.jogueonline.domain.Loteria}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LoteriaResource {

    private final Logger log = LoggerFactory.getLogger(LoteriaResource.class);

    private static final String ENTITY_NAME = "loteria";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoteriaRepository loteriaRepository;
    private final LoteriaService loteriaService;

    public LoteriaResource(LoteriaRepository loteriaRepository , LoteriaService loteriaService) {
        this.loteriaRepository = loteriaRepository;
        this.loteriaService = loteriaService;
    }

    /**
     * {@code POST  /loterias} : Create a new loteria.
     *
     * @param loteria the loteria to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loteria, or with status {@code 400 (Bad Request)} if the loteria has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/loterias")
    public ResponseEntity<Loteria> createLoteria(@Valid @RequestBody Loteria loteria) throws URISyntaxException {
        log.debug("REST request to save Loteria : {}", loteria);
        if (loteria.getId() != null) {
            throw new BadRequestAlertException("A new loteria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Loteria result = loteriaRepository.save(loteria);
        return ResponseEntity.created(new URI("/api/loterias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /loterias} : Updates an existing loteria.
     *
     * @param loteria the loteria to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loteria,
     * or with status {@code 400 (Bad Request)} if the loteria is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loteria couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/loterias")
    public ResponseEntity<Loteria> updateLoteria(@Valid @RequestBody Loteria loteria) throws URISyntaxException {
        log.debug("REST request to update Loteria : {}", loteria);
        if (loteria.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Loteria result = loteriaRepository.save(loteria);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, loteria.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /loterias} : get all the loterias.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loterias in body.
     */
    @GetMapping("/loterias")
    public List<Loteria> getAllLoterias(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Loterias");
        return loteriaRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /loterias/:id} : get the "id" loteria.
     *
     * @param id the id of the loteria to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loteria, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/loterias/{id}")
    public ResponseEntity<Loteria> getLoteria(@PathVariable Long id) {
        log.debug("REST request to get Loteria : {}", id);
        Optional<Loteria> loteria = loteriaRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(loteria);
    }

    /**
     * {@code DELETE  /loterias/:id} : delete the "id" loteria.
     *
     * @param id the id of the loteria to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/loterias/{id}")
    public ResponseEntity<Void> deleteLoteria(@PathVariable Long id) {
        log.debug("REST request to delete Loteria : {}", id);
        loteriaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/loterias/banca/{id}")
    public List<Loteria> findByBanca(@PathVariable Long id){
        return loteriaService.listarLoterias(id);
    }
}
