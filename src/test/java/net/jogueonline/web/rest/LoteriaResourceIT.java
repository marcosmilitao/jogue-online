package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Loteria;
import net.jogueonline.repository.LoteriaRepository;

import net.jogueonline.service.LoteriaService;
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
 * Integration tests for the {@link LoteriaResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class LoteriaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_HORA_ENCERRAMENTO = "AAAAAAAAAA";
    private static final String UPDATED_HORA_ENCERRAMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_PREMIACAO_INICIO = "AAAAAAAAAA";
    private static final String UPDATED_PREMIACAO_INICIO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    private static final BigDecimal DEFAULT_LIMITE_PREMIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_LIMITE_PREMIO = new BigDecimal(2);

    private static final Instant DEFAULT_DATA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CODIGO = 1;
    private static final Integer UPDATED_CODIGO = 2;

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final Integer DEFAULT_HORA = 1;
    private static final Integer UPDATED_HORA = 2;

    private static final Integer DEFAULT_MINUTO = 1;
    private static final Integer UPDATED_MINUTO = 2;

    private static final Boolean DEFAULT_DISPONIVEL = false;
    private static final Boolean UPDATED_DISPONIVEL = true;

    private static final String DEFAULT_DESCRICAO_COMPLETA = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO_COMPLETA = "BBBBBBBBBB";

    @Autowired
    private LoteriaRepository loteriaRepository;

    @Mock
    private LoteriaRepository loteriaRepositoryMock;

    @Mock
    private LoteriaService loteriaServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoteriaMockMvc;

    private Loteria loteria;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Loteria createEntity(EntityManager em) {
        Loteria loteria = new Loteria()
            .nome(DEFAULT_NOME)
            .horaEncerramento(DEFAULT_HORA_ENCERRAMENTO)
            .premiacaoInicio(DEFAULT_PREMIACAO_INICIO)
            .status(DEFAULT_STATUS)
            .limitePremio(DEFAULT_LIMITE_PREMIO)
            .data(DEFAULT_DATA)
            .codigo(DEFAULT_CODIGO)
            .descricao(DEFAULT_DESCRICAO)
            .hora(DEFAULT_HORA)
            .minuto(DEFAULT_MINUTO)
            .disponivel(DEFAULT_DISPONIVEL)
            .descricaoCompleta(DEFAULT_DESCRICAO_COMPLETA);
        return loteria;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Loteria createUpdatedEntity(EntityManager em) {
        Loteria loteria = new Loteria()
            .nome(UPDATED_NOME)
            .horaEncerramento(UPDATED_HORA_ENCERRAMENTO)
            .premiacaoInicio(UPDATED_PREMIACAO_INICIO)
            .status(UPDATED_STATUS)
            .limitePremio(UPDATED_LIMITE_PREMIO)
            .data(UPDATED_DATA)
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO)
            .hora(UPDATED_HORA)
            .minuto(UPDATED_MINUTO)
            .disponivel(UPDATED_DISPONIVEL)
            .descricaoCompleta(UPDATED_DESCRICAO_COMPLETA);
        return loteria;
    }

    @BeforeEach
    public void initTest() {
        loteria = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoteria() throws Exception {
        int databaseSizeBeforeCreate = loteriaRepository.findAll().size();

        // Create the Loteria
        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isCreated());

        // Validate the Loteria in the database
        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeCreate + 1);
        Loteria testLoteria = loteriaList.get(loteriaList.size() - 1);
        assertThat(testLoteria.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testLoteria.getHoraEncerramento()).isEqualTo(DEFAULT_HORA_ENCERRAMENTO);
        assertThat(testLoteria.getPremiacaoInicio()).isEqualTo(DEFAULT_PREMIACAO_INICIO);
        assertThat(testLoteria.isStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLoteria.getLimitePremio()).isEqualTo(DEFAULT_LIMITE_PREMIO);
        assertThat(testLoteria.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testLoteria.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testLoteria.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testLoteria.getHora()).isEqualTo(DEFAULT_HORA);
        assertThat(testLoteria.getMinuto()).isEqualTo(DEFAULT_MINUTO);
        assertThat(testLoteria.isDisponivel()).isEqualTo(DEFAULT_DISPONIVEL);
        assertThat(testLoteria.getDescricaoCompleta()).isEqualTo(DEFAULT_DESCRICAO_COMPLETA);
    }

    @Test
    @Transactional
    public void createLoteriaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loteriaRepository.findAll().size();

        // Create the Loteria with an existing ID
        loteria.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        // Validate the Loteria in the database
        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteriaRepository.findAll().size();
        // set the field null
        loteria.setNome(null);

        // Create the Loteria, which fails.

        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPremiacaoInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteriaRepository.findAll().size();
        // set the field null
        loteria.setPremiacaoInicio(null);

        // Create the Loteria, which fails.

        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLimitePremioIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteriaRepository.findAll().size();
        // set the field null
        loteria.setLimitePremio(null);

        // Create the Loteria, which fails.

        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteriaRepository.findAll().size();
        // set the field null
        loteria.setCodigo(null);

        // Create the Loteria, which fails.

        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteriaRepository.findAll().size();
        // set the field null
        loteria.setHora(null);

        // Create the Loteria, which fails.

        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMinutoIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteriaRepository.findAll().size();
        // set the field null
        loteria.setMinuto(null);

        // Create the Loteria, which fails.

        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescricaoCompletaIsRequired() throws Exception {
        int databaseSizeBeforeTest = loteriaRepository.findAll().size();
        // set the field null
        loteria.setDescricaoCompleta(null);

        // Create the Loteria, which fails.

        restLoteriaMockMvc.perform(post("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLoterias() throws Exception {
        // Initialize the database
        loteriaRepository.saveAndFlush(loteria);

        // Get all the loteriaList
        restLoteriaMockMvc.perform(get("/api/loterias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loteria.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].horaEncerramento").value(hasItem(DEFAULT_HORA_ENCERRAMENTO)))
            .andExpect(jsonPath("$.[*].premiacaoInicio").value(hasItem(DEFAULT_PREMIACAO_INICIO)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())))
            .andExpect(jsonPath("$.[*].limitePremio").value(hasItem(DEFAULT_LIMITE_PREMIO.intValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)))
            .andExpect(jsonPath("$.[*].hora").value(hasItem(DEFAULT_HORA)))
            .andExpect(jsonPath("$.[*].minuto").value(hasItem(DEFAULT_MINUTO)))
            .andExpect(jsonPath("$.[*].disponivel").value(hasItem(DEFAULT_DISPONIVEL.booleanValue())))
            .andExpect(jsonPath("$.[*].descricaoCompleta").value(hasItem(DEFAULT_DESCRICAO_COMPLETA)));
    }

    @SuppressWarnings({"unchecked"})
    public void getAllLoteriasWithEagerRelationshipsIsEnabled() throws Exception {
        LoteriaResource loteriaResource = new LoteriaResource(loteriaRepositoryMock,loteriaServiceMock);
        when(loteriaRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restLoteriaMockMvc.perform(get("/api/loterias?eagerload=true"))
            .andExpect(status().isOk());

        verify(loteriaRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllLoteriasWithEagerRelationshipsIsNotEnabled() throws Exception {
        LoteriaResource loteriaResource = new LoteriaResource(loteriaRepositoryMock,loteriaServiceMock);
        when(loteriaRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restLoteriaMockMvc.perform(get("/api/loterias?eagerload=true"))
            .andExpect(status().isOk());

        verify(loteriaRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getLoteria() throws Exception {
        // Initialize the database
        loteriaRepository.saveAndFlush(loteria);

        // Get the loteria
        restLoteriaMockMvc.perform(get("/api/loterias/{id}", loteria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loteria.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.horaEncerramento").value(DEFAULT_HORA_ENCERRAMENTO))
            .andExpect(jsonPath("$.premiacaoInicio").value(DEFAULT_PREMIACAO_INICIO))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()))
            .andExpect(jsonPath("$.limitePremio").value(DEFAULT_LIMITE_PREMIO.intValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO))
            .andExpect(jsonPath("$.hora").value(DEFAULT_HORA))
            .andExpect(jsonPath("$.minuto").value(DEFAULT_MINUTO))
            .andExpect(jsonPath("$.disponivel").value(DEFAULT_DISPONIVEL.booleanValue()))
            .andExpect(jsonPath("$.descricaoCompleta").value(DEFAULT_DESCRICAO_COMPLETA));
    }

    @Test
    @Transactional
    public void getNonExistingLoteria() throws Exception {
        // Get the loteria
        restLoteriaMockMvc.perform(get("/api/loterias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoteria() throws Exception {
        // Initialize the database
        loteriaRepository.saveAndFlush(loteria);

        int databaseSizeBeforeUpdate = loteriaRepository.findAll().size();

        // Update the loteria
        Loteria updatedLoteria = loteriaRepository.findById(loteria.getId()).get();
        // Disconnect from session so that the updates on updatedLoteria are not directly saved in db
        em.detach(updatedLoteria);
        updatedLoteria
            .nome(UPDATED_NOME)
            .horaEncerramento(UPDATED_HORA_ENCERRAMENTO)
            .premiacaoInicio(UPDATED_PREMIACAO_INICIO)
            .status(UPDATED_STATUS)
            .limitePremio(UPDATED_LIMITE_PREMIO)
            .data(UPDATED_DATA)
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO)
            .hora(UPDATED_HORA)
            .minuto(UPDATED_MINUTO)
            .disponivel(UPDATED_DISPONIVEL)
            .descricaoCompleta(UPDATED_DESCRICAO_COMPLETA);

        restLoteriaMockMvc.perform(put("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoteria)))
            .andExpect(status().isOk());

        // Validate the Loteria in the database
        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeUpdate);
        Loteria testLoteria = loteriaList.get(loteriaList.size() - 1);
        assertThat(testLoteria.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testLoteria.getHoraEncerramento()).isEqualTo(UPDATED_HORA_ENCERRAMENTO);
        assertThat(testLoteria.getPremiacaoInicio()).isEqualTo(UPDATED_PREMIACAO_INICIO);
        assertThat(testLoteria.isStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLoteria.getLimitePremio()).isEqualTo(UPDATED_LIMITE_PREMIO);
        assertThat(testLoteria.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testLoteria.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testLoteria.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testLoteria.getHora()).isEqualTo(UPDATED_HORA);
        assertThat(testLoteria.getMinuto()).isEqualTo(UPDATED_MINUTO);
        assertThat(testLoteria.isDisponivel()).isEqualTo(UPDATED_DISPONIVEL);
        assertThat(testLoteria.getDescricaoCompleta()).isEqualTo(UPDATED_DESCRICAO_COMPLETA);
    }

    @Test
    @Transactional
    public void updateNonExistingLoteria() throws Exception {
        int databaseSizeBeforeUpdate = loteriaRepository.findAll().size();

        // Create the Loteria

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoteriaMockMvc.perform(put("/api/loterias")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loteria)))
            .andExpect(status().isBadRequest());

        // Validate the Loteria in the database
        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoteria() throws Exception {
        // Initialize the database
        loteriaRepository.saveAndFlush(loteria);

        int databaseSizeBeforeDelete = loteriaRepository.findAll().size();

        // Delete the loteria
        restLoteriaMockMvc.perform(delete("/api/loterias/{id}", loteria.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Loteria> loteriaList = loteriaRepository.findAll();
        assertThat(loteriaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
