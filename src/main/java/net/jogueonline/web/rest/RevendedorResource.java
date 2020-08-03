package net.jogueonline.web.rest;

import net.jogueonline.domain.Revendedor;
import net.jogueonline.repository.RevendedorRepository;
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
 * REST controller for managing {@link net.jogueonline.domain.Revendedor}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RevendedorResource {

    private final Logger log = LoggerFactory.getLogger(RevendedorResource.class);

    private static final String ENTITY_NAME = "revendedor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RevendedorRepository revendedorRepository;

    public RevendedorResource(RevendedorRepository revendedorRepository) {
        this.revendedorRepository = revendedorRepository;
    }

    /**
     * {@code POST  /revendedors} : Create a new revendedor.
     *
     * @param revendedor the revendedor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new revendedor, or with status {@code 400 (Bad Request)} if the revendedor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/revendedors")
    public ResponseEntity<Revendedor> createRevendedor(@Valid @RequestBody Revendedor revendedor) throws URISyntaxException {
        log.debug("REST request to save Revendedor : {}", revendedor);
        if (revendedor.getId() != null) {
            throw new BadRequestAlertException("A new revendedor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Revendedor result = revendedorRepository.save(revendedor);
        return ResponseEntity.created(new URI("/api/revendedors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /revendedors} : Updates an existing revendedor.
     *
     * @param revendedor the revendedor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated revendedor,
     * or with status {@code 400 (Bad Request)} if the revendedor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the revendedor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/revendedors")
    public ResponseEntity<Revendedor> updateRevendedor(@Valid @RequestBody Revendedor revendedor) throws URISyntaxException {
        log.debug("REST request to update Revendedor : {}", revendedor);
        if (revendedor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Revendedor result = revendedorRepository.save(revendedor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, revendedor.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /revendedors} : get all the revendedors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of revendedors in body.
     */
    @GetMapping("/revendedors")
    public List<Revendedor> getAllRevendedors() {
        log.debug("REST request to get all Revendedors");
        return revendedorRepository.findAll();
    }

    /**
     * {@code GET  /revendedors/:id} : get the "id" revendedor.
     *
     * @param id the id of the revendedor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the revendedor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/revendedors/{id}")
    public ResponseEntity<Revendedor> getRevendedor(@PathVariable Long id) {
        log.debug("REST request to get Revendedor : {}", id);
        Optional<Revendedor> revendedor = revendedorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(revendedor);
    }

    /**
     * {@code DELETE  /revendedors/:id} : delete the "id" revendedor.
     *
     * @param id the id of the revendedor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/revendedors/{id}")
    public ResponseEntity<Void> deleteRevendedor(@PathVariable Long id) {
        log.debug("REST request to delete Revendedor : {}", id);
        revendedorRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
