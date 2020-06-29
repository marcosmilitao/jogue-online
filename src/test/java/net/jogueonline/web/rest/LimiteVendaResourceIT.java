package net.jogueonline.web.rest;

import net.jogueonline.JogueOnlineApp;
import net.jogueonline.domain.LimiteVenda;
import net.jogueonline.repository.LimiteVendaRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LimiteVendaResource} REST controller.
 */
@SpringBootTest(classes = JogueOnlineApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LimiteVendaResourceIT {

    private static final Integer DEFAULT_CODIGO_REVENDEDOR = 1;
    private static final Integer UPDATED_CODIGO_REVENDEDOR = 2;

    private static final BigDecimal DEFAULT_VENDA_DIA = new BigDecimal(1);
    private static final BigDecimal UPDATED_VENDA_DIA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DEBITO_ATUAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEBITO_ATUAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LIMITE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LIMITE = new BigDecimal(2);

    @Autowired
    private LimiteVendaRepository limiteVendaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLimiteVendaMockMvc;

    private LimiteVenda limiteVenda;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LimiteVenda createEntity(EntityManager em) {
        LimiteVenda limiteVenda = new LimiteVenda()
            .codigoRevendedor(DEFAULT_CODIGO_REVENDEDOR)
            .vendaDia(DEFAULT_VENDA_DIA)
            .debitoAtual(DEFAULT_DEBITO_ATUAL)
            .limite(DEFAULT_LIMITE);
        return limiteVenda;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LimiteVenda createUpdatedEntity(EntityManager em) {
        LimiteVenda limiteVenda = new LimiteVenda()
            .codigoRevendedor(UPDATED_CODIGO_REVENDEDOR)
            .vendaDia(UPDATED_VENDA_DIA)
            .debitoAtual(UPDATED_DEBITO_ATUAL)
            .limite(UPDATED_LIMITE);
        return limiteVenda;
    }

    @BeforeEach
    public void initTest() {
        limiteVenda = createEntity(em);
    }

    @Test
    @Transactional
    public void createLimiteVenda() throws Exception {
        int databaseSizeBeforeCreate = limiteVendaRepository.findAll().size();

        // Create the LimiteVenda
        restLimiteVendaMockMvc.perform(post("/api/limite-vendas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(limiteVenda)))
            .andExpect(status().isCreated());

        // Validate the LimiteVenda in the database
        List<LimiteVenda> limiteVendaList = limiteVendaRepository.findAll();
        assertThat(limiteVendaList).hasSize(databaseSizeBeforeCreate + 1);
        LimiteVenda testLimiteVenda = limiteVendaList.get(limiteVendaList.size() - 1);
        assertThat(testLimiteVenda.getCodigoRevendedor()).isEqualTo(DEFAULT_CODIGO_REVENDEDOR);
        assertThat(testLimiteVenda.getVendaDia()).isEqualTo(DEFAULT_VENDA_DIA);
        assertThat(testLimiteVenda.getDebitoAtual()).isEqualTo(DEFAULT_DEBITO_ATUAL);
        assertThat(testLimiteVenda.getLimite()).isEqualTo(DEFAULT_LIMITE);
    }

    @Test
    @Transactional
    public void createLimiteVendaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = limiteVendaRepository.findAll().size();

        // Create the LimiteVenda with an existing ID
        limiteVenda.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLimiteVendaMockMvc.perform(post("/api/limite-vendas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(limiteVenda)))
            .andExpect(status().isBadRequest());

        // Validate the LimiteVenda in the database
        List<LimiteVenda> limiteVendaList = limiteVendaRepository.findAll();
        assertThat(limiteVendaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLimiteVendas() throws Exception {
        // Initialize the database
        limiteVendaRepository.saveAndFlush(limiteVenda);

        // Get all the limiteVendaList
        restLimiteVendaMockMvc.perform(get("/api/limite-vendas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(limiteVenda.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoRevendedor").value(hasItem(DEFAULT_CODIGO_REVENDEDOR)))
            .andExpect(jsonPath("$.[*].vendaDia").value(hasItem(DEFAULT_VENDA_DIA.intValue())))
            .andExpect(jsonPath("$.[*].debitoAtual").value(hasItem(DEFAULT_DEBITO_ATUAL.intValue())))
            .andExpect(jsonPath("$.[*].limite").value(hasItem(DEFAULT_LIMITE.intValue())));
    }
    
    @Test
    @Transactional
    public void getLimiteVenda() throws Exception {
        // Initialize the database
        limiteVendaRepository.saveAndFlush(limiteVenda);

        // Get the limiteVenda
        restLimiteVendaMockMvc.perform(get("/api/limite-vendas/{id}", limiteVenda.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(limiteVenda.getId().intValue()))
            .andExpect(jsonPath("$.codigoRevendedor").value(DEFAULT_CODIGO_REVENDEDOR))
            .andExpect(jsonPath("$.vendaDia").value(DEFAULT_VENDA_DIA.intValue()))
            .andExpect(jsonPath("$.debitoAtual").value(DEFAULT_DEBITO_ATUAL.intValue()))
            .andExpect(jsonPath("$.limite").value(DEFAULT_LIMITE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLimiteVenda() throws Exception {
        // Get the limiteVenda
        restLimiteVendaMockMvc.perform(get("/api/limite-vendas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLimiteVenda() throws Exception {
        // Initialize the database
        limiteVendaRepository.saveAndFlush(limiteVenda);

        int databaseSizeBeforeUpdate = limiteVendaRepository.findAll().size();

        // Update the limiteVenda
        LimiteVenda updatedLimiteVenda = limiteVendaRepository.findById(limiteVenda.getId()).get();
        // Disconnect from session so that the updates on updatedLimiteVenda are not directly saved in db
        em.detach(updatedLimiteVenda);
        updatedLimiteVenda
            .codigoRevendedor(UPDATED_CODIGO_REVENDEDOR)
            .vendaDia(UPDATED_VENDA_DIA)
            .debitoAtual(UPDATED_DEBITO_ATUAL)
            .limite(UPDATED_LIMITE);

        restLimiteVendaMockMvc.perform(put("/api/limite-vendas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLimiteVenda)))
            .andExpect(status().isOk());

        // Validate the LimiteVenda in the database
        List<LimiteVenda> limiteVendaList = limiteVendaRepository.findAll();
        assertThat(limiteVendaList).hasSize(databaseSizeBeforeUpdate);
        LimiteVenda testLimiteVenda = limiteVendaList.get(limiteVendaList.size() - 1);
        assertThat(testLimiteVenda.getCodigoRevendedor()).isEqualTo(UPDATED_CODIGO_REVENDEDOR);
        assertThat(testLimiteVenda.getVendaDia()).isEqualTo(UPDATED_VENDA_DIA);
        assertThat(testLimiteVenda.getDebitoAtual()).isEqualTo(UPDATED_DEBITO_ATUAL);
        assertThat(testLimiteVenda.getLimite()).isEqualTo(UPDATED_LIMITE);
    }

    @Test
    @Transactional
    public void updateNonExistingLimiteVenda() throws Exception {
        int databaseSizeBeforeUpdate = limiteVendaRepository.findAll().size();

        // Create the LimiteVenda

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLimiteVendaMockMvc.perform(put("/api/limite-vendas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(limiteVenda)))
            .andExpect(status().isBadRequest());

        // Validate the LimiteVenda in the database
        List<LimiteVenda> limiteVendaList = limiteVendaRepository.findAll();
        assertThat(limiteVendaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLimiteVenda() throws Exception {
        // Initialize the database
        limiteVendaRepository.saveAndFlush(limiteVenda);

        int databaseSizeBeforeDelete = limiteVendaRepository.findAll().size();

        // Delete the limiteVenda
        restLimiteVendaMockMvc.perform(delete("/api/limite-vendas/{id}", limiteVenda.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LimiteVenda> limiteVendaList = limiteVendaRepository.findAll();
        assertThat(limiteVendaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
