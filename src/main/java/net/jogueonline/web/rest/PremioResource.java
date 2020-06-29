package net.jogueonline.web.rest;

import net.jogueonline.domain.Premio;
import net.jogueonline.repository.PremioRepository;
import net.jogueonline.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link net.jogueonline.domain.Premio}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PremioResource {

    private final Logger log = LoggerFactory.getLogger(PremioResource.class);

    private static final String ENTITY_NAME = "premio";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PremioRepository premioRepository;

    public PremioResource(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    /**
     * {@code POST  /premios} : Create a new premio.
     *
     * @param premio the premio to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new premio, or with status {@code 400 (Bad Request)} if the premio has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/premios")
    public ResponseEntity<Premio> createPremio(@RequestBody Premio premio) throws URISyntaxException {
        log.debug("REST request to save Premio : {}", premio);
        if (premio.getId() != null) {
            throw new BadRequestAlertException("A new premio cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Premio result = premioRepository.save(premio);
        return ResponseEntity.created(new URI("/api/premios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /premios} : Updates an existing premio.
     *
     * @param premio the premio to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated premio,
     * or with status {@code 400 (Bad Request)} if the premio is not valid,
     * or with status {@code 500 (Internal Server Error)} if the premio couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/premios")
    public ResponseEntity<Premio> updatePremio(@RequestBody Premio premio) throws URISyntaxException {
        log.debug("REST request to update Premio : {}", premio);
        if (premio.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Premio result = premioRepository.save(premio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, premio.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /premios} : get all the premios.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of premios in body.
     */
    @GetMapping("/premios")
    public List<Premio> getAllPremios() {
        log.debug("REST request to get all Premios");
        return premioRepository.findAll();
    }

    /**
     * {@code GET  /premios/:id} : get the "id" premio.
     *
     * @param id the id of the premio to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the premio, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/premios/{id}")
    public ResponseEntity<Premio> getPremio(@PathVariable Long id) {
        log.debug("REST request to get Premio : {}", id);
        Optional<Premio> premio = premioRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(premio);
    }

    /**
     * {@code DELETE  /premios/:id} : delete the "id" premio.
     *
     * @param id the id of the premio to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/premios/{id}")
    public ResponseEntity<Void> deletePremio(@PathVariable Long id) {
        log.debug("REST request to delete Premio : {}", id);
        premioRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
