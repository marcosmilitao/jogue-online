package net.jogueonline.web.rest;

import net.jogueonline.domain.Promotor;
import net.jogueonline.repository.PromotorRepository;
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
 * REST controller for managing {@link net.jogueonline.domain.Promotor}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PromotorResource {

    private final Logger log = LoggerFactory.getLogger(PromotorResource.class);

    private static final String ENTITY_NAME = "promotor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PromotorRepository promotorRepository;

    public PromotorResource(PromotorRepository promotorRepository) {
        this.promotorRepository = promotorRepository;
    }

    /**
     * {@code POST  /promotors} : Create a new promotor.
     *
     * @param promotor the promotor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new promotor, or with status {@code 400 (Bad Request)} if the promotor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/promotors")
    public ResponseEntity<Promotor> createPromotor(@Valid @RequestBody Promotor promotor) throws URISyntaxException {
        log.debug("REST request to save Promotor : {}", promotor);
        if (promotor.getId() != null) {
            throw new BadRequestAlertException("A new promotor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Promotor result = promotorRepository.save(promotor);
        return ResponseEntity.created(new URI("/api/promotors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /promotors} : Updates an existing promotor.
     *
     * @param promotor the promotor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated promotor,
     * or with status {@code 400 (Bad Request)} if the promotor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the promotor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/promotors")
    public ResponseEntity<Promotor> updatePromotor(@Valid @RequestBody Promotor promotor) throws URISyntaxException {
        log.debug("REST request to update Promotor : {}", promotor);
        if (promotor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Promotor result = promotorRepository.save(promotor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, promotor.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /promotors} : get all the promotors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of promotors in body.
     */
    @GetMapping("/promotors")
    public List<Promotor> getAllPromotors() {
        log.debug("REST request to get all Promotors");
        return promotorRepository.findAll();
    }

    /**
     * {@code GET  /promotors/:id} : get the "id" promotor.
     *
     * @param id the id of the promotor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the promotor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/promotors/{id}")
    public ResponseEntity<Promotor> getPromotor(@PathVariable Long id) {
        log.debug("REST request to get Promotor : {}", id);
        Optional<Promotor> promotor = promotorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(promotor);
    }

    /**
     * {@code DELETE  /promotors/:id} : delete the "id" promotor.
     *
     * @param id the id of the promotor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/promotors/{id}")
    public ResponseEntity<Void> deletePromotor(@PathVariable Long id) {
        log.debug("REST request to delete Promotor : {}", id);
        promotorRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
