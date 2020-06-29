package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Revendedor;
import net.jogueonline.repository.RevendedorRepository;

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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RevendedorResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class RevendedorResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_COLETOR = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_COLETOR = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL_COLETOR = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_COLETOR = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_COMERCIAL = "AAAAAAAAAA";
    private static final String UPDATED_NOME_COMERCIAL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SITUACAO = false;
    private static final Boolean UPDATED_SITUACAO = true;

    private static final BigDecimal DEFAULT_SALDO = new BigDecimal(1);
    private static final BigDecimal UPDATED_SALDO = new BigDecimal(2);

    private static final String DEFAULT_SENHA = "AAAAAAAAAA";
    private static final String UPDATED_SENHA = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private RevendedorRepository revendedorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRevendedorMockMvc;

    private Revendedor revendedor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Revendedor createEntity(EntityManager em) {
        Revendedor revendedor = new Revendedor()
            .nome(DEFAULT_NOME)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .telefone(DEFAULT_TELEFONE)
            .tipo(DEFAULT_TIPO)
            .tipoColetor(DEFAULT_TIPO_COLETOR)
            .serialColetor(DEFAULT_SERIAL_COLETOR)
            .nomeComercial(DEFAULT_NOME_COMERCIAL)
            .situacao(DEFAULT_SITUACAO)
            .saldo(DEFAULT_SALDO)
            .senha(DEFAULT_SENHA)
            .data(DEFAULT_DATA);
        return revendedor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Revendedor createUpdatedEntity(EntityManager em) {
        Revendedor revendedor = new Revendedor()
            .nome(UPDATED_NOME)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefone(UPDATED_TELEFONE)
            .tipo(UPDATED_TIPO)
            .tipoColetor(UPDATED_TIPO_COLETOR)
            .serialColetor(UPDATED_SERIAL_COLETOR)
            .nomeComercial(UPDATED_NOME_COMERCIAL)
            .situacao(UPDATED_SITUACAO)
            .saldo(UPDATED_SALDO)
            .senha(UPDATED_SENHA)
            .data(UPDATED_DATA);
        return revendedor;
    }

    @BeforeEach
    public void initTest() {
        revendedor = createEntity(em);
    }

    @Test
    @Transactional
    public void createRevendedor() throws Exception {
        int databaseSizeBeforeCreate = revendedorRepository.findAll().size();

        // Create the Revendedor
        restRevendedorMockMvc.perform(post("/api/revendedors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(revendedor)))
            .andExpect(status().isCreated());

        // Validate the Revendedor in the database
        List<Revendedor> revendedorList = revendedorRepository.findAll();
        assertThat(revendedorList).hasSize(databaseSizeBeforeCreate + 1);
        Revendedor testRevendedor = revendedorList.get(revendedorList.size() - 1);
        assertThat(testRevendedor.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testRevendedor.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testRevendedor.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testRevendedor.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testRevendedor.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testRevendedor.getTipoColetor()).isEqualTo(DEFAULT_TIPO_COLETOR);
        assertThat(testRevendedor.getSerialColetor()).isEqualTo(DEFAULT_SERIAL_COLETOR);
        assertThat(testRevendedor.getNomeComercial()).isEqualTo(DEFAULT_NOME_COMERCIAL);
        assertThat(testRevendedor.isSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testRevendedor.getSaldo()).isEqualTo(DEFAULT_SALDO);
        assertThat(testRevendedor.getSenha()).isEqualTo(DEFAULT_SENHA);
        assertThat(testRevendedor.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createRevendedorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = revendedorRepository.findAll().size();

        // Create the Revendedor with an existing ID
        revendedor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRevendedorMockMvc.perform(post("/api/revendedors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(revendedor)))
            .andExpect(status().isBadRequest());

        // Validate the Revendedor in the database
        List<Revendedor> revendedorList = revendedorRepository.findAll();
        assertThat(revendedorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = revendedorRepository.findAll().size();
        // set the field null
        revendedor.setNome(null);

        // Create the Revendedor, which fails.

        restRevendedorMockMvc.perform(post("/api/revendedors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(revendedor)))
            .andExpect(status().isBadRequest());

        List<Revendedor> revendedorList = revendedorRepository.findAll();
        assertThat(revendedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelefoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = revendedorRepository.findAll().size();
        // set the field null
        revendedor.setTelefone(null);

        // Create the Revendedor, which fails.

        restRevendedorMockMvc.perform(post("/api/revendedors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(revendedor)))
            .andExpect(status().isBadRequest());

        List<Revendedor> revendedorList = revendedorRepository.findAll();
        assertThat(revendedorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRevendedors() throws Exception {
        // Initialize the database
        revendedorRepository.saveAndFlush(revendedor);

        // Get all the revendedorList
        restRevendedorMockMvc.perform(get("/api/revendedors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(revendedor.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)))
            .andExpect(jsonPath("$.[*].tipoColetor").value(hasItem(DEFAULT_TIPO_COLETOR)))
            .andExpect(jsonPath("$.[*].serialColetor").value(hasItem(DEFAULT_SERIAL_COLETOR)))
            .andExpect(jsonPath("$.[*].nomeComercial").value(hasItem(DEFAULT_NOME_COMERCIAL)))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.booleanValue())))
            .andExpect(jsonPath("$.[*].saldo").value(hasItem(DEFAULT_SALDO.intValue())))
            .andExpect(jsonPath("$.[*].senha").value(hasItem(DEFAULT_SENHA)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getRevendedor() throws Exception {
        // Initialize the database
        revendedorRepository.saveAndFlush(revendedor);

        // Get the revendedor
        restRevendedorMockMvc.perform(get("/api/revendedors/{id}", revendedor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(revendedor.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO))
            .andExpect(jsonPath("$.tipoColetor").value(DEFAULT_TIPO_COLETOR))
            .andExpect(jsonPath("$.serialColetor").value(DEFAULT_SERIAL_COLETOR))
            .andExpect(jsonPath("$.nomeComercial").value(DEFAULT_NOME_COMERCIAL))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.booleanValue()))
            .andExpect(jsonPath("$.saldo").value(DEFAULT_SALDO.intValue()))
            .andExpect(jsonPath("$.senha").value(DEFAULT_SENHA))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRevendedor() throws Exception {
        // Get the revendedor
        restRevendedorMockMvc.perform(get("/api/revendedors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRevendedor() throws Exception {
        // Initialize the database
        revendedorRepository.saveAndFlush(revendedor);

        int databaseSizeBeforeUpdate = revendedorRepository.findAll().size();

        // Update the revendedor
        Revendedor updatedRevendedor = revendedorRepository.findById(revendedor.getId()).get();
        // Disconnect from session so that the updates on updatedRevendedor are not directly saved in db
        em.detach(updatedRevendedor);
        updatedRevendedor
            .nome(UPDATED_NOME)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .telefone(UPDATED_TELEFONE)
            .tipo(UPDATED_TIPO)
            .tipoColetor(UPDATED_TIPO_COLETOR)
            .serialColetor(UPDATED_SERIAL_COLETOR)
            .nomeComercial(UPDATED_NOME_COMERCIAL)
            .situacao(UPDATED_SITUACAO)
            .saldo(UPDATED_SALDO)
            .senha(UPDATED_SENHA)
            .data(UPDATED_DATA);

        restRevendedorMockMvc.perform(put("/api/revendedors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRevendedor)))
            .andExpect(status().isOk());

        // Validate the Revendedor in the database
        List<Revendedor> revendedorList = revendedorRepository.findAll();
        assertThat(revendedorList).hasSize(databaseSizeBeforeUpdate);
        Revendedor testRevendedor = revendedorList.get(revendedorList.size() - 1);
        assertThat(testRevendedor.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testRevendedor.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testRevendedor.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testRevendedor.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testRevendedor.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testRevendedor.getTipoColetor()).isEqualTo(UPDATED_TIPO_COLETOR);
        assertThat(testRevendedor.getSerialColetor()).isEqualTo(UPDATED_SERIAL_COLETOR);
        assertThat(testRevendedor.getNomeComercial()).isEqualTo(UPDATED_NOME_COMERCIAL);
        assertThat(testRevendedor.isSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testRevendedor.getSaldo()).isEqualTo(UPDATED_SALDO);
        assertThat(testRevendedor.getSenha()).isEqualTo(UPDATED_SENHA);
        assertThat(testRevendedor.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingRevendedor() throws Exception {
        int databaseSizeBeforeUpdate = revendedorRepository.findAll().size();

        // Create the Revendedor

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRevendedorMockMvc.perform(put("/api/revendedors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(revendedor)))
            .andExpect(status().isBadRequest());

        // Validate the Revendedor in the database
        List<Revendedor> revendedorList = revendedorRepository.findAll();
        assertThat(revendedorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRevendedor() throws Exception {
        // Initialize the database
        revendedorRepository.saveAndFlush(revendedor);

        int databaseSizeBeforeDelete = revendedorRepository.findAll().size();

        // Delete the revendedor
        restRevendedorMockMvc.perform(delete("/api/revendedors/{id}", revendedor.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Revendedor> revendedorList = revendedorRepository.findAll();
        assertThat(revendedorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
