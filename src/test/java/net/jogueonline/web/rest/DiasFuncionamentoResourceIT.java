package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.DiasFuncionamento;
import net.jogueonline.repository.DiasFuncionamentoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DiasFuncionamentoResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DiasFuncionamentoResourceIT {

    private static final String DEFAULT_DIA = "AAAAAAAAAA";
    private static final String UPDATED_DIA = "BBBBBBBBBB";

    @Autowired
    private DiasFuncionamentoRepository diasFuncionamentoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDiasFuncionamentoMockMvc;

    private DiasFuncionamento diasFuncionamento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DiasFuncionamento createEntity(EntityManager em) {
        DiasFuncionamento diasFuncionamento = new DiasFuncionamento()
            .dia(DEFAULT_DIA);
        return diasFuncionamento;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DiasFuncionamento createUpdatedEntity(EntityManager em) {
        DiasFuncionamento diasFuncionamento = new DiasFuncionamento()
            .dia(UPDATED_DIA);
        return diasFuncionamento;
    }

    @BeforeEach
    public void initTest() {
        diasFuncionamento = createEntity(em);
    }

    @Test
    @Transactional
    public void createDiasFuncionamento() throws Exception {
        int databaseSizeBeforeCreate = diasFuncionamentoRepository.findAll().size();

        // Create the DiasFuncionamento
        restDiasFuncionamentoMockMvc.perform(post("/api/dias-funcionamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(diasFuncionamento)))
            .andExpect(status().isCreated());

        // Validate the DiasFuncionamento in the database
        List<DiasFuncionamento> diasFuncionamentoList = diasFuncionamentoRepository.findAll();
        assertThat(diasFuncionamentoList).hasSize(databaseSizeBeforeCreate + 1);
        DiasFuncionamento testDiasFuncionamento = diasFuncionamentoList.get(diasFuncionamentoList.size() - 1);
        assertThat(testDiasFuncionamento.getDia()).isEqualTo(DEFAULT_DIA);
    }

    @Test
    @Transactional
    public void createDiasFuncionamentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diasFuncionamentoRepository.findAll().size();

        // Create the DiasFuncionamento with an existing ID
        diasFuncionamento.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiasFuncionamentoMockMvc.perform(post("/api/dias-funcionamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(diasFuncionamento)))
            .andExpect(status().isBadRequest());

        // Validate the DiasFuncionamento in the database
        List<DiasFuncionamento> diasFuncionamentoList = diasFuncionamentoRepository.findAll();
        assertThat(diasFuncionamentoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = diasFuncionamentoRepository.findAll().size();
        // set the field null
        diasFuncionamento.setDia(null);

        // Create the DiasFuncionamento, which fails.

        restDiasFuncionamentoMockMvc.perform(post("/api/dias-funcionamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(diasFuncionamento)))
            .andExpect(status().isBadRequest());

        List<DiasFuncionamento> diasFuncionamentoList = diasFuncionamentoRepository.findAll();
        assertThat(diasFuncionamentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDiasFuncionamentos() throws Exception {
        // Initialize the database
        diasFuncionamentoRepository.saveAndFlush(diasFuncionamento);

        // Get all the diasFuncionamentoList
        restDiasFuncionamentoMockMvc.perform(get("/api/dias-funcionamentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diasFuncionamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].dia").value(hasItem(DEFAULT_DIA)));
    }
    
    @Test
    @Transactional
    public void getDiasFuncionamento() throws Exception {
        // Initialize the database
        diasFuncionamentoRepository.saveAndFlush(diasFuncionamento);

        // Get the diasFuncionamento
        restDiasFuncionamentoMockMvc.perform(get("/api/dias-funcionamentos/{id}", diasFuncionamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(diasFuncionamento.getId().intValue()))
            .andExpect(jsonPath("$.dia").value(DEFAULT_DIA));
    }

    @Test
    @Transactional
    public void getNonExistingDiasFuncionamento() throws Exception {
        // Get the diasFuncionamento
        restDiasFuncionamentoMockMvc.perform(get("/api/dias-funcionamentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiasFuncionamento() throws Exception {
        // Initialize the database
        diasFuncionamentoRepository.saveAndFlush(diasFuncionamento);

        int databaseSizeBeforeUpdate = diasFuncionamentoRepository.findAll().size();

        // Update the diasFuncionamento
        DiasFuncionamento updatedDiasFuncionamento = diasFuncionamentoRepository.findById(diasFuncionamento.getId()).get();
        // Disconnect from session so that the updates on updatedDiasFuncionamento are not directly saved in db
        em.detach(updatedDiasFuncionamento);
        updatedDiasFuncionamento
            .dia(UPDATED_DIA);

        restDiasFuncionamentoMockMvc.perform(put("/api/dias-funcionamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDiasFuncionamento)))
            .andExpect(status().isOk());

        // Validate the DiasFuncionamento in the database
        List<DiasFuncionamento> diasFuncionamentoList = diasFuncionamentoRepository.findAll();
        assertThat(diasFuncionamentoList).hasSize(databaseSizeBeforeUpdate);
        DiasFuncionamento testDiasFuncionamento = diasFuncionamentoList.get(diasFuncionamentoList.size() - 1);
        assertThat(testDiasFuncionamento.getDia()).isEqualTo(UPDATED_DIA);
    }

    @Test
    @Transactional
    public void updateNonExistingDiasFuncionamento() throws Exception {
        int databaseSizeBeforeUpdate = diasFuncionamentoRepository.findAll().size();

        // Create the DiasFuncionamento

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiasFuncionamentoMockMvc.perform(put("/api/dias-funcionamentos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(diasFuncionamento)))
            .andExpect(status().isBadRequest());

        // Validate the DiasFuncionamento in the database
        List<DiasFuncionamento> diasFuncionamentoList = diasFuncionamentoRepository.findAll();
        assertThat(diasFuncionamentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDiasFuncionamento() throws Exception {
        // Initialize the database
        diasFuncionamentoRepository.saveAndFlush(diasFuncionamento);

        int databaseSizeBeforeDelete = diasFuncionamentoRepository.findAll().size();

        // Delete the diasFuncionamento
        restDiasFuncionamentoMockMvc.perform(delete("/api/dias-funcionamentos/{id}", diasFuncionamento.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DiasFuncionamento> diasFuncionamentoList = diasFuncionamentoRepository.findAll();
        assertThat(diasFuncionamentoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
