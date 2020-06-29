package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * entity MovimentoGeral {\ndataMovimentacao Instant,\nvalor BigDecimal,\ntipo String,\nidDadosEntrada Long,\nidDadosSaida Long,\nidBanca Long,\nidCliente Long,\ndescricao String,\ntipoMovimentacao TipoMovimento\n}
 */
@ApiModel(description = "entity MovimentoGeral {\ndataMovimentacao Instant,\nvalor BigDecimal,\ntipo String,\nidDadosEntrada Long,\nidDadosSaida Long,\nidBanca Long,\nidCliente Long,\ndescricao String,\ntipoMovimentacao TipoMovimento\n}")
@Entity
@Table(name = "aposta")
public class Aposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


    @Column(name = "codigo_jogo", unique = true)
    private Long codigoJogo;

    @Column(name = "data_aposta")
    private Instant dataAposta;

    @Column(name = "loteria_nome")
    private String loteriaNome;

    @Column(name = "loteria_codigo")
    private Integer loteriaCodigo;

    @Column(name = "modalide")
    private String modalide;

    @Column(name = "codigo_modalidade")
    private String codigoModalidade;

    @Column(name = "premio")
    private String premio;

    @Column(name = "codigo_premio")
    private Long codigoPremio;

    @Column(name = "valor_jogo", precision = 21, scale = 2)
    private BigDecimal valorJogo;

    @Column(name = "codigo_banca")
    private Long codigoBanca;

    @ManyToOne
    @JsonIgnoreProperties("apostas")
    private Banca banca;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoJogo() {
        return codigoJogo;
    }

    public Aposta codigoJogo(Long codigoJogo) {
        this.codigoJogo = codigoJogo;
        return this;
    }

    public void setCodigoJogo(Long codigoJogo) {
        this.codigoJogo = codigoJogo;
    }

    public Instant getDataAposta() {
        return dataAposta;
    }

    public Aposta dataAposta(Instant dataAposta) {
        this.dataAposta = dataAposta;
        return this;
    }

    public void setDataAposta(Instant dataAposta) {
        this.dataAposta = dataAposta;
    }

    public String getLoteriaNome() {
        return loteriaNome;
    }

    public Aposta loteriaNome(String loteriaNome) {
        this.loteriaNome = loteriaNome;
        return this;
    }

    public void setLoteriaNome(String loteriaNome) {
        this.loteriaNome = loteriaNome;
    }

    public Integer getLoteriaCodigo() {
        return loteriaCodigo;
    }

    public Aposta loteriaCodigo(Integer loteriaCodigo) {
        this.loteriaCodigo = loteriaCodigo;
        return this;
    }

    public void setLoteriaCodigo(Integer loteriaCodigo) {
        this.loteriaCodigo = loteriaCodigo;
    }

    public String getModalide() {
        return modalide;
    }

    public Aposta modalide(String modalide) {
        this.modalide = modalide;
        return this;
    }

    public void setModalide(String modalide) {
        this.modalide = modalide;
    }

    public String getCodigoModalidade() {
        return codigoModalidade;
    }

    public Aposta codigoModalidade(String codigoModalidade) {
        this.codigoModalidade = codigoModalidade;
        return this;
    }

    public void setCodigoModalidade(String codigoModalidade) {
        this.codigoModalidade = codigoModalidade;
    }

    public String getPremio() {
        return premio;
    }

    public Aposta premio(String premio) {
        this.premio = premio;
        return this;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public Long getCodigoPremio() {
        return codigoPremio;
    }

    public Aposta codigoPremio(Long codigoPremio) {
        this.codigoPremio = codigoPremio;
        return this;
    }

    public void setCodigoPremio(Long codigoPremio) {
        this.codigoPremio = codigoPremio;
    }

    public BigDecimal getValorJogo() {
        return valorJogo;
    }

    public Aposta valorJogo(BigDecimal valorJogo) {
        this.valorJogo = valorJogo;
        return this;
    }

    public void setValorJogo(BigDecimal valorJogo) {
        this.valorJogo = valorJogo;
    }

    public Long getCodigoBanca() {
        return codigoBanca;
    }

    public Aposta codigoBanca(Long codigoBanca) {
        this.codigoBanca = codigoBanca;
        return this;
    }

    public void setCodigoBanca(Long codigoBanca) {
        this.codigoBanca = codigoBanca;
    }

    public Banca getBanca() {
        return banca;
    }

    public Aposta banca(Banca banca) {
        this.banca = banca;
        return this;
    }

    public void setBanca(Banca banca) {
        this.banca = banca;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Aposta)) {
            return false;
        }
        return id != null && id.equals(((Aposta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Aposta{" +
            "id=" + getId() +
            ", codigoJogo=" + getCodigoJogo() +
            ", dataAposta='" + getDataAposta() + "'" +
            ", loteriaNome='" + getLoteriaNome() + "'" +
            ", loteriaCodigo=" + getLoteriaCodigo() +
            ", modalide='" + getModalide() + "'" +
            ", codigoModalidade='" + getCodigoModalidade() + "'" +
            ", premio='" + getPremio() + "'" +
            ", codigoPremio=" + getCodigoPremio() +
            ", valorJogo=" + getValorJogo() +
            ", codigoBanca=" + getCodigoBanca() +
            "}";
    }
}
