package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Banca;
import net.jogueonline.repository.BancaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BancaResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class BancaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LIMITE_DESCARGA = new BigDecimal(1);
    private static final BigDecimal UPDATED_LIMITE_DESCARGA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LIMITE_PREMIACAO = new BigDecimal(1);
    private static final BigDecimal UPDATED_LIMITE_PREMIACAO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LIMITE_BAIXA_AUTOMATICA = new BigDecimal(1);
    private static final BigDecimal UPDATED_LIMITE_BAIXA_AUTOMATICA = new BigDecimal(2);

    private static final Long DEFAULT_LIMITE_HORARIO_ENCERRAMENTO = 1L;
    private static final Long UPDATED_LIMITE_HORARIO_ENCERRAMENTO = 2L;

    private static final String DEFAULT_MENSAGEM_PULE_1 = "AAAAAAAAAA";
    private static final String UPDATED_MENSAGEM_PULE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_MENSAGEM_PULE_2 = "AAAAAAAAAA";
    private static final String UPDATED_MENSAGEM_PULE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_MENSAGEM_PULE_3 = "AAAAAAAAAA";
    private static final String UPDATED_MENSAGEM_PULE_3 = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_BONUS = 1L;
    private static final Long UPDATED_BONUS = 2L;

    @Autowired
    private BancaRepository bancaRepository;

    @Mock
    private BancaRepository bancaRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBancaMockMvc;

    private Banca banca;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Banca createEntity(EntityManager em) {
        Banca banca = new Banca()
            .nome(DEFAULT_NOME)
            .cidade(DEFAULT_CIDADE)
            .telefone(DEFAULT_TELEFONE)
            .estado(DEFAULT_ESTADO)
            .limiteDescarga(DEFAULT_LIMITE_DESCARGA)
            .limitePremiacao(DEFAULT_LIMITE_PREMIACAO)
            .limiteBaixaAutomatica(DEFAULT_LIMITE_BAIXA_AUTOMATICA)
            .limiteHorarioEncerramento(DEFAULT_LIMITE_HORARIO_ENCERRAMENTO)
            .mensagemPule1(DEFAULT_MENSAGEM_PULE_1)
            .mensagemPule2(DEFAULT_MENSAGEM_PULE_2)
            .mensagemPule3(DEFAULT_MENSAGEM_PULE_3)
            .data(DEFAULT_DATA)
            .bonus(DEFAULT_BONUS);
        return banca;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Banca createUpdatedEntity(EntityManager em) {
        Banca banca = new Banca()
            .nome(UPDATED_NOME)
            .cidade(UPDATED_CIDADE)
            .telefone(UPDATED_TELEFONE)
            .estado(UPDATED_ESTADO)
            .limiteDescarga(UPDATED_LIMITE_DESCARGA)
            .limitePremiacao(UPDATED_LIMITE_PREMIACAO)
            .limiteBaixaAutomatica(UPDATED_LIMITE_BAIXA_AUTOMATICA)
            .limiteHorarioEncerramento(UPDATED_LIMITE_HORARIO_ENCERRAMENTO)
            .mensagemPule1(UPDATED_MENSAGEM_PULE_1)
            .mensagemPule2(UPDATED_MENSAGEM_PULE_2)
            .mensagemPule3(UPDATED_MENSAGEM_PULE_3)
            .data(UPDATED_DATA)
            .bonus(UPDATED_BONUS);
        return banca;
    }

    @BeforeEach
    public void initTest() {
        banca = createEntity(em);
    }

    @Test
    @Transactional
    public void createBanca() throws Exception {
        int databaseSizeBeforeCreate = bancaRepository.findAll().size();

        // Create the Banca
        restBancaMockMvc.perform(post("/api/bancas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banca)))
            .andExpect(status().isCreated());

        // Validate the Banca in the database
        List<Banca> bancaList = bancaRepository.findAll();
        assertThat(bancaList).hasSize(databaseSizeBeforeCreate + 1);
        Banca testBanca = bancaList.get(bancaList.size() - 1);
        assertThat(testBanca.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testBanca.getCidade()).isEqualTo(DEFAULT_CIDADE);
        assertThat(testBanca.getTelefone()).isEqualTo(DEFAULT_TELEFONE);
        assertThat(testBanca.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testBanca.getLimiteDescarga()).isEqualTo(DEFAULT_LIMITE_DESCARGA);
        assertThat(testBanca.getLimitePremiacao()).isEqualTo(DEFAULT_LIMITE_PREMIACAO);
        assertThat(testBanca.getLimiteBaixaAutomatica()).isEqualTo(DEFAULT_LIMITE_BAIXA_AUTOMATICA);
        assertThat(testBanca.getLimiteHorarioEncerramento()).isEqualTo(DEFAULT_LIMITE_HORARIO_ENCERRAMENTO);
        assertThat(testBanca.getMensagemPule1()).isEqualTo(DEFAULT_MENSAGEM_PULE_1);
        assertThat(testBanca.getMensagemPule2()).isEqualTo(DEFAULT_MENSAGEM_PULE_2);
        assertThat(testBanca.getMensagemPule3()).isEqualTo(DEFAULT_MENSAGEM_PULE_3);
        assertThat(testBanca.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testBanca.getBonus()).isEqualTo(DEFAULT_BONUS);
    }

    @Test
    @Transactional
    public void createBancaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bancaRepository.findAll().size();

        // Create the Banca with an existing ID
        banca.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBancaMockMvc.perform(post("/api/bancas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banca)))
            .andExpect(status().isBadRequest());

        // Validate the Banca in the database
        List<Banca> bancaList = bancaRepository.findAll();
        assertThat(bancaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bancaRepository.findAll().size();
        // set the field null
        banca.setNome(null);

        // Create the Banca, which fails.

        restBancaMockMvc.perform(post("/api/bancas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banca)))
            .andExpect(status().isBadRequest());

        List<Banca> bancaList = bancaRepository.findAll();
        assertThat(bancaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBancas() throws Exception {
        // Initialize the database
        bancaRepository.saveAndFlush(banca);

        // Get all the bancaList
        restBancaMockMvc.perform(get("/api/bancas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(banca.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].telefone").value(hasItem(DEFAULT_TELEFONE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].limiteDescarga").value(hasItem(DEFAULT_LIMITE_DESCARGA.intValue())))
            .andExpect(jsonPath("$.[*].limitePremiacao").value(hasItem(DEFAULT_LIMITE_PREMIACAO.intValue())))
            .andExpect(jsonPath("$.[*].limiteBaixaAutomatica").value(hasItem(DEFAULT_LIMITE_BAIXA_AUTOMATICA.intValue())))
            .andExpect(jsonPath("$.[*].limiteHorarioEncerramento").value(hasItem(DEFAULT_LIMITE_HORARIO_ENCERRAMENTO.intValue())))
            .andExpect(jsonPath("$.[*].mensagemPule1").value(hasItem(DEFAULT_MENSAGEM_PULE_1)))
            .andExpect(jsonPath("$.[*].mensagemPule2").value(hasItem(DEFAULT_MENSAGEM_PULE_2)))
            .andExpect(jsonPath("$.[*].mensagemPule3").value(hasItem(DEFAULT_MENSAGEM_PULE_3)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].bonus").value(hasItem(DEFAULT_BONUS.intValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllBancasWithEagerRelationshipsIsEnabled() throws Exception {
        BancaResource bancaResource = new BancaResource(bancaRepositoryMock);
        when(bancaRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restBancaMockMvc.perform(get("/api/bancas?eagerload=true"))
            .andExpect(status().isOk());

        verify(bancaRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllBancasWithEagerRelationshipsIsNotEnabled() throws Exception {
        BancaResource bancaResource = new BancaResource(bancaRepositoryMock);
        when(bancaRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restBancaMockMvc.perform(get("/api/bancas?eagerload=true"))
            .andExpect(status().isOk());

        verify(bancaRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getBanca() throws Exception {
        // Initialize the database
        bancaRepository.saveAndFlush(banca);

        // Get the banca
        restBancaMockMvc.perform(get("/api/bancas/{id}", banca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(banca.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.telefone").value(DEFAULT_TELEFONE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.limiteDescarga").value(DEFAULT_LIMITE_DESCARGA.intValue()))
            .andExpect(jsonPath("$.limitePremiacao").value(DEFAULT_LIMITE_PREMIACAO.intValue()))
            .andExpect(jsonPath("$.limiteBaixaAutomatica").value(DEFAULT_LIMITE_BAIXA_AUTOMATICA.intValue()))
            .andExpect(jsonPath("$.limiteHorarioEncerramento").value(DEFAULT_LIMITE_HORARIO_ENCERRAMENTO.intValue()))
            .andExpect(jsonPath("$.mensagemPule1").value(DEFAULT_MENSAGEM_PULE_1))
            .andExpect(jsonPath("$.mensagemPule2").value(DEFAULT_MENSAGEM_PULE_2))
            .andExpect(jsonPath("$.mensagemPule3").value(DEFAULT_MENSAGEM_PULE_3))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.bonus").value(DEFAULT_BONUS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBanca() throws Exception {
        // Get the banca
        restBancaMockMvc.perform(get("/api/bancas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBanca() throws Exception {
        // Initialize the database
        bancaRepository.saveAndFlush(banca);

        int databaseSizeBeforeUpdate = bancaRepository.findAll().size();

        // Update the banca
        Banca updatedBanca = bancaRepository.findById(banca.getId()).get();
        // Disconnect from session so that the updates on updatedBanca are not directly saved in db
        em.detach(updatedBanca);
        updatedBanca
            .nome(UPDATED_NOME)
            .cidade(UPDATED_CIDADE)
            .telefone(UPDATED_TELEFONE)
            .estado(UPDATED_ESTADO)
            .limiteDescarga(UPDATED_LIMITE_DESCARGA)
            .limitePremiacao(UPDATED_LIMITE_PREMIACAO)
            .limiteBaixaAutomatica(UPDATED_LIMITE_BAIXA_AUTOMATICA)
            .limiteHorarioEncerramento(UPDATED_LIMITE_HORARIO_ENCERRAMENTO)
            .mensagemPule1(UPDATED_MENSAGEM_PULE_1)
            .mensagemPule2(UPDATED_MENSAGEM_PULE_2)
            .mensagemPule3(UPDATED_MENSAGEM_PULE_3)
            .data(UPDATED_DATA)
            .bonus(UPDATED_BONUS);

        restBancaMockMvc.perform(put("/api/bancas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBanca)))
            .andExpect(status().isOk());

        // Validate the Banca in the database
        List<Banca> bancaList = bancaRepository.findAll();
        assertThat(bancaList).hasSize(databaseSizeBeforeUpdate);
        Banca testBanca = bancaList.get(bancaList.size() - 1);
        assertThat(testBanca.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testBanca.getCidade()).isEqualTo(UPDATED_CIDADE);
        assertThat(testBanca.getTelefone()).isEqualTo(UPDATED_TELEFONE);
        assertThat(testBanca.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testBanca.getLimiteDescarga()).isEqualTo(UPDATED_LIMITE_DESCARGA);
        assertThat(testBanca.getLimitePremiacao()).isEqualTo(UPDATED_LIMITE_PREMIACAO);
        assertThat(testBanca.getLimiteBaixaAutomatica()).isEqualTo(UPDATED_LIMITE_BAIXA_AUTOMATICA);
        assertThat(testBanca.getLimiteHorarioEncerramento()).isEqualTo(UPDATED_LIMITE_HORARIO_ENCERRAMENTO);
        assertThat(testBanca.getMensagemPule1()).isEqualTo(UPDATED_MENSAGEM_PULE_1);
        assertThat(testBanca.getMensagemPule2()).isEqualTo(UPDATED_MENSAGEM_PULE_2);
        assertThat(testBanca.getMensagemPule3()).isEqualTo(UPDATED_MENSAGEM_PULE_3);
        assertThat(testBanca.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testBanca.getBonus()).isEqualTo(UPDATED_BONUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBanca() throws Exception {
        int databaseSizeBeforeUpdate = bancaRepository.findAll().size();

        // Create the Banca

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBancaMockMvc.perform(put("/api/bancas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(banca)))
            .andExpect(status().isBadRequest());

        // Validate the Banca in the database
        List<Banca> bancaList = bancaRepository.findAll();
        assertThat(bancaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBanca() throws Exception {
        // Initialize the database
        bancaRepository.saveAndFlush(banca);

        int databaseSizeBeforeDelete = bancaRepository.findAll().size();

        // Delete the banca
        restBancaMockMvc.perform(delete("/api/bancas/{id}", banca.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Banca> bancaList = bancaRepository.findAll();
        assertThat(bancaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
