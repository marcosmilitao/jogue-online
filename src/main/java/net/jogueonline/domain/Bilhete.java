package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import net.jogueonline.util.DateTimeInstantUtil;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * entity MovimentoGeral {\ndataMovimentacao Instant,\nvalor BigDecimal,\ntipo String,\nidDadosEntrada Long,\nidDadosSaida Long,\nidBanca Long,\nidCliente Long,\ndescricao String,\ntipoMovimentacao TipoMovimento\n}
 */
@ApiModel(description = "entity MovimentoGeral {\ndataMovimentacao Instant,\nvalor BigDecimal,\ntipo String,\nidDadosEntrada Long,\nidDadosSaida Long,\nidBanca Long,\nidCliente Long,\ndescricao String,\ntipoMovimentacao TipoMovimento\n}")
@Entity
@Table(name = "bilhete")
public class Bilhete implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "numero_bilhete", nullable = false)
    private Long numeroBilhete;

    @NotNull
    @Column(name = "codigo_banca", nullable = false)
    private Long codigoBanca;

    @NotNull
    @Column(name = "codigo_loteria", nullable = false)
    private Long codigoLoteria;

    @Column(name = "loteria_nome")
    private String loteriaNome;

    @NotNull
    @Column(name = "valor_total_aposta", precision = 21, scale = 2, nullable = false)
    private BigDecimal valorTotalAposta;

    @Column(name = "bonus_banca")
    private Long bonusBanca;

    @Column(name = "bonus_individual")
    private Long bonusIndividual;

    @Column(name = "comicao")
    private Long comicao;

    @NotNull
    @Column(name = "valor_bilhete", precision = 21, scale = 2, nullable = false)
    private BigDecimal valorBilhete;

    @NotNull
    @Column(name = "data_hora_aposta", nullable = false)
    private Instant dataHoraAposta;

    @Column(name = "qrcode")
    private String qrcode;

    @NotNull
    @Column(name = "codigo_terminal", nullable = false)
    private Long codigoTerminal;

    @OneToMany(mappedBy = "bilhete")
    private Set<Aposta> apostas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("bilhetes")
    private Banca banca;




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

    public Bilhete numeroBilhete(Long numeroBilhete) {
        this.numeroBilhete = numeroBilhete;
        return this;
    }

    public void setNumeroBilhete(Long numeroBilhete) {
        this.numeroBilhete = numeroBilhete;
    }

    public Long getCodigoBanca() {
        return codigoBanca;
    }

    public Bilhete codigoBanca(Long codigoBanca) {
        this.codigoBanca = codigoBanca;
        return this;
    }

    public void setCodigoBanca(Long codigoBanca) {
        this.codigoBanca = codigoBanca;
    }

    public Long getCodigoLoteria() {
        return codigoLoteria;
    }

    public Bilhete codigoLoteria(Long codigoLoteria) {
        this.codigoLoteria = codigoLoteria;
        return this;
    }

    public void setCodigoLoteria(Long codigoLoteria) {
        this.codigoLoteria = codigoLoteria;
    }

    public String getLoteriaNome() {
        return loteriaNome;
    }

    public Bilhete loteriaNome(String loteriaNome) {
        this.loteriaNome = loteriaNome;
        return this;
    }

    public void setLoteriaNome(String loteriaNome) {
        this.loteriaNome = loteriaNome;
    }

    public BigDecimal getValorTotalAposta() {
        return valorTotalAposta;
    }

    public Bilhete valorTotalAposta(BigDecimal valorTotalAposta) {
        this.valorTotalAposta = valorTotalAposta;
        return this;
    }

    public void setValorTotalAposta(BigDecimal valorTotalAposta) {
        this.valorTotalAposta = valorTotalAposta;
    }

    public Long getBonusBanca() {
        return bonusBanca;
    }

    public Bilhete bonusBanca(Long bonusBanca) {
        this.bonusBanca = bonusBanca;
        return this;
    }

    public void setBonusBanca(Long bonusBanca) {
        this.bonusBanca = bonusBanca;
    }

    public Long getBonusIndividual() {
        return bonusIndividual;
    }

    public Bilhete bonusIndividual(Long bonusIndividual) {
        this.bonusIndividual = bonusIndividual;
        return this;
    }

    public void setBonusIndividual(Long bonusIndividual) {
        this.bonusIndividual = bonusIndividual;
    }

    public Long getComicao() {
        return comicao;
    }

    public Bilhete comicao(Long comicao) {
        this.comicao = comicao;
        return this;
    }

    public void setComicao(Long comicao) {
        this.comicao = comicao;
    }

    public BigDecimal getValorBilhete() {
        return valorBilhete;
    }

    public Bilhete valorBilhete(BigDecimal valorBilhete) {
        this.valorBilhete = valorBilhete;
        return this;
    }

    public void setValorBilhete(BigDecimal valorBilhete) {
        this.valorBilhete = valorBilhete;
    }

    public Instant getDataHoraAposta() {
        return dataHoraAposta;
    }

    public Bilhete dataHoraAposta(Instant dataHoraAposta) {
        this.dataHoraAposta = dataHoraAposta;
        return this;
    }

    public void setDataHoraAposta(Instant dataHoraAposta) {
        this.dataHoraAposta = dataHoraAposta;
    }

    public String getQrcode() {
        return qrcode;
    }

    public Bilhete qrcode(String qrcode) {
        this.qrcode = qrcode;
        return this;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Long getCodigoTerminal() {
        return codigoTerminal;
    }

    public Bilhete codigoTerminal(Long codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
        return this;
    }

    public void setCodigoTerminal(Long codigoTerminal) {
        this.codigoTerminal = codigoTerminal;
    }

    public Set<Aposta> getApostas() {
        return apostas;
    }

    public Bilhete apostas(Set<Aposta> apostas) {
        this.apostas = apostas;
        return this;
    }

    public Bilhete addAposta(Aposta aposta) {
        this.apostas.add(aposta);
        aposta.setBilhete(this);
        return this;
    }

    public Bilhete removeAposta(Aposta aposta) {
        this.apostas.remove(aposta);
        aposta.setBilhete(null);
        return this;
    }

    public void setApostas(Set<Aposta> apostas) {
        this.apostas = apostas;
    }

    public Banca getBanca() {
        return banca;
    }

    public Bilhete banca(Banca banca) {
        this.banca = banca;
        return this;
    }

    public void setBanca(Banca banca) {
        this.banca = banca;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @PrePersist
    protected void prePersist() {
        if(this.dataHoraAposta == null){
            this.dataHoraAposta = DateTimeInstantUtil.getDataTimeNowBr();
        }
        if (this.numeroBilhete == null){
            this.numeroBilhete = 0L;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bilhete)) {
            return false;
        }
        return id != null && id.equals(((Bilhete) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bilhete{" +
            "id=" + getId() +
            ", numeroBilhete=" + getNumeroBilhete() +
            ", codigoBanca=" + getCodigoBanca() +
            ", codigoLoteria=" + getCodigoLoteria() +
            ", loteriaNome='" + getLoteriaNome() + "'" +
            ", valorTotalAposta=" + getValorTotalAposta() +
            ", bonusBanca=" + getBonusBanca() +
            ", bonusIndividual=" + getBonusIndividual() +
            ", comicao=" + getComicao() +
            ", valorBilhete=" + getValorBilhete() +
            ", dataHoraAposta='" + getDataHoraAposta() + "'" +
            ", qrcode='" + getQrcode() + "'" +
            ", codigoTerminal=" + getCodigoTerminal() +
            "}";
    }
}
