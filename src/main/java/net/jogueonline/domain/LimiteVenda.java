package net.jogueonline.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;

/**
 * A LimiteVenda.
 */
@Entity
@Table(name = "limite_venda")
public class LimiteVenda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "codigo_revendedor")
    private Integer codigoRevendedor;

    @Column(name = "venda_dia", precision = 21, scale = 2)
    private BigDecimal vendaDia;

    @Column(name = "debito_atual", precision = 21, scale = 2)
    private BigDecimal debitoAtual;

    @Column(name = "limite", precision = 21, scale = 2)
    private BigDecimal limite;

    @OneToOne
    @JoinColumn(unique = true)
    private Revendedor revendedor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigoRevendedor() {
        return codigoRevendedor;
    }

    public LimiteVenda codigoRevendedor(Integer codigoRevendedor) {
        this.codigoRevendedor = codigoRevendedor;
        return this;
    }

    public void setCodigoRevendedor(Integer codigoRevendedor) {
        this.codigoRevendedor = codigoRevendedor;
    }

    public BigDecimal getVendaDia() {
        return vendaDia;
    }

    public LimiteVenda vendaDia(BigDecimal vendaDia) {
        this.vendaDia = vendaDia;
        return this;
    }

    public void setVendaDia(BigDecimal vendaDia) {
        this.vendaDia = vendaDia;
    }

    public BigDecimal getDebitoAtual() {
        return debitoAtual;
    }

    public LimiteVenda debitoAtual(BigDecimal debitoAtual) {
        this.debitoAtual = debitoAtual;
        return this;
    }

    public void setDebitoAtual(BigDecimal debitoAtual) {
        this.debitoAtual = debitoAtual;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public LimiteVenda limite(BigDecimal limite) {
        this.limite = limite;
        return this;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public Revendedor getRevendedor() {
        return revendedor;
    }

    public LimiteVenda revendedor(Revendedor revendedor) {
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
        if (!(o instanceof LimiteVenda)) {
            return false;
        }
        return id != null && id.equals(((LimiteVenda) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LimiteVenda{" +
            "id=" + getId() +
            ", codigoRevendedor=" + getCodigoRevendedor() +
            ", vendaDia=" + getVendaDia() +
            ", debitoAtual=" + getDebitoAtual() +
            ", limite=" + getLimite() +
            "}";
    }
}
