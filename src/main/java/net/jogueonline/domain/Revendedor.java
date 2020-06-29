package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A Revendedor.
 */
@Entity
@Table(name = "revendedor")
public class Revendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @NotNull
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "tipo_coletor")
    private String tipoColetor;

    @Column(name = "serial_coletor")
    private String serialColetor;

    @Column(name = "nome_comercial")
    private String nomeComercial;

    @Column(name = "situacao")
    private Boolean situacao;

    @Column(name = "saldo", precision = 21, scale = 2)
    private BigDecimal saldo;

    @Column(name = "senha")
    private String senha;

    @Column(name = "data")
    private Instant data;

    @ManyToOne
    @JsonIgnoreProperties("revendedors")
    private Promotor promotor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Revendedor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public Revendedor cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Revendedor estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public Revendedor telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public Revendedor tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoColetor() {
        return tipoColetor;
    }

    public Revendedor tipoColetor(String tipoColetor) {
        this.tipoColetor = tipoColetor;
        return this;
    }

    public void setTipoColetor(String tipoColetor) {
        this.tipoColetor = tipoColetor;
    }

    public String getSerialColetor() {
        return serialColetor;
    }

    public Revendedor serialColetor(String serialColetor) {
        this.serialColetor = serialColetor;
        return this;
    }

    public void setSerialColetor(String serialColetor) {
        this.serialColetor = serialColetor;
    }

    public String getNomeComercial() {
        return nomeComercial;
    }

    public Revendedor nomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
        return this;
    }

    public void setNomeComercial(String nomeComercial) {
        this.nomeComercial = nomeComercial;
    }

    public Boolean isSituacao() {
        return situacao;
    }

    public Revendedor situacao(Boolean situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(Boolean situacao) {
        this.situacao = situacao;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Revendedor saldo(BigDecimal saldo) {
        this.saldo = saldo;
        return this;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public Revendedor senha(String senha) {
        this.senha = senha;
        return this;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Instant getData() {
        return data;
    }

    public Revendedor data(Instant data) {
        this.data = data;
        return this;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public Promotor getPromotor() {
        return promotor;
    }

    public Revendedor promotor(Promotor promotor) {
        this.promotor = promotor;
        return this;
    }

    public void setPromotor(Promotor promotor) {
        this.promotor = promotor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Revendedor)) {
            return false;
        }
        return id != null && id.equals(((Revendedor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Revendedor{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", tipoColetor='" + getTipoColetor() + "'" +
            ", serialColetor='" + getSerialColetor() + "'" +
            ", nomeComercial='" + getNomeComercial() + "'" +
            ", situacao='" + isSituacao() + "'" +
            ", saldo=" + getSaldo() +
            ", senha='" + getSenha() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
