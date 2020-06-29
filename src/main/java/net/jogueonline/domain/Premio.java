package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Premio.
 */
@Entity
@Table(name = "premio")
public class Premio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "premios")
    @JsonIgnore
    private Set<Modalidade> modalidades = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public Premio codigo(Long codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public Premio nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Modalidade> getModalidades() {
        return modalidades;
    }

    public Premio modalidades(Set<Modalidade> modalidades) {
        this.modalidades = modalidades;
        return this;
    }

    public Premio addModalidade(Modalidade modalidade) {
        this.modalidades.add(modalidade);
        modalidade.getPremios().add(this);
        return this;
    }

    public Premio removeModalidade(Modalidade modalidade) {
        this.modalidades.remove(modalidade);
        modalidade.getPremios().remove(this);
        return this;
    }

    public void setModalidades(Set<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Premio)) {
            return false;
        }
        return id != null && id.equals(((Premio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Premio{" +
            "id=" + getId() +
            ", codigo=" + getCodigo() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
