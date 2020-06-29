package net.jogueonline.web.rest;

import net.jogueonline.domain.Banca;
import net.jogueonline.repository.BancaRepository;
import net.jogueonline.security.SecurityUtils;
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
 * REST controller for managing {@link net.jogueonline.domain.Banca}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BancaResource {

    private final Logger log = LoggerFactory.getLogger(BancaResource.class);

    private static final String ENTITY_NAME = "banca";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BancaRepository bancaRepository;

    public BancaResource(BancaRepository bancaRepository) {
        this.bancaRepository = bancaRepository;
    }

    /**
     * {@code POST  /bancas} : Create a new banca.
     *
     * @param banca the banca to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new banca, or with status {@code 400 (Bad Request)} if the banca has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bancas")
    public ResponseEntity<Banca> createBanca(@Valid @RequestBody Banca banca) throws URISyntaxException {
        log.debug("REST request to save Banca : {}", banca);
        if (banca.getId() != null) {
            throw new BadRequestAlertException("A new banca cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Banca result = bancaRepository.save(banca);
        return ResponseEntity.created(new URI("/api/bancas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bancas} : Updates an existing banca.
     *
     * @param banca the banca to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated banca,
     * or with status {@code 400 (Bad Request)} if the banca is not valid,
     * or with status {@code 500 (Internal Server Error)} if the banca couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bancas")
    public ResponseEntity<Banca> updateBanca(@Valid @RequestBody Banca banca) throws URISyntaxException {
        log.debug("REST request to update Banca : {}", banca);
        if (banca.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Banca result = bancaRepository.save(banca);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, banca.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bancas} : get all the bancas.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bancas in body.
     */
    @GetMapping("/bancas")
    public List<Banca> getAllBancas(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Bancas");
        if(SecurityUtils.isCurrentUserInRole("ROLE_ADMIN")){
            return bancaRepository.findAllWithEagerRelationships();
        }else {
            return bancaRepository.findOneByUser(SecurityUtils.getCurrentUserLogin().get());
        }
    }

    /**
     * {@code GET  /bancas/:id} : get the "id" banca.
     *
     * @param id the id of the banca to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the banca, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bancas/{id}")
    public ResponseEntity<Banca> getBanca(@PathVariable Long id) {
        log.debug("REST request to get Banca : {}", id);
        Optional<Banca> banca = bancaRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(banca);
    }

    /**
     * {@code DELETE  /bancas/:id} : delete the "id" banca.
     *
     * @param id the id of the banca to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bancas/{id}")
    public ResponseEntity<Void> deleteBanca(@PathVariable Long id) {
        log.debug("REST request to delete Banca : {}", id);
        bancaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    @GetMapping("/terminal/login/{serial}/{telefone}")
    public ResponseEntity<Banca> getTerminalUserInfo(@PathVariable String serial, @PathVariable Long telefone){
        // log.debug("REST request to delete Banca : {}", bancaRepository.loginTerminal(serial,telefone));
        Optional<Banca> banca = bancaRepository.loginTerminal(serial,telefone);
        return ResponseUtil.wrapOrNotFound(banca);
    }
}
