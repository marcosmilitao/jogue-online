package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Bilhete;
import net.jogueonline.repository.BilheteRepository;

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
 * Integration tests for the {@link BilheteResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class BilheteResourceIT {

    private static final Long DEFAULT_NUMERO_BILHETE = 1L;
    private static final Long UPDATED_NUMERO_BILHETE = 2L;

    private static final Long DEFAULT_CODIGO_BANCA = 1L;
    private static final Long UPDATED_CODIGO_BANCA = 2L;

    private static final Long DEFAULT_CODIGO_LOTERIA = 1L;
    private static final Long UPDATED_CODIGO_LOTERIA = 2L;

    private static final String DEFAULT_LOTERIA_NOME = "AAAAAAAAAA";
    private static final String UPDATED_LOTERIA_NOME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_VALOR_TOTAL_APOSTA = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR_TOTAL_APOSTA = new BigDecimal(2);

    private static final Long DEFAULT_BONUS_BANCA = 1L;
    private static final Long UPDATED_BONUS_BANCA = 2L;

    private static final Long DEFAULT_BONUS_INDIVIDUAL = 1L;
    private static final Long UPDATED_BONUS_INDIVIDUAL = 2L;

    private static final Long DEFAULT_COMICAO = 1L;
    private static final Long UPDATED_COMICAO = 2L;

    private static final BigDecimal DEFAULT_VALOR_BILHETE = new BigDecimal(1);
    private static final BigDecimal UPDATED_VALOR_BILHETE = new BigDecimal(2);

    private static final Instant DEFAULT_DATA_HORA_APOSTA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_HORA_APOSTA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_QRCODE = "AAAAAAAAAA";
    private static final String UPDATED_QRCODE = "BBBBBBBBBB";

    private static final Long DEFAULT_CODIGO_TERMINAL = 1L;
    private static final Long UPDATED_CODIGO_TERMINAL = 2L;

    @Autowired
    private BilheteRepository bilheteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBilheteMockMvc;

    private Bilhete bilhete;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bilhete createEntity(EntityManager em) {
        Bilhete bilhete = new Bilhete()
            .numeroBilhete(DEFAULT_NUMERO_BILHETE)
            .codigoBanca(DEFAULT_CODIGO_BANCA)
            .codigoLoteria(DEFAULT_CODIGO_LOTERIA)
            .loteriaNome(DEFAULT_LOTERIA_NOME)
            .valorTotalAposta(DEFAULT_VALOR_TOTAL_APOSTA)
            .bonusBanca(DEFAULT_BONUS_BANCA)
            .bonusIndividual(DEFAULT_BONUS_INDIVIDUAL)
            .comicao(DEFAULT_COMICAO)
            .valorBilhete(DEFAULT_VALOR_BILHETE)
            .dataHoraAposta(DEFAULT_DATA_HORA_APOSTA)
            .qrcode(DEFAULT_QRCODE)
            .codigoTerminal(DEFAULT_CODIGO_TERMINAL);
        return bilhete;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bilhete createUpdatedEntity(EntityManager em) {
        Bilhete bilhete = new Bilhete()
            .numeroBilhete(UPDATED_NUMERO_BILHETE)
            .codigoBanca(UPDATED_CODIGO_BANCA)
            .codigoLoteria(UPDATED_CODIGO_LOTERIA)
            .loteriaNome(UPDATED_LOTERIA_NOME)
            .valorTotalAposta(UPDATED_VALOR_TOTAL_APOSTA)
            .bonusBanca(UPDATED_BONUS_BANCA)
            .bonusIndividual(UPDATED_BONUS_INDIVIDUAL)
            .comicao(UPDATED_COMICAO)
            .valorBilhete(UPDATED_VALOR_BILHETE)
            .dataHoraAposta(UPDATED_DATA_HORA_APOSTA)
            .qrcode(UPDATED_QRCODE)
            .codigoTerminal(UPDATED_CODIGO_TERMINAL);
        return bilhete;
    }

    @BeforeEach
    public void initTest() {
        bilhete = createEntity(em);
    }

    @Test
    @Transactional
    public void createBilhete() throws Exception {
        int databaseSizeBeforeCreate = bilheteRepository.findAll().size();

        // Create the Bilhete
        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isCreated());

        // Validate the Bilhete in the database
        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeCreate + 1);
        Bilhete testBilhete = bilheteList.get(bilheteList.size() - 1);
        assertThat(testBilhete.getNumeroBilhete()).isEqualTo(DEFAULT_NUMERO_BILHETE);
        assertThat(testBilhete.getCodigoBanca()).isEqualTo(DEFAULT_CODIGO_BANCA);
        assertThat(testBilhete.getCodigoLoteria()).isEqualTo(DEFAULT_CODIGO_LOTERIA);
        assertThat(testBilhete.getLoteriaNome()).isEqualTo(DEFAULT_LOTERIA_NOME);
        assertThat(testBilhete.getValorTotalAposta()).isEqualTo(DEFAULT_VALOR_TOTAL_APOSTA);
        assertThat(testBilhete.getBonusBanca()).isEqualTo(DEFAULT_BONUS_BANCA);
        assertThat(testBilhete.getBonusIndividual()).isEqualTo(DEFAULT_BONUS_INDIVIDUAL);
        assertThat(testBilhete.getComicao()).isEqualTo(DEFAULT_COMICAO);
        assertThat(testBilhete.getValorBilhete()).isEqualTo(DEFAULT_VALOR_BILHETE);
        assertThat(testBilhete.getDataHoraAposta()).isEqualTo(DEFAULT_DATA_HORA_APOSTA);
        assertThat(testBilhete.getQrcode()).isEqualTo(DEFAULT_QRCODE);
        assertThat(testBilhete.getCodigoTerminal()).isEqualTo(DEFAULT_CODIGO_TERMINAL);
    }

    @Test
    @Transactional
    public void createBilheteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bilheteRepository.findAll().size();

        // Create the Bilhete with an existing ID
        bilhete.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        // Validate the Bilhete in the database
        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNumeroBilheteIsRequired() throws Exception {
        int databaseSizeBeforeTest = bilheteRepository.findAll().size();
        // set the field null
        bilhete.setNumeroBilhete(null);

        // Create the Bilhete, which fails.

        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoBancaIsRequired() throws Exception {
        int databaseSizeBeforeTest = bilheteRepository.findAll().size();
        // set the field null
        bilhete.setCodigoBanca(null);

        // Create the Bilhete, which fails.

        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoLoteriaIsRequired() throws Exception {
        int databaseSizeBeforeTest = bilheteRepository.findAll().size();
        // set the field null
        bilhete.setCodigoLoteria(null);

        // Create the Bilhete, which fails.

        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorTotalApostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = bilheteRepository.findAll().size();
        // set the field null
        bilhete.setValorTotalAposta(null);

        // Create the Bilhete, which fails.

        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorBilheteIsRequired() throws Exception {
        int databaseSizeBeforeTest = bilheteRepository.findAll().size();
        // set the field null
        bilhete.setValorBilhete(null);

        // Create the Bilhete, which fails.

        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataHoraApostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = bilheteRepository.findAll().size();
        // set the field null
        bilhete.setDataHoraAposta(null);

        // Create the Bilhete, which fails.

        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoTerminalIsRequired() throws Exception {
        int databaseSizeBeforeTest = bilheteRepository.findAll().size();
        // set the field null
        bilhete.setCodigoTerminal(null);

        // Create the Bilhete, which fails.

        restBilheteMockMvc.perform(post("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBilhetes() throws Exception {
        // Initialize the database
        bilheteRepository.saveAndFlush(bilhete);

        // Get all the bilheteList
        restBilheteMockMvc.perform(get("/api/bilhetes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bilhete.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroBilhete").value(hasItem(DEFAULT_NUMERO_BILHETE.intValue())))
            .andExpect(jsonPath("$.[*].codigoBanca").value(hasItem(DEFAULT_CODIGO_BANCA.intValue())))
            .andExpect(jsonPath("$.[*].codigoLoteria").value(hasItem(DEFAULT_CODIGO_LOTERIA.intValue())))
            .andExpect(jsonPath("$.[*].loteriaNome").value(hasItem(DEFAULT_LOTERIA_NOME)))
            .andExpect(jsonPath("$.[*].valorTotalAposta").value(hasItem(DEFAULT_VALOR_TOTAL_APOSTA.intValue())))
            .andExpect(jsonPath("$.[*].bonusBanca").value(hasItem(DEFAULT_BONUS_BANCA.intValue())))
            .andExpect(jsonPath("$.[*].bonusIndividual").value(hasItem(DEFAULT_BONUS_INDIVIDUAL.intValue())))
            .andExpect(jsonPath("$.[*].comicao").value(hasItem(DEFAULT_COMICAO.intValue())))
            .andExpect(jsonPath("$.[*].valorBilhete").value(hasItem(DEFAULT_VALOR_BILHETE.intValue())))
            .andExpect(jsonPath("$.[*].dataHoraAposta").value(hasItem(DEFAULT_DATA_HORA_APOSTA.toString())))
            .andExpect(jsonPath("$.[*].qrcode").value(hasItem(DEFAULT_QRCODE)))
            .andExpect(jsonPath("$.[*].codigoTerminal").value(hasItem(DEFAULT_CODIGO_TERMINAL.intValue())));
    }
    
    @Test
    @Transactional
    public void getBilhete() throws Exception {
        // Initialize the database
        bilheteRepository.saveAndFlush(bilhete);

        // Get the bilhete
        restBilheteMockMvc.perform(get("/api/bilhetes/{id}", bilhete.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bilhete.getId().intValue()))
            .andExpect(jsonPath("$.numeroBilhete").value(DEFAULT_NUMERO_BILHETE.intValue()))
            .andExpect(jsonPath("$.codigoBanca").value(DEFAULT_CODIGO_BANCA.intValue()))
            .andExpect(jsonPath("$.codigoLoteria").value(DEFAULT_CODIGO_LOTERIA.intValue()))
            .andExpect(jsonPath("$.loteriaNome").value(DEFAULT_LOTERIA_NOME))
            .andExpect(jsonPath("$.valorTotalAposta").value(DEFAULT_VALOR_TOTAL_APOSTA.intValue()))
            .andExpect(jsonPath("$.bonusBanca").value(DEFAULT_BONUS_BANCA.intValue()))
            .andExpect(jsonPath("$.bonusIndividual").value(DEFAULT_BONUS_INDIVIDUAL.intValue()))
            .andExpect(jsonPath("$.comicao").value(DEFAULT_COMICAO.intValue()))
            .andExpect(jsonPath("$.valorBilhete").value(DEFAULT_VALOR_BILHETE.intValue()))
            .andExpect(jsonPath("$.dataHoraAposta").value(DEFAULT_DATA_HORA_APOSTA.toString()))
            .andExpect(jsonPath("$.qrcode").value(DEFAULT_QRCODE))
            .andExpect(jsonPath("$.codigoTerminal").value(DEFAULT_CODIGO_TERMINAL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBilhete() throws Exception {
        // Get the bilhete
        restBilheteMockMvc.perform(get("/api/bilhetes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBilhete() throws Exception {
        // Initialize the database
        bilheteRepository.saveAndFlush(bilhete);

        int databaseSizeBeforeUpdate = bilheteRepository.findAll().size();

        // Update the bilhete
        Bilhete updatedBilhete = bilheteRepository.findById(bilhete.getId()).get();
        // Disconnect from session so that the updates on updatedBilhete are not directly saved in db
        em.detach(updatedBilhete);
        updatedBilhete
            .numeroBilhete(UPDATED_NUMERO_BILHETE)
            .codigoBanca(UPDATED_CODIGO_BANCA)
            .codigoLoteria(UPDATED_CODIGO_LOTERIA)
            .loteriaNome(UPDATED_LOTERIA_NOME)
            .valorTotalAposta(UPDATED_VALOR_TOTAL_APOSTA)
            .bonusBanca(UPDATED_BONUS_BANCA)
            .bonusIndividual(UPDATED_BONUS_INDIVIDUAL)
            .comicao(UPDATED_COMICAO)
            .valorBilhete(UPDATED_VALOR_BILHETE)
            .dataHoraAposta(UPDATED_DATA_HORA_APOSTA)
            .qrcode(UPDATED_QRCODE)
            .codigoTerminal(UPDATED_CODIGO_TERMINAL);

        restBilheteMockMvc.perform(put("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBilhete)))
            .andExpect(status().isOk());

        // Validate the Bilhete in the database
        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeUpdate);
        Bilhete testBilhete = bilheteList.get(bilheteList.size() - 1);
        assertThat(testBilhete.getNumeroBilhete()).isEqualTo(UPDATED_NUMERO_BILHETE);
        assertThat(testBilhete.getCodigoBanca()).isEqualTo(UPDATED_CODIGO_BANCA);
        assertThat(testBilhete.getCodigoLoteria()).isEqualTo(UPDATED_CODIGO_LOTERIA);
        assertThat(testBilhete.getLoteriaNome()).isEqualTo(UPDATED_LOTERIA_NOME);
        assertThat(testBilhete.getValorTotalAposta()).isEqualTo(UPDATED_VALOR_TOTAL_APOSTA);
        assertThat(testBilhete.getBonusBanca()).isEqualTo(UPDATED_BONUS_BANCA);
        assertThat(testBilhete.getBonusIndividual()).isEqualTo(UPDATED_BONUS_INDIVIDUAL);
        assertThat(testBilhete.getComicao()).isEqualTo(UPDATED_COMICAO);
        assertThat(testBilhete.getValorBilhete()).isEqualTo(UPDATED_VALOR_BILHETE);
        assertThat(testBilhete.getDataHoraAposta()).isEqualTo(UPDATED_DATA_HORA_APOSTA);
        assertThat(testBilhete.getQrcode()).isEqualTo(UPDATED_QRCODE);
        assertThat(testBilhete.getCodigoTerminal()).isEqualTo(UPDATED_CODIGO_TERMINAL);
    }

    @Test
    @Transactional
    public void updateNonExistingBilhete() throws Exception {
        int databaseSizeBeforeUpdate = bilheteRepository.findAll().size();

        // Create the Bilhete

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBilheteMockMvc.perform(put("/api/bilhetes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bilhete)))
            .andExpect(status().isBadRequest());

        // Validate the Bilhete in the database
        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBilhete() throws Exception {
        // Initialize the database
        bilheteRepository.saveAndFlush(bilhete);

        int databaseSizeBeforeDelete = bilheteRepository.findAll().size();

        // Delete the bilhete
        restBilheteMockMvc.perform(delete("/api/bilhetes/{id}", bilhete.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bilhete> bilheteList = bilheteRepository.findAll();
        assertThat(bilheteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
