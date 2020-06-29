package net.jogueonline.web.rest;

import net.jogueonline.domain.LimiteVenda;
import net.jogueonline.repository.LimiteVendaRepository;
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
 * REST controller for managing {@link net.jogueonline.domain.LimiteVenda}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LimiteVendaResource {

    private final Logger log = LoggerFactory.getLogger(LimiteVendaResource.class);

    private static final String ENTITY_NAME = "limiteVenda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LimiteVendaRepository limiteVendaRepository;

    public LimiteVendaResource(LimiteVendaRepository limiteVendaRepository) {
        this.limiteVendaRepository = limiteVendaRepository;
    }

    /**
     * {@code POST  /limite-vendas} : Create a new limiteVenda.
     *
     * @param limiteVenda the limiteVenda to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new limiteVenda, or with status {@code 400 (Bad Request)} if the limiteVenda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/limite-vendas")
    public ResponseEntity<LimiteVenda> createLimiteVenda(@RequestBody LimiteVenda limiteVenda) throws URISyntaxException {
        log.debug("REST request to save LimiteVenda : {}", limiteVenda);
        if (limiteVenda.getId() != null) {
            throw new BadRequestAlertException("A new limiteVenda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LimiteVenda result = limiteVendaRepository.save(limiteVenda);
        return ResponseEntity.created(new URI("/api/limite-vendas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /limite-vendas} : Updates an existing limiteVenda.
     *
     * @param limiteVenda the limiteVenda to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated limiteVenda,
     * or with status {@code 400 (Bad Request)} if the limiteVenda is not valid,
     * or with status {@code 500 (Internal Server Error)} if the limiteVenda couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/limite-vendas")
    public ResponseEntity<LimiteVenda> updateLimiteVenda(@RequestBody LimiteVenda limiteVenda) throws URISyntaxException {
        log.debug("REST request to update LimiteVenda : {}", limiteVenda);
        if (limiteVenda.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LimiteVenda result = limiteVendaRepository.save(limiteVenda);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, limiteVenda.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /limite-vendas} : get all the limiteVendas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of limiteVendas in body.
     */
    @GetMapping("/limite-vendas")
    public List<LimiteVenda> getAllLimiteVendas() {
        log.debug("REST request to get all LimiteVendas");
        return limiteVendaRepository.findAll();
    }

    /**
     * {@code GET  /limite-vendas/:id} : get the "id" limiteVenda.
     *
     * @param id the id of the limiteVenda to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the limiteVenda, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/limite-vendas/{id}")
    public ResponseEntity<LimiteVenda> getLimiteVenda(@PathVariable Long id) {
        log.debug("REST request to get LimiteVenda : {}", id);
        Optional<LimiteVenda> limiteVenda = limiteVendaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(limiteVenda);
    }

    /**
     * {@code DELETE  /limite-vendas/:id} : delete the "id" limiteVenda.
     *
     * @param id the id of the limiteVenda to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/limite-vendas/{id}")
    public ResponseEntity<Void> deleteLimiteVenda(@PathVariable Long id) {
        log.debug("REST request to delete LimiteVenda : {}", id);
        limiteVendaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
