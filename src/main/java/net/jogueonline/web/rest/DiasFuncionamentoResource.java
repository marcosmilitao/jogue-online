package net.jogueonline.web.rest;

import net.jogueonline.domain.DiasFuncionamento;
import net.jogueonline.repository.DiasFuncionamentoRepository;
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
 * REST controller for managing {@link net.jogueonline.domain.DiasFuncionamento}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DiasFuncionamentoResource {

    private final Logger log = LoggerFactory.getLogger(DiasFuncionamentoResource.class);

    private static final String ENTITY_NAME = "diasFuncionamento";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DiasFuncionamentoRepository diasFuncionamentoRepository;

    public DiasFuncionamentoResource(DiasFuncionamentoRepository diasFuncionamentoRepository) {
        this.diasFuncionamentoRepository = diasFuncionamentoRepository;
    }

    /**
     * {@code POST  /dias-funcionamentos} : Create a new diasFuncionamento.
     *
     * @param diasFuncionamento the diasFuncionamento to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new diasFuncionamento, or with status {@code 400 (Bad Request)} if the diasFuncionamento has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dias-funcionamentos")
    public ResponseEntity<DiasFuncionamento> createDiasFuncionamento(@Valid @RequestBody DiasFuncionamento diasFuncionamento) throws URISyntaxException {
        log.debug("REST request to save DiasFuncionamento : {}", diasFuncionamento);
        if (diasFuncionamento.getId() != null) {
            throw new BadRequestAlertException("A new diasFuncionamento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiasFuncionamento result = diasFuncionamentoRepository.save(diasFuncionamento);
        return ResponseEntity.created(new URI("/api/dias-funcionamentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dias-funcionamentos} : Updates an existing diasFuncionamento.
     *
     * @param diasFuncionamento the diasFuncionamento to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated diasFuncionamento,
     * or with status {@code 400 (Bad Request)} if the diasFuncionamento is not valid,
     * or with status {@code 500 (Internal Server Error)} if the diasFuncionamento couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dias-funcionamentos")
    public ResponseEntity<DiasFuncionamento> updateDiasFuncionamento(@Valid @RequestBody DiasFuncionamento diasFuncionamento) throws URISyntaxException {
        log.debug("REST request to update DiasFuncionamento : {}", diasFuncionamento);
        if (diasFuncionamento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiasFuncionamento result = diasFuncionamentoRepository.save(diasFuncionamento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, diasFuncionamento.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dias-funcionamentos} : get all the diasFuncionamentos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of diasFuncionamentos in body.
     */
    @GetMapping("/dias-funcionamentos")
    public List<DiasFuncionamento> getAllDiasFuncionamentos() {
        log.debug("REST request to get all DiasFuncionamentos");
        return diasFuncionamentoRepository.findAll();
    }

    /**
     * {@code GET  /dias-funcionamentos/:id} : get the "id" diasFuncionamento.
     *
     * @param id the id of the diasFuncionamento to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the diasFuncionamento, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dias-funcionamentos/{id}")
    public ResponseEntity<DiasFuncionamento> getDiasFuncionamento(@PathVariable Long id) {
        log.debug("REST request to get DiasFuncionamento : {}", id);
        Optional<DiasFuncionamento> diasFuncionamento = diasFuncionamentoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(diasFuncionamento);
    }

    /**
     * {@code DELETE  /dias-funcionamentos/:id} : delete the "id" diasFuncionamento.
     *
     * @param id the id of the diasFuncionamento to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dias-funcionamentos/{id}")
    public ResponseEntity<Void> deleteDiasFuncionamento(@PathVariable Long id) {
        log.debug("REST request to delete DiasFuncionamento : {}", id);
        diasFuncionamentoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
