package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Loteria.
 */
@Entity
@Table(name = "loteria")
public class Loteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "hora_encerramento")
    private String horaEncerramento;

    @NotNull
    @Column(name = "premiacao_inicio", nullable = false)
    private String premiacaoInicio;

    @Column(name = "status")
    private Boolean status;

    @NotNull
    @Column(name = "limite_premio", precision = 21, scale = 2, nullable = false)
    private BigDecimal limitePremio;

    @Column(name = "data")
    private Instant data;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "hora", nullable = false)
    private Integer hora;

    @NotNull
    @Column(name = "minuto", nullable = false)
    private Integer minuto;

    @Column(name = "disponivel")
    private Boolean disponivel;

    @NotNull
    @Column(name = "descricao_completa", nullable = false)
    private String descricaoCompleta;

    @ManyToMany
    @JoinTable(name = "loteria_dias_funcionamento",
               joinColumns = @JoinColumn(name = "loteria_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "dias_funcionamento_id", referencedColumnName = "id"))
    private Set<DiasFuncionamento> diasFuncionamentos = new HashSet<>();

    @ManyToMany(mappedBy = "loterias")
    @JsonIgnore
    private Set<Banca> bancas = new HashSet<>();

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

    public Loteria nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHoraEncerramento() {
        return horaEncerramento;
    }

    public Loteria horaEncerramento(String horaEncerramento) {
        this.horaEncerramento = horaEncerramento;
        return this;
    }

    public void setHoraEncerramento(String horaEncerramento) {
        this.horaEncerramento = horaEncerramento;
    }

    public String getPremiacaoInicio() {
        return premiacaoInicio;
    }

    public Loteria premiacaoInicio(String premiacaoInicio) {
        this.premiacaoInicio = premiacaoInicio;
        return this;
    }

    public void setPremiacaoInicio(String premiacaoInicio) {
        this.premiacaoInicio = premiacaoInicio;
    }

    public Boolean isStatus() {
        return status;
    }

    public Loteria status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigDecimal getLimitePremio() {
        return limitePremio;
    }

    public Loteria limitePremio(BigDecimal limitePremio) {
        this.limitePremio = limitePremio;
        return this;
    }

    public void setLimitePremio(BigDecimal limitePremio) {
        this.limitePremio = limitePremio;
    }

    public Instant getData() {
        return data;
    }

    public Loteria data(Instant data) {
        this.data = data;
        return this;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Loteria codigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Loteria descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getHora() {
        return hora;
    }

    public Loteria hora(Integer hora) {
        this.hora = hora;
        return this;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public Loteria minuto(Integer minuto) {
        this.minuto = minuto;
        return this;
    }

    public void setMinuto(Integer minuto) {
        this.minuto = minuto;
    }

    public Boolean isDisponivel() {
        return disponivel;
    }

    public Loteria disponivel(Boolean disponivel) {
        this.disponivel = disponivel;
        return this;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public Loteria descricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
        return this;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public Set<DiasFuncionamento> getDiasFuncionamentos() {
        return diasFuncionamentos;
    }

    public Loteria diasFuncionamentos(Set<DiasFuncionamento> diasFuncionamentos) {
        this.diasFuncionamentos = diasFuncionamentos;
        return this;
    }

    public Loteria addDiasFuncionamento(DiasFuncionamento diasFuncionamento) {
        this.diasFuncionamentos.add(diasFuncionamento);
        diasFuncionamento.getLoterias().add(this);
        return this;
    }

    public Loteria removeDiasFuncionamento(DiasFuncionamento diasFuncionamento) {
        this.diasFuncionamentos.remove(diasFuncionamento);
        diasFuncionamento.getLoterias().remove(this);
        return this;
    }

    public void setDiasFuncionamentos(Set<DiasFuncionamento> diasFuncionamentos) {
        this.diasFuncionamentos = diasFuncionamentos;
    }

    public Set<Banca> getBancas() {
        return bancas;
    }

    public Loteria bancas(Set<Banca> bancas) {
        this.bancas = bancas;
        return this;
    }

    public Loteria addBanca(Banca banca) {
        this.bancas.add(banca);
        banca.getLoterias().add(this);
        return this;
    }

    public Loteria removeBanca(Banca banca) {
        this.bancas.remove(banca);
        banca.getLoterias().remove(this);
        return this;
    }

    public void setBancas(Set<Banca> bancas) {
        this.bancas = bancas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Loteria)) {
            return false;
        }
        return id != null && id.equals(((Loteria) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Loteria{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", horaEncerramento='" + getHoraEncerramento() + "'" +
            ", premiacaoInicio='" + getPremiacaoInicio() + "'" +
            ", status='" + isStatus() + "'" +
            ", limitePremio=" + getLimitePremio() +
            ", data='" + getData() + "'" +
            ", codigo=" + getCodigo() +
            ", descricao='" + getDescricao() + "'" +
            ", hora=" + getHora() +
            ", minuto=" + getMinuto() +
            ", disponivel='" + isDisponivel() + "'" +
            ", descricaoCompleta='" + getDescricaoCompleta() + "'" +
            "}";
    }
}
