package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.Modalidade;
import net.jogueonline.repository.ModalidadeRepository;

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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ModalidadeResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ModalidadeResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MENOR_PALPITE = 1;
    private static final Integer UPDATED_MENOR_PALPITE = 2;

    private static final Integer DEFAULT_MAIOR_PALPITE = 1;
    private static final Integer UPDATED_MAIOR_PALPITE = 2;

    private static final Integer DEFAULT_QTDE_PALPITES = 1;
    private static final Integer UPDATED_QTDE_PALPITES = 2;

    private static final Integer DEFAULT_QTDE_MINIMA_PALPITES = 1;
    private static final Integer UPDATED_QTDE_MINIMA_PALPITES = 2;

    private static final Integer DEFAULT_QTDE_CARACTERES = 1;
    private static final Integer UPDATED_QTDE_CARACTERES = 2;

    private static final Integer DEFAULT_QTDE_MINIMA_CARACTERES = 1;
    private static final Integer UPDATED_QTDE_MINIMA_CARACTERES = 2;

    private static final BigDecimal DEFAULT_MENOR_VALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_MENOR_VALOR = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MAIOR_VALOR = new BigDecimal(1);
    private static final BigDecimal UPDATED_MAIOR_VALOR = new BigDecimal(2);

    private static final Boolean DEFAULT_MAIOR_VALOR_EXCESSAO = false;
    private static final Boolean UPDATED_MAIOR_VALOR_EXCESSAO = true;

    private static final Boolean DEFAULT_REPETICAO = false;
    private static final Boolean UPDATED_REPETICAO = true;

    private static final String DEFAULT_MASCARA = "AAAAAAAAAA";
    private static final String UPDATED_MASCARA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PALPITE_MULTIPLO = false;
    private static final Boolean UPDATED_PALPITE_MULTIPLO = true;

    private static final Boolean DEFAULT_PALPITE_MULTIPLO_TERMINAL = false;
    private static final Boolean UPDATED_PALPITE_MULTIPLO_TERMINAL = true;

    private static final Boolean DEFAULT_ORDENAR = false;
    private static final Boolean UPDATED_ORDENAR = true;

    private static final Boolean DEFAULT_PERMITE_PALPITE_ALEATORIO = false;
    private static final Boolean UPDATED_PERMITE_PALPITE_ALEATORIO = true;

    @Autowired
    private ModalidadeRepository modalidadeRepository;

    @Mock
    private ModalidadeRepository modalidadeRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restModalidadeMockMvc;

    private Modalidade modalidade;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modalidade createEntity(EntityManager em) {
        Modalidade modalidade = new Modalidade()
            .codigo(DEFAULT_CODIGO)
            .nome(DEFAULT_NOME)
            .menorPalpite(DEFAULT_MENOR_PALPITE)
            .maiorPalpite(DEFAULT_MAIOR_PALPITE)
            .qtdePalpites(DEFAULT_QTDE_PALPITES)
            .qtdeMinimaPalpites(DEFAULT_QTDE_MINIMA_PALPITES)
            .qtdeCaracteres(DEFAULT_QTDE_CARACTERES)
            .qtdeMinimaCaracteres(DEFAULT_QTDE_MINIMA_CARACTERES)
            .menorValor(DEFAULT_MENOR_VALOR)
            .maiorValor(DEFAULT_MAIOR_VALOR)
            .maiorValorExcessao(DEFAULT_MAIOR_VALOR_EXCESSAO)
            .repeticao(DEFAULT_REPETICAO)
            .mascara(DEFAULT_MASCARA)
            .palpiteMultiplo(DEFAULT_PALPITE_MULTIPLO)
            .palpiteMultiploTerminal(DEFAULT_PALPITE_MULTIPLO_TERMINAL)
            .ordenar(DEFAULT_ORDENAR)
            .permitePalpiteAleatorio(DEFAULT_PERMITE_PALPITE_ALEATORIO);
        return modalidade;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modalidade createUpdatedEntity(EntityManager em) {
        Modalidade modalidade = new Modalidade()
            .codigo(UPDATED_CODIGO)
            .nome(UPDATED_NOME)
            .menorPalpite(UPDATED_MENOR_PALPITE)
            .maiorPalpite(UPDATED_MAIOR_PALPITE)
            .qtdePalpites(UPDATED_QTDE_PALPITES)
            .qtdeMinimaPalpites(UPDATED_QTDE_MINIMA_PALPITES)
            .qtdeCaracteres(UPDATED_QTDE_CARACTERES)
            .qtdeMinimaCaracteres(UPDATED_QTDE_MINIMA_CARACTERES)
            .menorValor(UPDATED_MENOR_VALOR)
            .maiorValor(UPDATED_MAIOR_VALOR)
            .maiorValorExcessao(UPDATED_MAIOR_VALOR_EXCESSAO)
            .repeticao(UPDATED_REPETICAO)
            .mascara(UPDATED_MASCARA)
            .palpiteMultiplo(UPDATED_PALPITE_MULTIPLO)
            .palpiteMultiploTerminal(UPDATED_PALPITE_MULTIPLO_TERMINAL)
            .ordenar(UPDATED_ORDENAR)
            .permitePalpiteAleatorio(UPDATED_PERMITE_PALPITE_ALEATORIO);
        return modalidade;
    }

    @BeforeEach
    public void initTest() {
        modalidade = createEntity(em);
    }

    @Test
    @Transactional
    public void createModalidade() throws Exception {
        int databaseSizeBeforeCreate = modalidadeRepository.findAll().size();

        // Create the Modalidade
        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isCreated());

        // Validate the Modalidade in the database
        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeCreate + 1);
        Modalidade testModalidade = modalidadeList.get(modalidadeList.size() - 1);
        assertThat(testModalidade.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testModalidade.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testModalidade.getMenorPalpite()).isEqualTo(DEFAULT_MENOR_PALPITE);
        assertThat(testModalidade.getMaiorPalpite()).isEqualTo(DEFAULT_MAIOR_PALPITE);
        assertThat(testModalidade.getQtdePalpites()).isEqualTo(DEFAULT_QTDE_PALPITES);
        assertThat(testModalidade.getQtdeMinimaPalpites()).isEqualTo(DEFAULT_QTDE_MINIMA_PALPITES);
        assertThat(testModalidade.getQtdeCaracteres()).isEqualTo(DEFAULT_QTDE_CARACTERES);
        assertThat(testModalidade.getQtdeMinimaCaracteres()).isEqualTo(DEFAULT_QTDE_MINIMA_CARACTERES);
        assertThat(testModalidade.getMenorValor()).isEqualTo(DEFAULT_MENOR_VALOR);
        assertThat(testModalidade.getMaiorValor()).isEqualTo(DEFAULT_MAIOR_VALOR);
        assertThat(testModalidade.isMaiorValorExcessao()).isEqualTo(DEFAULT_MAIOR_VALOR_EXCESSAO);
        assertThat(testModalidade.isRepeticao()).isEqualTo(DEFAULT_REPETICAO);
        assertThat(testModalidade.getMascara()).isEqualTo(DEFAULT_MASCARA);
        assertThat(testModalidade.isPalpiteMultiplo()).isEqualTo(DEFAULT_PALPITE_MULTIPLO);
        assertThat(testModalidade.isPalpiteMultiploTerminal()).isEqualTo(DEFAULT_PALPITE_MULTIPLO_TERMINAL);
        assertThat(testModalidade.isOrdenar()).isEqualTo(DEFAULT_ORDENAR);
        assertThat(testModalidade.isPermitePalpiteAleatorio()).isEqualTo(DEFAULT_PERMITE_PALPITE_ALEATORIO);
    }

    @Test
    @Transactional
    public void createModalidadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = modalidadeRepository.findAll().size();

        // Create the Modalidade with an existing ID
        modalidade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        // Validate the Modalidade in the database
        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setCodigo(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomeIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setNome(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMenorPalpiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setMenorPalpite(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaiorPalpiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setMaiorPalpite(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdeMinimaPalpitesIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setQtdeMinimaPalpites(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdeCaracteresIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setQtdeCaracteres(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQtdeMinimaCaracteresIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setQtdeMinimaCaracteres(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMenorValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setMenorValor(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaiorValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = modalidadeRepository.findAll().size();
        // set the field null
        modalidade.setMaiorValor(null);

        // Create the Modalidade, which fails.

        restModalidadeMockMvc.perform(post("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModalidades() throws Exception {
        // Initialize the database
        modalidadeRepository.saveAndFlush(modalidade);

        // Get all the modalidadeList
        restModalidadeMockMvc.perform(get("/api/modalidades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modalidade.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].menorPalpite").value(hasItem(DEFAULT_MENOR_PALPITE)))
            .andExpect(jsonPath("$.[*].maiorPalpite").value(hasItem(DEFAULT_MAIOR_PALPITE)))
            .andExpect(jsonPath("$.[*].qtdePalpites").value(hasItem(DEFAULT_QTDE_PALPITES)))
            .andExpect(jsonPath("$.[*].qtdeMinimaPalpites").value(hasItem(DEFAULT_QTDE_MINIMA_PALPITES)))
            .andExpect(jsonPath("$.[*].qtdeCaracteres").value(hasItem(DEFAULT_QTDE_CARACTERES)))
            .andExpect(jsonPath("$.[*].qtdeMinimaCaracteres").value(hasItem(DEFAULT_QTDE_MINIMA_CARACTERES)))
            .andExpect(jsonPath("$.[*].menorValor").value(hasItem(DEFAULT_MENOR_VALOR.intValue())))
            .andExpect(jsonPath("$.[*].maiorValor").value(hasItem(DEFAULT_MAIOR_VALOR.intValue())))
            .andExpect(jsonPath("$.[*].maiorValorExcessao").value(hasItem(DEFAULT_MAIOR_VALOR_EXCESSAO.booleanValue())))
            .andExpect(jsonPath("$.[*].repeticao").value(hasItem(DEFAULT_REPETICAO.booleanValue())))
            .andExpect(jsonPath("$.[*].mascara").value(hasItem(DEFAULT_MASCARA)))
            .andExpect(jsonPath("$.[*].palpiteMultiplo").value(hasItem(DEFAULT_PALPITE_MULTIPLO.booleanValue())))
            .andExpect(jsonPath("$.[*].palpiteMultiploTerminal").value(hasItem(DEFAULT_PALPITE_MULTIPLO_TERMINAL.booleanValue())))
            .andExpect(jsonPath("$.[*].ordenar").value(hasItem(DEFAULT_ORDENAR.booleanValue())))
            .andExpect(jsonPath("$.[*].permitePalpiteAleatorio").value(hasItem(DEFAULT_PERMITE_PALPITE_ALEATORIO.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllModalidadesWithEagerRelationshipsIsEnabled() throws Exception {
        ModalidadeResource modalidadeResource = new ModalidadeResource(modalidadeRepositoryMock);
        when(modalidadeRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restModalidadeMockMvc.perform(get("/api/modalidades?eagerload=true"))
            .andExpect(status().isOk());

        verify(modalidadeRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllModalidadesWithEagerRelationshipsIsNotEnabled() throws Exception {
        ModalidadeResource modalidadeResource = new ModalidadeResource(modalidadeRepositoryMock);
        when(modalidadeRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restModalidadeMockMvc.perform(get("/api/modalidades?eagerload=true"))
            .andExpect(status().isOk());

        verify(modalidadeRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getModalidade() throws Exception {
        // Initialize the database
        modalidadeRepository.saveAndFlush(modalidade);

        // Get the modalidade
        restModalidadeMockMvc.perform(get("/api/modalidades/{id}", modalidade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modalidade.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.menorPalpite").value(DEFAULT_MENOR_PALPITE))
            .andExpect(jsonPath("$.maiorPalpite").value(DEFAULT_MAIOR_PALPITE))
            .andExpect(jsonPath("$.qtdePalpites").value(DEFAULT_QTDE_PALPITES))
            .andExpect(jsonPath("$.qtdeMinimaPalpites").value(DEFAULT_QTDE_MINIMA_PALPITES))
            .andExpect(jsonPath("$.qtdeCaracteres").value(DEFAULT_QTDE_CARACTERES))
            .andExpect(jsonPath("$.qtdeMinimaCaracteres").value(DEFAULT_QTDE_MINIMA_CARACTERES))
            .andExpect(jsonPath("$.menorValor").value(DEFAULT_MENOR_VALOR.intValue()))
            .andExpect(jsonPath("$.maiorValor").value(DEFAULT_MAIOR_VALOR.intValue()))
            .andExpect(jsonPath("$.maiorValorExcessao").value(DEFAULT_MAIOR_VALOR_EXCESSAO.booleanValue()))
            .andExpect(jsonPath("$.repeticao").value(DEFAULT_REPETICAO.booleanValue()))
            .andExpect(jsonPath("$.mascara").value(DEFAULT_MASCARA))
            .andExpect(jsonPath("$.palpiteMultiplo").value(DEFAULT_PALPITE_MULTIPLO.booleanValue()))
            .andExpect(jsonPath("$.palpiteMultiploTerminal").value(DEFAULT_PALPITE_MULTIPLO_TERMINAL.booleanValue()))
            .andExpect(jsonPath("$.ordenar").value(DEFAULT_ORDENAR.booleanValue()))
            .andExpect(jsonPath("$.permitePalpiteAleatorio").value(DEFAULT_PERMITE_PALPITE_ALEATORIO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingModalidade() throws Exception {
        // Get the modalidade
        restModalidadeMockMvc.perform(get("/api/modalidades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModalidade() throws Exception {
        // Initialize the database
        modalidadeRepository.saveAndFlush(modalidade);

        int databaseSizeBeforeUpdate = modalidadeRepository.findAll().size();

        // Update the modalidade
        Modalidade updatedModalidade = modalidadeRepository.findById(modalidade.getId()).get();
        // Disconnect from session so that the updates on updatedModalidade are not directly saved in db
        em.detach(updatedModalidade);
        updatedModalidade
            .codigo(UPDATED_CODIGO)
            .nome(UPDATED_NOME)
            .menorPalpite(UPDATED_MENOR_PALPITE)
            .maiorPalpite(UPDATED_MAIOR_PALPITE)
            .qtdePalpites(UPDATED_QTDE_PALPITES)
            .qtdeMinimaPalpites(UPDATED_QTDE_MINIMA_PALPITES)
            .qtdeCaracteres(UPDATED_QTDE_CARACTERES)
            .qtdeMinimaCaracteres(UPDATED_QTDE_MINIMA_CARACTERES)
            .menorValor(UPDATED_MENOR_VALOR)
            .maiorValor(UPDATED_MAIOR_VALOR)
            .maiorValorExcessao(UPDATED_MAIOR_VALOR_EXCESSAO)
            .repeticao(UPDATED_REPETICAO)
            .mascara(UPDATED_MASCARA)
            .palpiteMultiplo(UPDATED_PALPITE_MULTIPLO)
            .palpiteMultiploTerminal(UPDATED_PALPITE_MULTIPLO_TERMINAL)
            .ordenar(UPDATED_ORDENAR)
            .permitePalpiteAleatorio(UPDATED_PERMITE_PALPITE_ALEATORIO);

        restModalidadeMockMvc.perform(put("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedModalidade)))
            .andExpect(status().isOk());

        // Validate the Modalidade in the database
        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeUpdate);
        Modalidade testModalidade = modalidadeList.get(modalidadeList.size() - 1);
        assertThat(testModalidade.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testModalidade.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testModalidade.getMenorPalpite()).isEqualTo(UPDATED_MENOR_PALPITE);
        assertThat(testModalidade.getMaiorPalpite()).isEqualTo(UPDATED_MAIOR_PALPITE);
        assertThat(testModalidade.getQtdePalpites()).isEqualTo(UPDATED_QTDE_PALPITES);
        assertThat(testModalidade.getQtdeMinimaPalpites()).isEqualTo(UPDATED_QTDE_MINIMA_PALPITES);
        assertThat(testModalidade.getQtdeCaracteres()).isEqualTo(UPDATED_QTDE_CARACTERES);
        assertThat(testModalidade.getQtdeMinimaCaracteres()).isEqualTo(UPDATED_QTDE_MINIMA_CARACTERES);
        assertThat(testModalidade.getMenorValor()).isEqualTo(UPDATED_MENOR_VALOR);
        assertThat(testModalidade.getMaiorValor()).isEqualTo(UPDATED_MAIOR_VALOR);
        assertThat(testModalidade.isMaiorValorExcessao()).isEqualTo(UPDATED_MAIOR_VALOR_EXCESSAO);
        assertThat(testModalidade.isRepeticao()).isEqualTo(UPDATED_REPETICAO);
        assertThat(testModalidade.getMascara()).isEqualTo(UPDATED_MASCARA);
        assertThat(testModalidade.isPalpiteMultiplo()).isEqualTo(UPDATED_PALPITE_MULTIPLO);
        assertThat(testModalidade.isPalpiteMultiploTerminal()).isEqualTo(UPDATED_PALPITE_MULTIPLO_TERMINAL);
        assertThat(testModalidade.isOrdenar()).isEqualTo(UPDATED_ORDENAR);
        assertThat(testModalidade.isPermitePalpiteAleatorio()).isEqualTo(UPDATED_PERMITE_PALPITE_ALEATORIO);
    }

    @Test
    @Transactional
    public void updateNonExistingModalidade() throws Exception {
        int databaseSizeBeforeUpdate = modalidadeRepository.findAll().size();

        // Create the Modalidade

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModalidadeMockMvc.perform(put("/api/modalidades")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(modalidade)))
            .andExpect(status().isBadRequest());

        // Validate the Modalidade in the database
        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModalidade() throws Exception {
        // Initialize the database
        modalidadeRepository.saveAndFlush(modalidade);

        int databaseSizeBeforeDelete = modalidadeRepository.findAll().size();

        // Delete the modalidade
        restModalidadeMockMvc.perform(delete("/api/modalidades/{id}", modalidade.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Modalidade> modalidadeList = modalidadeRepository.findAll();
        assertThat(modalidadeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
