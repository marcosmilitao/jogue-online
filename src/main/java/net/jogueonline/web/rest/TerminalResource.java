package net.jogueonline.web.rest;

import net.jogueonline.domain.Terminal;
import net.jogueonline.repository.TerminalRepository;
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
 * REST controller for managing {@link net.jogueonline.domain.Terminal}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TerminalResource {

    private final Logger log = LoggerFactory.getLogger(TerminalResource.class);

    private static final String ENTITY_NAME = "terminal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TerminalRepository terminalRepository;

    public TerminalResource(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    /**
     * {@code POST  /terminals} : Create a new terminal.
     *
     * @param terminal the terminal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new terminal, or with status {@code 400 (Bad Request)} if the terminal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/terminals")
    public ResponseEntity<Terminal> createTerminal(@Valid @RequestBody Terminal terminal) throws URISyntaxException {
        log.debug("REST request to save Terminal : {}", terminal);
        if (terminal.getId() != null) {
            throw new BadRequestAlertException("A new terminal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Terminal result = terminalRepository.save(terminal);
        return ResponseEntity.created(new URI("/api/terminals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /terminals} : Updates an existing terminal.
     *
     * @param terminal the terminal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated terminal,
     * or with status {@code 400 (Bad Request)} if the terminal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the terminal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/terminals")
    public ResponseEntity<Terminal> updateTerminal(@Valid @RequestBody Terminal terminal) throws URISyntaxException {
        log.debug("REST request to update Terminal : {}", terminal);
        if (terminal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Terminal result = terminalRepository.save(terminal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, terminal.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /terminals} : get all the terminals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of terminals in body.
     */
    @GetMapping("/terminals")
    public List<Terminal> getAllTerminals() {
        log.debug("REST request to get all Terminals");
        return terminalRepository.findAll();
    }

    /**
     * {@code GET  /terminals/:id} : get the "id" terminal.
     *
     * @param id the id of the terminal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the terminal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/terminals/{id}")
    public ResponseEntity<Terminal> getTerminal(@PathVariable Long id) {
        log.debug("REST request to get Terminal : {}", id);
        Optional<Terminal> terminal = terminalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(terminal);
    }

    /**
     * {@code DELETE  /terminals/:id} : delete the "id" terminal.
     *
     * @param id the id of the terminal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/terminals/{id}")
    public ResponseEntity<Void> deleteTerminal(@PathVariable Long id) {
        log.debug("REST request to delete Terminal : {}", id);
        terminalRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }


    /**
     * {@code GET  /terminals/:emei} : get the "emei" terminal.
     *
     * @param imei the imei of the terminal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the terminal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/terminal/login2/{imei}/{telefone}")
    public ResponseEntity<Terminal> getTerminalbyImei(@PathVariable String imei, @PathVariable Long telefone) {
        log.debug("REST request to get Terminal : {}", imei);
        Optional<Terminal> terminal = terminalRepository.findOneByImei(imei,telefone);
        return ResponseUtil.wrapOrNotFound(terminal);
    }
}
