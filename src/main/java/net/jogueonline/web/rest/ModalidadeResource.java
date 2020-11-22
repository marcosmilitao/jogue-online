package net.jogueonline.web.rest;

import net.jogueonline.domain.Modalidade;
import net.jogueonline.repository.ModalidadeRepository;
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
 * REST controller for managing {@link net.jogueonline.domain.Modalidade}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ModalidadeResource {

    private final Logger log = LoggerFactory.getLogger(ModalidadeResource.class);

    private static final String ENTITY_NAME = "modalidade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModalidadeRepository modalidadeRepository;

    public ModalidadeResource(ModalidadeRepository modalidadeRepository) {
        this.modalidadeRepository = modalidadeRepository;
    }

    /**
     * {@code POST  /modalidades} : Create a new modalidade.
     *
     * @param modalidade the modalidade to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modalidade, or with status {@code 400 (Bad Request)} if the modalidade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modalidades")
    public ResponseEntity<Modalidade> createModalidade(@Valid @RequestBody Modalidade modalidade) throws URISyntaxException {
        log.debug("REST request to save Modalidade : {}", modalidade);
        if (modalidade.getId() != null) {
            throw new BadRequestAlertException("A new modalidade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Modalidade result = modalidadeRepository.save(modalidade);
        return ResponseEntity.created(new URI("/api/modalidades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /modalidades} : Updates an existing modalidade.
     *
     * @param modalidade the modalidade to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modalidade,
     * or with status {@code 400 (Bad Request)} if the modalidade is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modalidade couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modalidades")
    public ResponseEntity<Modalidade> updateModalidade(@Valid @RequestBody Modalidade modalidade) throws URISyntaxException {
        log.debug("REST request to update Modalidade : {}", modalidade);
        if (modalidade.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Modalidade result = modalidadeRepository.save(modalidade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modalidade.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /modalidades} : get all the modalidades.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modalidades in body.
     */
    @GetMapping("/modalidades")
    public List<Modalidade> getAllModalidades(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Modalidades");
        return modalidadeRepository.findAllWithEagerRelationships();
    }

    /**
     * {@code GET  /modalidades/:id} : get the "id" modalidade.
     *
     * @param id the id of the modalidade to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modalidade, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modalidades/{id}")
    public ResponseEntity<Modalidade> getModalidade(@PathVariable Long id) {
        log.debug("REST request to get Modalidade : {}", id);
        Optional<Modalidade> modalidade = modalidadeRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(modalidade);
    }

    /**
     * {@code DELETE  /modalidades/:id} : delete the "id" modalidade.
     *
     * @param id the id of the modalidade to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modalidades/{id}")
    public ResponseEntity<Void> deleteModalidade(@PathVariable Long id) {
        log.debug("REST request to delete Modalidade : {}", id);
        modalidadeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @CrossOrigin("*")
    @GetMapping("/modalidade/banca/{id}")
    public List<Modalidade> getModalidadeBanca(@PathVariable Long id){
        return modalidadeRepository.findByBancaPremio(id);
    }
}
