package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Promotor;
import net.jogueonline.repository.PromotorRepository;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PromotorResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class PromotorResourceIT {

    private static final Long DEFAULT_CODIGO = 1L;
    private static final Long UPDATED_CODIGO = 2L;

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    private static final Long DEFAULT_COMISSAO = 1L;
    private static final Long UPDATED_COMISSAO = 2L;

    private static final Instant DEFAULT_DATA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private PromotorRepository promotorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPromotorMockMvc;

    private Promotor promotor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promotor createEntity(EntityManager em) {
        Promotor promotor = new Promotor()
            .codigo(DEFAULT_CODIGO)
            .nome(DEFAULT_NOME)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .telefone(DEFAULT_TELEFONE)
            .comissao(DEFAULT_COMISSAO)
            .data(DEFAULT_DATA);
        return promotor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Promotor createUpdatedEntity(EntityManager em) {
        Promotor promotor = new Promotor()
            .codigo(UPDATED_CODIGO)
            .nome(UPDATED_NOME)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefone(UPDATED_TELEFONE)
            .comissao(UPDATED_COMISSAO)
            .data(UPDATED_DATA);
        return promotor;
    }

    @BeforeEach
    public void initTest() {
        promotor = createEntity(em);
    }

    @Test
    @Transactional
    public void createPromotor() throws Exception {
        int databaseSizeBeforeCreate = promotorRepository.findAll().size();

        // Create the Promotor
        restPromotorMockMvc.perform(post("/api/promotors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotor)))
            .andExpect(status().isCreated());

        // Validate the Promotor in the database
        List<Promotor> promotorList = promotorRepository.findAll();
        assertThat(promotorList).hasSize(databaseSizeBeforeCreate + 1);
        Promotor testPromotor = promotorList.get(promotorList.size() - 1);
        assertThat(testPromotor.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testPromotor.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPromotor.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testPromotor.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testPromotor.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testPromotor.getComissao()).isEqualTo(DEFAULT_COMISSAO);
        assertThat(testPromotor.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createPromotorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = promotorRepository.findAll().size();

        // Create the Promotor with an existing ID
        promotor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromotorMockMvc.perform(post("/api/promotors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotor)))
            .andExpect(status().isBadRequest());

        // Validate the Promotor in the database
        List<Promotor> promotorList = promotorRepository.findAll();
        assertThat(promotorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotorRepository.findAll().size();
        // set the field null
        promotor.setCodigo(null);

        // Create the Promotor, which fails.

        restPromotorMockMvc.perform(post("/api/promotors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotor)))
            .andExpect(status().isBadRequest());

        List<Promotor> promotorList = promotorRepository.findAll();
        assertThat(promotorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotorRepository.findAll().size();
        // set the field null
        promotor.setNome(null);

        // Create the Promotor, which fails.

        restPromotorMockMvc.perform(post("/api/promotors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotor)))
            .andExpect(status().isBadRequest());

        List<Promotor> promotorList = promotorRepository.findAll();
        assertThat(promotorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = promotorRepository.findAll().size();
        // set the field null
        promotor.setTelefone(null);

        // Create the Promotor, which fails.

        restPromotorMockMvc.perform(post("/api/promotors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotor)))
            .andExpect(status().isBadRequest());

        List<Promotor> promotorList = promotorRepository.findAll();
        assertThat(promotorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPromotors() throws Exception {
        // Initialize the database
        promotorRepository.saveAndFlush(promotor);

        // Get all the promotorList
        restPromotorMockMvc.perform(get("/api/promotors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promotor.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)))
            .andExpect(jsonPath("$.[*].comissao").value(hasItem(DEFAULT_COMISSAO.intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getPromotor() throws Exception {
        // Initialize the database
        promotorRepository.saveAndFlush(promotor);

        // Get the promotor
        restPromotorMockMvc.perform(get("/api/promotors/{id}", promotor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(promotor.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE))
            .andExpect(jsonPath("$.comissao").value(DEFAULT_COMISSAO.intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPromotor() throws Exception {
        // Get the promotor
        restPromotorMockMvc.perform(get("/api/promotors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePromotor() throws Exception {
        // Initialize the database
        promotorRepository.saveAndFlush(promotor);

        int databaseSizeBeforeUpdate = promotorRepository.findAll().size();

        // Update the promotor
        Promotor updatedPromotor = promotorRepository.findById(promotor.getId()).get();
        // Disconnect from session so that the updates on updatedPromotor are not directly saved in db
        em.detach(updatedPromotor);
        updatedPromotor
            .codigo(UPDATED_CODIGO)
            .nome(UPDATED_NOME)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefone(UPDATED_TELEFONE)
            .comissao(UPDATED_COMISSAO)
            .data(UPDATED_DATA);

        restPromotorMockMvc.perform(put("/api/promotors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPromotor)))
            .andExpect(status().isOk());

        // Validate the Promotor in the database
        List<Promotor> promotorList = promotorRepository.findAll();
        assertThat(promotorList).hasSize(databaseSizeBeforeUpdate);
        Promotor testPromotor = promotorList.get(promotorList.size() - 1);
        assertThat(testPromotor.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testPromotor.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPromotor.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testPromotor.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testPromotor.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testPromotor.getComissao()).isEqualTo(UPDATED_COMISSAO);
        assertThat(testPromotor.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingPromotor() throws Exception {
        int databaseSizeBeforeUpdate = promotorRepository.findAll().size();

        // Create the Promotor

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromotorMockMvc.perform(put("/api/promotors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(promotor)))
            .andExpect(status().isBadRequest());

        // Validate the Promotor in the database
        List<Promotor> promotorList = promotorRepository.findAll();
        assertThat(promotorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePromotor() throws Exception {
        // Initialize the database
        promotorRepository.saveAndFlush(promotor);

        int databaseSizeBeforeDelete = promotorRepository.findAll().size();

        // Delete the promotor
        restPromotorMockMvc.perform(delete("/api/promotors/{id}", promotor.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Promotor> promotorList = promotorRepository.findAll();
        assertThat(promotorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
