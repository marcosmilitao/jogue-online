package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Premio;
import net.jogueonline.repository.PremioRepository;

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
 * Integration tests for the {@link PremioResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class PremioResourceIT {

    private static final Long DEFAULT_CODIGO = 1L;
    private static final Long UPDATED_CODIGO = 2L;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private PremioRepository premioRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPremioMockMvc;

    private Premio premio;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Premio createEntity(EntityManager em) {
        Premio premio = new Premio()
            .codigo(DEFAULT_CODIGO)
            .nome(DEFAULT_NOME);
        return premio;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Premio createUpdatedEntity(EntityManager em) {
        Premio premio = new Premio()
            .codigo(UPDATED_CODIGO)
            .nome(UPDATED_NOME);
        return premio;
    }

    @BeforeEach
    public void initTest() {
        premio = createEntity(em);
    }

    @Test
    @Transactional
    public void createPremio() throws Exception {
        int databaseSizeBeforeCreate = premioRepository.findAll().size();

        // Create the Premio
        restPremioMockMvc.perform(post("/api/premios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(premio)))
            .andExpect(status().isCreated());

        // Validate the Premio in the database
        List<Premio> premioList = premioRepository.findAll();
        assertThat(premioList).hasSize(databaseSizeBeforeCreate + 1);
        Premio testPremio = premioList.get(premioList.size() - 1);
        assertThat(testPremio.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testPremio.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createPremioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = premioRepository.findAll().size();

        // Create the Premio with an existing ID
        premio.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPremioMockMvc.perform(post("/api/premios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(premio)))
            .andExpect(status().isBadRequest());

        // Validate the Premio in the database
        List<Premio> premioList = premioRepository.findAll();
        assertThat(premioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPremios() throws Exception {
        // Initialize the database
        premioRepository.saveAndFlush(premio);

        // Get all the premioList
        restPremioMockMvc.perform(get("/api/premios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(premio.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)));
    }
    
    @Test
    @Transactional
    public void getPremio() throws Exception {
        // Initialize the database
        premioRepository.saveAndFlush(premio);

        // Get the premio
        restPremioMockMvc.perform(get("/api/premios/{id}", premio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(premio.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME));
    }

    @Test
    @Transactional
    public void getNonExistingPremio() throws Exception {
        // Get the premio
        restPremioMockMvc.perform(get("/api/premios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePremio() throws Exception {
        // Initialize the database
        premioRepository.saveAndFlush(premio);

        int databaseSizeBeforeUpdate = premioRepository.findAll().size();

        // Update the premio
        Premio updatedPremio = premioRepository.findById(premio.getId()).get();
        // Disconnect from session so that the updates on updatedPremio are not directly saved in db
        em.detach(updatedPremio);
        updatedPremio
            .codigo(UPDATED_CODIGO)
            .nome(UPDATED_NOME);

        restPremioMockMvc.perform(put("/api/premios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPremio)))
            .andExpect(status().isOk());

        // Validate the Premio in the database
        List<Premio> premioList = premioRepository.findAll();
        assertThat(premioList).hasSize(databaseSizeBeforeUpdate);
        Premio testPremio = premioList.get(premioList.size() - 1);
        assertThat(testPremio.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testPremio.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingPremio() throws Exception {
        int databaseSizeBeforeUpdate = premioRepository.findAll().size();

        // Create the Premio

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPremioMockMvc.perform(put("/api/premios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(premio)))
            .andExpect(status().isBadRequest());

        // Validate the Premio in the database
        List<Premio> premioList = premioRepository.findAll();
        assertThat(premioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePremio() throws Exception {
        // Initialize the database
        premioRepository.saveAndFlush(premio);

        int databaseSizeBeforeDelete = premioRepository.findAll().size();

        // Delete the premio
        restPremioMockMvc.perform(delete("/api/premios/{id}", premio.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Premio> premioList = premioRepository.findAll();
        assertThat(premioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
