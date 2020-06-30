package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Aposta;
import net.jogueonline.repository.ApostaRepository;

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
 * Integration tests for the {@link ApostaResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ApostaResourceIT {

    private static final Long DEFAULT_CODIGO_JOGO = 1L;
    private static final Long UPDATED_CODIGO_JOGO = 2L;

    private static final Instant DEFAULT_DATA_APOSTA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_APOSTA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LOTERIA_NOME = "AAAAAAAAAA";
    private static final String UPDATED_LOTERIA_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOTERIA_CODIGO = 1;
    private static final Integer UPDATED_LOTERIA_CODIGO = 2;

    private static final String DEFAULT_MODALIDE = "AAAAAAAAAA";
    private static final String UPDATED_MODALIDE = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_MODALIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_MODALIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_PREMIO = "AAAAAAAAAA";
    private static final String UPDATED_PREMIO = "BBBBBBBBBB";

    private static final Long DEFAULT_CODIGO_PREMIO = 1L;
    private static final Long UPDATED_CODIGO_PREMIO = 2L;

    private static final BigDecimal DEFAULT_VALOR_JOGO = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR_JOGO = new BigDecimal(2);

    private static final Long DEFAULT_CODIGO_BANCA = 1L;
    private static final Long UPDATED_CODIGO_BANCA = 2L;

    private static final Long DEFAULT_NUMERO_APOSTA = 1L;
    private static final Long UPDATED_NUMERO_APOSTA = 2L;

    @Autowired
    private ApostaRepository apostaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restApostaMockMvc;

    private Aposta aposta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aposta createEntity(EntityManager em) {
        Aposta aposta = new Aposta()
            .codigoJogo(DEFAULT_CODIGO_JOGO)
            .dataAposta(DEFAULT_DATA_APOSTA)
            .loteriaNome(DEFAULT_LOTERIA_NOME)
            .loteriaCodigo(DEFAULT_LOTERIA_CODIGO)
            .modalide(DEFAULT_MODALIDE)
            .codigoModalidade(DEFAULT_CODIGO_MODALIDADE)
            .premio(DEFAULT_PREMIO)
            .codigoPremio(DEFAULT_CODIGO_PREMIO)
            .valorJogo(DEFAULT_VALOR_JOGO)
            .codigoBanca(DEFAULT_CODIGO_BANCA)
            .numeroAposta(DEFAULT_NUMERO_APOSTA);
        return aposta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Aposta createUpdatedEntity(EntityManager em) {
        Aposta aposta = new Aposta()
            .codigoJogo(UPDATED_CODIGO_JOGO)
            .dataAposta(UPDATED_DATA_APOSTA)
            .loteriaNome(UPDATED_LOTERIA_NOME)
            .loteriaCodigo(UPDATED_LOTERIA_CODIGO)
            .modalide(UPDATED_MODALIDE)
            .codigoModalidade(UPDATED_CODIGO_MODALIDADE)
            .premio(UPDATED_PREMIO)
            .codigoPremio(UPDATED_CODIGO_PREMIO)
            .valorJogo(UPDATED_VALOR_JOGO)
            .codigoBanca(UPDATED_CODIGO_BANCA)
            .numeroAposta(UPDATED_NUMERO_APOSTA);
        return aposta;
    }

    @BeforeEach
    public void initTest() {
        aposta = createEntity(em);
    }

    @Test
    @Transactional
    public void createAposta() throws Exception {
        int databaseSizeBeforeCreate = apostaRepository.findAll().size();

        // Create the Aposta
        restApostaMockMvc.perform(post("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isCreated());

        // Validate the Aposta in the database
        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeCreate + 1);
        Aposta testAposta = apostaList.get(apostaList.size() - 1);
        assertThat(testAposta.getCodigoJogo()).isEqualTo(DEFAULT_CODIGO_JOGO);
        assertThat(testAposta.getDataAposta()).isEqualTo(DEFAULT_DATA_APOSTA);
        assertThat(testAposta.getLoteriaNome()).isEqualTo(DEFAULT_LOTERIA_NOME);
        assertThat(testAposta.getLoteriaCodigo()).isEqualTo(DEFAULT_LOTERIA_CODIGO);
        assertThat(testAposta.getModalide()).isEqualTo(DEFAULT_MODALIDE);
        assertThat(testAposta.getCodigoModalidade()).isEqualTo(DEFAULT_CODIGO_MODALIDADE);
        assertThat(testAposta.getPremio()).isEqualTo(DEFAULT_PREMIO);
        assertThat(testAposta.getCodigoPremio()).isEqualTo(DEFAULT_CODIGO_PREMIO);
        assertThat(testAposta.getValorJogo()).isEqualTo(DEFAULT_VALOR_JOGO);
        assertThat(testAposta.getCodigoBanca()).isEqualTo(DEFAULT_CODIGO_BANCA);
        assertThat(testAposta.getNumeroAposta()).isEqualTo(DEFAULT_NUMERO_APOSTA);
    }

    @Test
    @Transactional
    public void createApostaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apostaRepository.findAll().size();

        // Create the Aposta with an existing ID
        aposta.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApostaMockMvc.perform(post("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isBadRequest());

        // Validate the Aposta in the database
        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodigoJogoIsRequired() throws Exception {
        int databaseSizeBeforeTest = apostaRepository.findAll().size();
        // set the field null
        aposta.setCodigoJogo(null);

        // Create the Aposta, which fails.

        restApostaMockMvc.perform(post("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isBadRequest());

        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataApostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = apostaRepository.findAll().size();
        // set the field null
        aposta.setDataAposta(null);

        // Create the Aposta, which fails.

        restApostaMockMvc.perform(post("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isBadRequest());

        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLoteriaCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = apostaRepository.findAll().size();
        // set the field null
        aposta.setLoteriaCodigo(null);

        // Create the Aposta, which fails.

        restApostaMockMvc.perform(post("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isBadRequest());

        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoModalidadeIsRequired() throws Exception {
        int databaseSizeBeforeTest = apostaRepository.findAll().size();
        // set the field null
        aposta.setCodigoModalidade(null);

        // Create the Aposta, which fails.

        restApostaMockMvc.perform(post("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isBadRequest());

        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoPremioIsRequired() throws Exception {
        int databaseSizeBeforeTest = apostaRepository.findAll().size();
        // set the field null
        aposta.setCodigoPremio(null);

        // Create the Aposta, which fails.

        restApostaMockMvc.perform(post("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isBadRequest());

        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroApostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = apostaRepository.findAll().size();
        // set the field null
        aposta.setNumeroAposta(null);

        // Create the Aposta, which fails.

        restApostaMockMvc.perform(post("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isBadRequest());

        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllApostas() throws Exception {
        // Initialize the database
        apostaRepository.saveAndFlush(aposta);

        // Get all the apostaList
        restApostaMockMvc.perform(get("/api/apostas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aposta.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoJogo").value(hasItem(DEFAULT_CODIGO_JOGO.intValue())))
            .andExpect(jsonPath("$.[*].dataAposta").value(hasItem(DEFAULT_DATA_APOSTA.toString())))
            .andExpect(jsonPath("$.[*].loteriaNome").value(hasItem(DEFAULT_LOTERIA_NOME)))
            .andExpect(jsonPath("$.[*].loteriaCodigo").value(hasItem(DEFAULT_LOTERIA_CODIGO)))
            .andExpect(jsonPath("$.[*].modalide").value(hasItem(DEFAULT_MODALIDE)))
            .andExpect(jsonPath("$.[*].codigoModalidade").value(hasItem(DEFAULT_CODIGO_MODALIDADE)))
            .andExpect(jsonPath("$.[*].premio").value(hasItem(DEFAULT_PREMIO)))
            .andExpect(jsonPath("$.[*].codigoPremio").value(hasItem(DEFAULT_CODIGO_PREMIO.intValue())))
            .andExpect(jsonPath("$.[*].valorJogo").value(hasItem(DEFAULT_VALOR_JOGO.intValue())))
            .andExpect(jsonPath("$.[*].codigoBanca").value(hasItem(DEFAULT_CODIGO_BANCA.intValue())))
            .andExpect(jsonPath("$.[*].numeroAposta").value(hasItem(DEFAULT_NUMERO_APOSTA.intValue())));
    }
    
    @Test
    @Transactional
    public void getAposta() throws Exception {
        // Initialize the database
        apostaRepository.saveAndFlush(aposta);

        // Get the aposta
        restApostaMockMvc.perform(get("/api/apostas/{id}", aposta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(aposta.getId().intValue()))
            .andExpect(jsonPath("$.codigoJogo").value(DEFAULT_CODIGO_JOGO.intValue()))
            .andExpect(jsonPath("$.dataAposta").value(DEFAULT_DATA_APOSTA.toString()))
            .andExpect(jsonPath("$.loteriaNome").value(DEFAULT_LOTERIA_NOME))
            .andExpect(jsonPath("$.loteriaCodigo").value(DEFAULT_LOTERIA_CODIGO))
            .andExpect(jsonPath("$.modalide").value(DEFAULT_MODALIDE))
            .andExpect(jsonPath("$.codigoModalidade").value(DEFAULT_CODIGO_MODALIDADE))
            .andExpect(jsonPath("$.premio").value(DEFAULT_PREMIO))
            .andExpect(jsonPath("$.codigoPremio").value(DEFAULT_CODIGO_PREMIO.intValue()))
            .andExpect(jsonPath("$.valorJogo").value(DEFAULT_VALOR_JOGO.intValue()))
            .andExpect(jsonPath("$.codigoBanca").value(DEFAULT_CODIGO_BANCA.intValue()))
            .andExpect(jsonPath("$.numeroAposta").value(DEFAULT_NUMERO_APOSTA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAposta() throws Exception {
        // Get the aposta
        restApostaMockMvc.perform(get("/api/apostas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAposta() throws Exception {
        // Initialize the database
        apostaRepository.saveAndFlush(aposta);

        int databaseSizeBeforeUpdate = apostaRepository.findAll().size();

        // Update the aposta
        Aposta updatedAposta = apostaRepository.findById(aposta.getId()).get();
        // Disconnect from session so that the updates on updatedAposta are not directly saved in db
        em.detach(updatedAposta);
        updatedAposta
            .codigoJogo(UPDATED_CODIGO_JOGO)
            .dataAposta(UPDATED_DATA_APOSTA)
            .loteriaNome(UPDATED_LOTERIA_NOME)
            .loteriaCodigo(UPDATED_LOTERIA_CODIGO)
            .modalide(UPDATED_MODALIDE)
            .codigoModalidade(UPDATED_CODIGO_MODALIDADE)
            .premio(UPDATED_PREMIO)
            .codigoPremio(UPDATED_CODIGO_PREMIO)
            .valorJogo(UPDATED_VALOR_JOGO)
            .codigoBanca(UPDATED_CODIGO_BANCA)
            .numeroAposta(UPDATED_NUMERO_APOSTA);

        restApostaMockMvc.perform(put("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAposta)))
            .andExpect(status().isOk());

        // Validate the Aposta in the database
        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeUpdate);
        Aposta testAposta = apostaList.get(apostaList.size() - 1);
        assertThat(testAposta.getCodigoJogo()).isEqualTo(UPDATED_CODIGO_JOGO);
        assertThat(testAposta.getDataAposta()).isEqualTo(UPDATED_DATA_APOSTA);
        assertThat(testAposta.getLoteriaNome()).isEqualTo(UPDATED_LOTERIA_NOME);
        assertThat(testAposta.getLoteriaCodigo()).isEqualTo(UPDATED_LOTERIA_CODIGO);
        assertThat(testAposta.getModalide()).isEqualTo(UPDATED_MODALIDE);
        assertThat(testAposta.getCodigoModalidade()).isEqualTo(UPDATED_CODIGO_MODALIDADE);
        assertThat(testAposta.getPremio()).isEqualTo(UPDATED_PREMIO);
        assertThat(testAposta.getCodigoPremio()).isEqualTo(UPDATED_CODIGO_PREMIO);
        assertThat(testAposta.getValorJogo()).isEqualTo(UPDATED_VALOR_JOGO);
        assertThat(testAposta.getCodigoBanca()).isEqualTo(UPDATED_CODIGO_BANCA);
        assertThat(testAposta.getNumeroAposta()).isEqualTo(UPDATED_NUMERO_APOSTA);
    }

    @Test
    @Transactional
    public void updateNonExistingAposta() throws Exception {
        int databaseSizeBeforeUpdate = apostaRepository.findAll().size();

        // Create the Aposta

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApostaMockMvc.perform(put("/api/apostas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(aposta)))
            .andExpect(status().isBadRequest());

        // Validate the Aposta in the database
        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAposta() throws Exception {
        // Initialize the database
        apostaRepository.saveAndFlush(aposta);

        int databaseSizeBeforeDelete = apostaRepository.findAll().size();

        // Delete the aposta
        restApostaMockMvc.perform(delete("/api/apostas/{id}", aposta.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Aposta> apostaList = apostaRepository.findAll();
        assertThat(apostaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
