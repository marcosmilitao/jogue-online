package net.jogueonline.web.rest;

import net.jogueonline.domain.Bilhete;
import net.jogueonline.repository.BilheteRepository;
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
 * REST controller for managing {@link net.jogueonline.domain.Bilhete}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BilheteResource {

    private final Logger log = LoggerFactory.getLogger(BilheteResource.class);

    private static final String ENTITY_NAME = "bilhete";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BilheteRepository bilheteRepository;

    public BilheteResource(BilheteRepository bilheteRepository) {
        this.bilheteRepository = bilheteRepository;
    }

    /**
     * {@code POST  /bilhetes} : Create a new bilhete.
     *
     * @param bilhete the bilhete to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bilhete, or with status {@code 400 (Bad Request)} if the bilhete has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bilhetes")
    public ResponseEntity<Bilhete> createBilhete(@Valid @RequestBody Bilhete bilhete) throws URISyntaxException {
        log.debug("REST request to save Bilhete : {}", bilhete);
        if (bilhete.getId() != null) {
            throw new BadRequestAlertException("A new bilhete cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bilhete result = bilheteRepository.save(bilhete);
        return ResponseEntity.created(new URI("/api/bilhetes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bilhetes} : Updates an existing bilhete.
     *
     * @param bilhete the bilhete to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bilhete,
     * or with status {@code 400 (Bad Request)} if the bilhete is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bilhete couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bilhetes")
    public ResponseEntity<Bilhete> updateBilhete(@Valid @RequestBody Bilhete bilhete) throws URISyntaxException {
        log.debug("REST request to update Bilhete : {}", bilhete);
        if (bilhete.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bilhete result = bilheteRepository.save(bilhete);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bilhete.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bilhetes} : get all the bilhetes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bilhetes in body.
     */
    @GetMapping("/bilhetes")
    public List<Bilhete> getAllBilhetes() {
        log.debug("REST request to get all Bilhetes");
        return bilheteRepository.findAll();
    }

    /**
     * {@code GET  /bilhetes/:id} : get the "id" bilhete.
     *
     * @param id the id of the bilhete to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bilhete, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bilhetes/{id}")
    public ResponseEntity<Bilhete> getBilhete(@PathVariable Long id) {
        log.debug("REST request to get Bilhete : {}", id);
        Optional<Bilhete> bilhete = bilheteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bilhete);
    }

    /**
     * {@code DELETE  /bilhetes/:id} : delete the "id" bilhete.
     *
     * @param id the id of the bilhete to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bilhetes/{id}")
    public ResponseEntity<Void> deleteBilhete(@PathVariable Long id) {
        log.debug("REST request to delete Bilhete : {}", id);
        bilheteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
