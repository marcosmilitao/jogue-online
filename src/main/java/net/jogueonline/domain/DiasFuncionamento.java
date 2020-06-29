package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A DiasFuncionamento.
 */
@Entity
@Table(name = "dias_funcionamento")
public class DiasFuncionamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "dia", nullable = false)
    private String dia;

    @ManyToMany(mappedBy = "diasFuncionamentos")
    @JsonIgnore
    private Set<Loteria> loterias = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public DiasFuncionamento dia(String dia) {
        this.dia = dia;
        return this;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Set<Loteria> getLoterias() {
        return loterias;
    }

    public DiasFuncionamento loterias(Set<Loteria> loterias) {
        this.loterias = loterias;
        return this;
    }

    public DiasFuncionamento addLoteria(Loteria loteria) {
        this.loterias.add(loteria);
        loteria.getDiasFuncionamentos().add(this);
        return this;
    }

    public DiasFuncionamento removeLoteria(Loteria loteria) {
        this.loterias.remove(loteria);
        loteria.getDiasFuncionamentos().remove(this);
        return this;
    }

    public void setLoterias(Set<Loteria> loterias) {
        this.loterias = loterias;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DiasFuncionamento)) {
            return false;
        }
        return id != null && id.equals(((DiasFuncionamento) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DiasFuncionamento{" +
            "id=" + getId() +
            ", dia='" + getDia() + "'" +
            "}";
    }
}
