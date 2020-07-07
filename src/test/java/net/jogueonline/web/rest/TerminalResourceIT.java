package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Terminal;
import net.jogueonline.repository.TerminalRepository;

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
 * Integration tests for the {@link TerminalResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class TerminalResourceIT {

    private static final Long DEFAULT_TELEFONE_CHIPE = 1L;
    private static final Long UPDATED_TELEFONE_CHIPE = 2L;

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_MENSSAGEM = "AAAAAAAAAA";
    private static final String UPDATED_MENSSAGEM = "BBBBBBBBBB";

    private static final String DEFAULT_SENHA_COMUNICACAO = "AAAAAAAAAA";
    private static final String UPDATED_SENHA_COMUNICACAO = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA_INICIO = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_INICIO = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_SITUACAO = false;
    private static final Boolean UPDATED_SITUACAO = true;

    private static final String DEFAULT_VERSAO_TERMINAL = "AAAAAAAAAA";
    private static final String UPDATED_VERSAO_TERMINAL = "BBBBBBBBBB";

    private static final Long DEFAULT_MUDA_CODIGO = 1L;
    private static final Long UPDATED_MUDA_CODIGO = 2L;

    private static final Instant DEFAULT_DATA_ENTRADA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA_ENTRADA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_NUMERO_FONTE = 1L;
    private static final Long UPDATED_NUMERO_FONTE = 2L;

    private static final Long DEFAULT_CODIGO_AUTORIZACAO = 1L;
    private static final Long UPDATED_CODIGO_AUTORIZACAO = 2L;

    private static final String DEFAULT_IMEI = "AAAAAAAAAA";
    private static final String UPDATED_IMEI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_CODIGO_BANCA = 1L;
    private static final Long UPDATED_CODIGO_BANCA = 2L;

    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTerminalMockMvc;

    private Terminal terminal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Terminal createEntity(EntityManager em) {
        Terminal terminal = new Terminal()
            .telefoneChipe(DEFAULT_TELEFONE_CHIPE)
            .serial(DEFAULT_SERIAL)
            .menssagem(DEFAULT_MENSSAGEM)
            .senhaComunicacao(DEFAULT_SENHA_COMUNICACAO)
            .dataInicio(DEFAULT_DATA_INICIO)
            .situacao(DEFAULT_SITUACAO)
            .versaoTerminal(DEFAULT_VERSAO_TERMINAL)
            .mudaCodigo(DEFAULT_MUDA_CODIGO)
            .dataEntrada(DEFAULT_DATA_ENTRADA)
            .numeroFonte(DEFAULT_NUMERO_FONTE)
            .codigoAutorizacao(DEFAULT_CODIGO_AUTORIZACAO)
            .imei(DEFAULT_IMEI)
            .email(DEFAULT_EMAIL)
            .codigoBanca(DEFAULT_CODIGO_BANCA);
        return terminal;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Terminal createUpdatedEntity(EntityManager em) {
        Terminal terminal = new Terminal()
            .telefoneChipe(UPDATED_TELEFONE_CHIPE)
            .serial(UPDATED_SERIAL)
            .menssagem(UPDATED_MENSSAGEM)
            .senhaComunicacao(UPDATED_SENHA_COMUNICACAO)
            .dataInicio(UPDATED_DATA_INICIO)
            .situacao(UPDATED_SITUACAO)
            .versaoTerminal(UPDATED_VERSAO_TERMINAL)
            .mudaCodigo(UPDATED_MUDA_CODIGO)
            .dataEntrada(UPDATED_DATA_ENTRADA)
            .numeroFonte(UPDATED_NUMERO_FONTE)
            .codigoAutorizacao(UPDATED_CODIGO_AUTORIZACAO)
            .imei(UPDATED_IMEI)
            .email(UPDATED_EMAIL)
            .codigoBanca(UPDATED_CODIGO_BANCA);
        return terminal;
    }

    @BeforeEach
    public void initTest() {
        terminal = createEntity(em);
    }

    @Test
    @Transactional
    public void createTerminal() throws Exception {
        int databaseSizeBeforeCreate = terminalRepository.findAll().size();

        // Create the Terminal
        restTerminalMockMvc.perform(post("/api/terminals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(terminal)))
            .andExpect(status().isCreated());

        // Validate the Terminal in the database
        List<Terminal> terminalList = terminalRepository.findAll();
        assertThat(terminalList).hasSize(databaseSizeBeforeCreate + 1);
        Terminal testTerminal = terminalList.get(terminalList.size() - 1);
        assertThat(testTerminal.getTelefoneChipe()).isEqualTo(DEFAULT_TELEFONE_CHIPE);
        assertThat(testTerminal.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testTerminal.getMenssagem()).isEqualTo(DEFAULT_MENSSAGEM);
        assertThat(testTerminal.getSenhaComunicacao()).isEqualTo(DEFAULT_SENHA_COMUNICACAO);
        assertThat(testTerminal.getDataInicio()).isEqualTo(DEFAULT_DATA_INICIO);
        assertThat(testTerminal.isSituacao()).isEqualTo(DEFAULT_SITUACAO);
        assertThat(testTerminal.getVersaoTerminal()).isEqualTo(DEFAULT_VERSAO_TERMINAL);
        assertThat(testTerminal.getMudaCodigo()).isEqualTo(DEFAULT_MUDA_CODIGO);
        assertThat(testTerminal.getDataEntrada()).isEqualTo(DEFAULT_DATA_ENTRADA);
        assertThat(testTerminal.getNumeroFonte()).isEqualTo(DEFAULT_NUMERO_FONTE);
        assertThat(testTerminal.getCodigoAutorizacao()).isEqualTo(DEFAULT_CODIGO_AUTORIZACAO);
        assertThat(testTerminal.getImei()).isEqualTo(DEFAULT_IMEI);
        assertThat(testTerminal.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testTerminal.getCodigoBanca()).isEqualTo(DEFAULT_CODIGO_BANCA);
    }

    @Test
    @Transactional
    public void createTerminalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = terminalRepository.findAll().size();

        // Create the Terminal with an existing ID
        terminal.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTerminalMockMvc.perform(post("/api/terminals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(terminal)))
            .andExpect(status().isBadRequest());

        // Validate the Terminal in the database
        List<Terminal> terminalList = terminalRepository.findAll();
        assertThat(terminalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkSerialIsRequired() throws Exception {
        int databaseSizeBeforeTest = terminalRepository.findAll().size();
        // set the field null
        terminal.setSerial(null);

        // Create the Terminal, which fails.

        restTerminalMockMvc.perform(post("/api/terminals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(terminal)))
            .andExpect(status().isBadRequest());

        List<Terminal> terminalList = terminalRepository.findAll();
        assertThat(terminalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkImeiIsRequired() throws Exception {
        int databaseSizeBeforeTest = terminalRepository.findAll().size();
        // set the field null
        terminal.setImei(null);

        // Create the Terminal, which fails.

        restTerminalMockMvc.perform(post("/api/terminals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(terminal)))
            .andExpect(status().isBadRequest());

        List<Terminal> terminalList = terminalRepository.findAll();
        assertThat(terminalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTerminals() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

        // Get all the terminalList
        restTerminalMockMvc.perform(get("/api/terminals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(terminal.getId().intValue())))
            .andExpect(jsonPath("$.[*].telefoneChipe").value(hasItem(DEFAULT_TELEFONE_CHIPE.intValue())))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL)))
            .andExpect(jsonPath("$.[*].menssagem").value(hasItem(DEFAULT_MENSSAGEM)))
            .andExpect(jsonPath("$.[*].senhaComunicacao").value(hasItem(DEFAULT_SENHA_COMUNICACAO)))
            .andExpect(jsonPath("$.[*].dataInicio").value(hasItem(DEFAULT_DATA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.booleanValue())))
            .andExpect(jsonPath("$.[*].versaoTerminal").value(hasItem(DEFAULT_VERSAO_TERMINAL)))
            .andExpect(jsonPath("$.[*].mudaCodigo").value(hasItem(DEFAULT_MUDA_CODIGO.intValue())))
            .andExpect(jsonPath("$.[*].dataEntrada").value(hasItem(DEFAULT_DATA_ENTRADA.toString())))
            .andExpect(jsonPath("$.[*].numeroFonte").value(hasItem(DEFAULT_NUMERO_FONTE.intValue())))
            .andExpect(jsonPath("$.[*].codigoAutorizacao").value(hasItem(DEFAULT_CODIGO_AUTORIZACAO.intValue())))
            .andExpect(jsonPath("$.[*].imei").value(hasItem(DEFAULT_IMEI)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].codigoBanca").value(hasItem(DEFAULT_CODIGO_BANCA.intValue())));
    }
    
    @Test
    @Transactional
    public void getTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

        // Get the terminal
        restTerminalMockMvc.perform(get("/api/terminals/{id}", terminal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(terminal.getId().intValue()))
            .andExpect(jsonPath("$.telefoneChipe").value(DEFAULT_TELEFONE_CHIPE.intValue()))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL))
            .andExpect(jsonPath("$.menssagem").value(DEFAULT_MENSSAGEM))
            .andExpect(jsonPath("$.senhaComunicacao").value(DEFAULT_SENHA_COMUNICACAO))
            .andExpect(jsonPath("$.dataInicio").value(DEFAULT_DATA_INICIO.toString()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.booleanValue()))
            .andExpect(jsonPath("$.versaoTerminal").value(DEFAULT_VERSAO_TERMINAL))
            .andExpect(jsonPath("$.mudaCodigo").value(DEFAULT_MUDA_CODIGO.intValue()))
            .andExpect(jsonPath("$.dataEntrada").value(DEFAULT_DATA_ENTRADA.toString()))
            .andExpect(jsonPath("$.numeroFonte").value(DEFAULT_NUMERO_FONTE.intValue()))
            .andExpect(jsonPath("$.codigoAutorizacao").value(DEFAULT_CODIGO_AUTORIZACAO.intValue()))
            .andExpect(jsonPath("$.imei").value(DEFAULT_IMEI))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.codigoBanca").value(DEFAULT_CODIGO_BANCA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTerminal() throws Exception {
        // Get the terminal
        restTerminalMockMvc.perform(get("/api/terminals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

        int databaseSizeBeforeUpdate = terminalRepository.findAll().size();

        // Update the terminal
        Terminal updatedTerminal = terminalRepository.findById(terminal.getId()).get();
        // Disconnect from session so that the updates on updatedTerminal are not directly saved in db
        em.detach(updatedTerminal);
        updatedTerminal
            .telefoneChipe(UPDATED_TELEFONE_CHIPE)
            .serial(UPDATED_SERIAL)
            .menssagem(UPDATED_MENSSAGEM)
            .senhaComunicacao(UPDATED_SENHA_COMUNICACAO)
            .dataInicio(UPDATED_DATA_INICIO)
            .situacao(UPDATED_SITUACAO)
            .versaoTerminal(UPDATED_VERSAO_TERMINAL)
            .mudaCodigo(UPDATED_MUDA_CODIGO)
            .dataEntrada(UPDATED_DATA_ENTRADA)
            .numeroFonte(UPDATED_NUMERO_FONTE)
            .codigoAutorizacao(UPDATED_CODIGO_AUTORIZACAO)
            .imei(UPDATED_IMEI)
            .email(UPDATED_EMAIL)
            .codigoBanca(UPDATED_CODIGO_BANCA);

        restTerminalMockMvc.perform(put("/api/terminals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTerminal)))
            .andExpect(status().isOk());

        // Validate the Terminal in the database
        List<Terminal> terminalList = terminalRepository.findAll();
        assertThat(terminalList).hasSize(databaseSizeBeforeUpdate);
        Terminal testTerminal = terminalList.get(terminalList.size() - 1);
        assertThat(testTerminal.getTelefoneChipe()).isEqualTo(UPDATED_TELEFONE_CHIPE);
        assertThat(testTerminal.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testTerminal.getMenssagem()).isEqualTo(UPDATED_MENSSAGEM);
        assertThat(testTerminal.getSenhaComunicacao()).isEqualTo(UPDATED_SENHA_COMUNICACAO);
        assertThat(testTerminal.getDataInicio()).isEqualTo(UPDATED_DATA_INICIO);
        assertThat(testTerminal.isSituacao()).isEqualTo(UPDATED_SITUACAO);
        assertThat(testTerminal.getVersaoTerminal()).isEqualTo(UPDATED_VERSAO_TERMINAL);
        assertThat(testTerminal.getMudaCodigo()).isEqualTo(UPDATED_MUDA_CODIGO);
        assertThat(testTerminal.getDataEntrada()).isEqualTo(UPDATED_DATA_ENTRADA);
        assertThat(testTerminal.getNumeroFonte()).isEqualTo(UPDATED_NUMERO_FONTE);
        assertThat(testTerminal.getCodigoAutorizacao()).isEqualTo(UPDATED_CODIGO_AUTORIZACAO);
        assertThat(testTerminal.getImei()).isEqualTo(UPDATED_IMEI);
        assertThat(testTerminal.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testTerminal.getCodigoBanca()).isEqualTo(UPDATED_CODIGO_BANCA);
    }

    @Test
    @Transactional
    public void updateNonExistingTerminal() throws Exception {
        int databaseSizeBeforeUpdate = terminalRepository.findAll().size();

        // Create the Terminal

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTerminalMockMvc.perform(put("/api/terminals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(terminal)))
            .andExpect(status().isBadRequest());

        // Validate the Terminal in the database
        List<Terminal> terminalList = terminalRepository.findAll();
        assertThat(terminalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

        int databaseSizeBeforeDelete = terminalRepository.findAll().size();

        // Delete the terminal
        restTerminalMockMvc.perform(delete("/api/terminals/{id}", terminal.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Terminal> terminalList = terminalRepository.findAll();
        assertThat(terminalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
