package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * entity MovimentacaoCliente{\ntipo String,\nvalor BigDecimal,\ndataAtualizacao Instant,\ntipoMovimentacao TipoMovimento\n}
 */
@ApiModel(description = "entity MovimentacaoCliente{\ntipo String,\nvalor BigDecimal,\ndataAtualizacao Instant,\ntipoMovimentacao TipoMovimento\n}")
@Entity
@Table(name = "terminal")
public class Terminal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "telefone_chipe")
    private Long telefoneChipe;

    @NotNull
    @Column(name = "serial", nullable = false)
    private String serial;

    @Column(name = "menssagem")
    private String menssagem;

    @Column(name = "senha_comunicacao")
    private String senhaComunicacao;

    @Column(name = "data_inicio")
    private Instant dataInicio;

    @Column(name = "situacao")
    private Boolean situacao;

    @Column(name = "versao_terminal")
    private String versaoTerminal;

    @Column(name = "muda_codigo")
    private Long mudaCodigo;

    @Column(name = "data_entrada")
    private Instant dataEntrada;

    @Column(name = "numero_fonte")
    private Long numeroFonte;

    @Column(name = "codigo_autorizacao")
    private Long codigoAutorizacao;

    @NotNull
    @Column(name = "imei", nullable = false)
    private String imei;

    @Column(name = "email")
    private String email;

    @Column(name = "codigo_banca")
    private Long codigoBanca;

    @ManyToOne
    @JsonIgnoreProperties("terminals")
    private Revendedor revendedor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTelefoneChipe() {
        return telefoneChipe;
    }

    public Terminal telefoneChipe(Long telefoneChipe) {
        this.telefoneChipe = telefoneChipe;
        return this;
    }

    public void setTelefoneChipe(Long telefoneChipe) {
        this.telefoneChipe = telefoneChipe;
    }

    public String getSerial() {
        return serial;
    }

    public Terminal serial(String serial) {
        this.serial = serial;
        return this;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public Terminal menssagem(String menssagem) {
        this.menssagem = menssagem;
        return this;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }

    public String getSenhaComunicacao() {
        return senhaComunicacao;
    }

    public Terminal senhaComunicacao(String senhaComunicacao) {
        this.senhaComunicacao = senhaComunicacao;
        return this;
    }

    public void setSenhaComunicacao(String senhaComunicacao) {
        this.senhaComunicacao = senhaComunicacao;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public Terminal dataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Boolean isSituacao() {
        return situacao;
    }

    public Terminal situacao(Boolean situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public String getVersaoTerminal() {
        return versaoTerminal;
    }

    public Terminal versaoTerminal(String versaoTerminal) {
        this.versaoTerminal = versaoTerminal;
        return this;
    }

    public void setVersaoTerminal(String versaoTerminal) {
        this.versaoTerminal = versaoTerminal;
    }

    public Long getMudaCodigo() {
        return mudaCodigo;
    }

    public Terminal mudaCodigo(Long mudaCodigo) {
        this.mudaCodigo = mudaCodigo;
        return this;
    }

    public void setMudaCodigo(Long mudaCodigo) {
        this.mudaCodigo = mudaCodigo;
    }

    public Instant getDataEntrada() {
        return dataEntrada;
    }

    public Terminal dataEntrada(Instant dataEntrada) {
        this.dataEntrada = dataEntrada;
        return this;
    }

    public void setDataEntrada(Instant dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Long getNumeroFonte() {
        return numeroFonte;
    }

    public Terminal numeroFonte(Long numeroFonte) {
        this.numeroFonte = numeroFonte;
        return this;
    }

    public void setNumeroFonte(Long numeroFonte) {
        this.numeroFonte = numeroFonte;
    }

    public Long getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public Terminal codigoAutorizacao(Long codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
        return this;
    }

    public void setCodigoAutorizacao(Long codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }

    public String getImei() {
        return imei;
    }

    public Terminal imei(String imei) {
        this.imei = imei;
        return this;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getEmail() {
        return email;
    }

    public Terminal email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCodigoBanca() {
        return codigoBanca;
    }

    public Terminal codigoBanca(Long codigoBanca) {
        this.codigoBanca = codigoBanca;
        return this;
    }

    public void setCodigoBanca(Long codigoBanca) {
        this.codigoBanca = codigoBanca;
    }

    public Revendedor getRevendedor() {
        return revendedor;
    }

    public Terminal revendedor(Revendedor revendedor) {
        this.revendedor = revendedor;
        return this;
    }

    public void setRevendedor(Revendedor revendedor) {
        this.revendedor = revendedor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Terminal)) {
            return false;
        }
        return id != null && id.equals(((Terminal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Terminal{" +
            "id=" + getId() +
            ", telefoneChipe=" + getTelefoneChipe() +
            ", serial='" + getSerial() + "'" +
            ", menssagem='" + getMenssagem() + "'" +
            ", senhaComunicacao='" + getSenhaComunicacao() + "'" +
            ", dataInicio='" + getDataInicio() + "'" +
            ", situacao='" + isSituacao() + "'" +
            ", versaoTerminal='" + getVersaoTerminal() + "'" +
            ", mudaCodigo=" + getMudaCodigo() +
            ", dataEntrada='" + getDataEntrada() + "'" +
            ", numeroFonte=" + getNumeroFonte() +
            ", codigoAutorizacao=" + getCodigoAutorizacao() +
            ", imei='" + getImei() + "'" +
            ", email='" + getEmail() + "'" +
            ", codigoBanca=" + getCodigoBanca() +
            "}";
    }
}
