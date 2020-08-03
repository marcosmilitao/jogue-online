package net.jogueonline.web.rest;

import net.jogueonline.domain.Aposta;
import net.jogueonline.repository.ApostaRepository;
import net.jogueonline.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link net.jogueonline.domain.Aposta}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ApostaResource {

    private final Logger log = LoggerFactory.getLogger(ApostaResource.class);

    private static final String ENTITY_NAME = "aposta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApostaRepository apostaRepository;

    public ApostaResource(ApostaRepository apostaRepository) {
        this.apostaRepository = apostaRepository;
    }

    /**
     * {@code POST  /apostas} : Create a new aposta.
     *
     * @param aposta the aposta to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new aposta, or with status {@code 400 (Bad Request)} if the aposta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/apostas")
    public ResponseEntity<Aposta> createAposta(@Valid @RequestBody Aposta aposta) throws URISyntaxException {
        log.debug("REST request to save Aposta : {}", aposta);
        if (aposta.getId() != null) {
            throw new BadRequestAlertException("A new aposta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Aposta result = apostaRepository.save(aposta);
        return ResponseEntity.created(new URI("/api/apostas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /apostas} : Updates an existing aposta.
     *
     * @param aposta the aposta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated aposta,
     * or with status {@code 400 (Bad Request)} if the aposta is not valid,
     * or with status {@code 500 (Internal Server Error)} if the aposta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/apostas")
    public ResponseEntity<Aposta> updateAposta(@Valid @RequestBody Aposta aposta) throws URISyntaxException {
        log.debug("REST request to update Aposta : {}", aposta);
        if (aposta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Aposta result = apostaRepository.save(aposta);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, aposta.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /apostas} : get all the apostas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apostas in body.
     */
    @GetMapping("/apostas")
    public ResponseEntity<List<Aposta>> getAllApostas(Pageable pageable) {
        log.debug("REST request to get a page of Apostas");
        Page<Aposta> page = apostaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /apostas/:id} : get the "id" aposta.
     *
     * @param id the id of the aposta to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the aposta, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apostas/{id}")
    public ResponseEntity<Aposta> getAposta(@PathVariable Long id) {
        log.debug("REST request to get Aposta : {}", id);
        Optional<Aposta> aposta = apostaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(aposta);
    }

    /**
     * {@code DELETE  /apostas/:id} : delete the "id" aposta.
     *
     * @param id the id of the aposta to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/apostas/{id}")
    public ResponseEntity<Void> deleteAposta(@PathVariable Long id) {
        log.debug("REST request to delete Aposta : {}", id);
        apostaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    //apostas/mobile
    @PostMapping("/apostas/mobile")
    public ResponseEntity<Void> createAposta2(@Valid @RequestBody List<Aposta> aposta) throws URISyntaxException {
        log.debug("REST request to save Aposta : {}", aposta);
//         if (aposta.getId() != null) {
//             throw new BadRequestAlertException("A new aposta cannot already have an ID", ENTITY_NAME, "idexists");
//         }
        apostaRepository.saveAll(aposta);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, "1")).build();
    }
}
