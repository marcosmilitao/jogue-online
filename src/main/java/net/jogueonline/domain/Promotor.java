package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Promotor.
 */
@Entity
@Table(name = "promotor")
public class Promotor implements Serializable {

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

    @Column(name = "comissao")
    private Long comissao;

    @Column(name = "data")
    private Instant data;

    @OneToMany(mappedBy = "promotor")
    private Set<Revendedor> revendedors = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("promotors")
    private Banca banca;

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

    public Promotor nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public Promotor cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Promotor estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public Promotor telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getComissao() {
        return comissao;
    }

    public Promotor comissao(Long comissao) {
        this.comissao = comissao;
        return this;
    }

    public void setComissao(Long comissao) {
        this.comissao = comissao;
    }

    public Instant getData() {
        return data;
    }

    public Promotor data(Instant data) {
        this.data = data;
        return this;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public Set<Revendedor> getRevendedors() {
        return revendedors;
    }

    public Promotor revendedors(Set<Revendedor> revendedors) {
        this.revendedors = revendedors;
        return this;
    }

    public Promotor addRevendedor(Revendedor revendedor) {
        this.revendedors.add(revendedor);
        revendedor.setPromotor(this);
        return this;
    }

    public Promotor removeRevendedor(Revendedor revendedor) {
        this.revendedors.remove(revendedor);
        revendedor.setPromotor(null);
        return this;
    }

    public void setRevendedors(Set<Revendedor> revendedors) {
        this.revendedors = revendedors;
    }

    public Banca getBanca() {
        return banca;
    }

    public Promotor banca(Banca banca) {
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
        if (!(o instanceof Promotor)) {
            return false;
        }
        return id != null && id.equals(((Promotor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Promotor{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", comissao=" + getComissao() +
            ", data='" + getData() + "'" +
            "}";
    }
}
