package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A Aposta.
 */
@Entity
@Table(name = "aposta")
public class Aposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "numero_bilhete", nullable = false)
    private Long numeroBilhete;

    @NotNull
    @Column(name = "data_aposta", nullable = false)
    private Instant dataAposta;

    @NotNull
    @Column(name = "loteria_codigo", nullable = false)
    private Integer loteriaCodigo;

    @Column(name = "modalide")
    private String modalide;

    @NotNull
    @Column(name = "codigo_modalidade", nullable = false)
    private String codigoModalidade;

    @Column(name = "premio")
    private String premio;

    @NotNull
    @Column(name = "codigo_premio", nullable = false)
    private Long codigoPremio;

    @Column(name = "valor_jogo", precision = 21, scale = 2)
    private BigDecimal valorJogo;

    @Column(name = "codigo_banca")
    private Long codigoBanca;

    @NotNull
    @Column(name = "numero_aposta", nullable = false)
    private Long numeroAposta;

    @ManyToOne
    @JsonIgnoreProperties("apostas")
    private Bilhete bilhete;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroBilhete() {
        return numeroBilhete;
    }

    public Aposta numeroBilhete(Long numeroBilhete) {
        this.numeroBilhete = numeroBilhete;
        return this;
    }

    public void setNumeroBilhete(Long numeroBilhete) {
        this.numeroBilhete = numeroBilhete;
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

    public Long getNumeroAposta() {
        return numeroAposta;
    }

    public Aposta numeroAposta(Long numeroAposta) {
        this.numeroAposta = numeroAposta;
        return this;
    }

    public void setNumeroAposta(Long numeroAposta) {
        this.numeroAposta = numeroAposta;
    }

    public Bilhete getBilhete() {
        return bilhete;
    }

    public Aposta bilhete(Bilhete bilhete) {
        this.bilhete = bilhete;
        return this;
    }

    public void setBilhete(Bilhete bilhete) {
        this.bilhete = bilhete;
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
            ", numeroBilhete=" + getNumeroBilhete() +
            ", dataAposta='" + getDataAposta() + "'" +
            ", loteriaCodigo=" + getLoteriaCodigo() +
            ", modalide='" + getModalide() + "'" +
            ", codigoModalidade='" + getCodigoModalidade() + "'" +
            ", premio='" + getPremio() + "'" +
            ", codigoPremio=" + getCodigoPremio() +
            ", valorJogo=" + getValorJogo() +
            ", codigoBanca=" + getCodigoBanca() +
            ", numeroAposta=" + getNumeroAposta() +
            "}";
    }
}
