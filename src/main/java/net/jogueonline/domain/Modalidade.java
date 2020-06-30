package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A Modalidade.
 */
@Entity
@Table(name = "modalidade")
public class Modalidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "menor_palpite", nullable = false)
    private Integer menorPalpite;

    @NotNull
    @Column(name = "maior_palpite", nullable = false)
    private Integer maiorPalpite;

    @Column(name = "qtde_palpites")
    private Integer qtdePalpites;

    @NotNull
    @Column(name = "qtde_minima_palpites", nullable = false)
    private Integer qtdeMinimaPalpites;

    @NotNull
    @Column(name = "qtde_caracteres", nullable = false)
    private Integer qtdeCaracteres;

    @NotNull
    @Column(name = "qtde_minima_caracteres", nullable = false)
    private Integer qtdeMinimaCaracteres;

    @NotNull
    @Column(name = "menor_valor", precision = 21, scale = 2, nullable = false)
    private BigDecimal menorValor;

    @NotNull
    @Column(name = "maior_valor", precision = 21, scale = 2, nullable = false)
    private BigDecimal maiorValor;

    @Column(name = "maior_valor_excessao")
    private Boolean maiorValorExcessao;

    @Column(name = "repeticao")
    private Boolean repeticao;

    @Column(name = "mascara")
    private String mascara;

    @Column(name = "palpite_multiplo")
    private Boolean palpiteMultiplo;

    @Column(name = "palpite_multiplo_terminal")
    private Boolean palpiteMultiploTerminal;

    @Column(name = "ordenar")
    private Boolean ordenar;

    @Column(name = "permite_palpite_aleatorio")
    private Boolean permitePalpiteAleatorio;

    @ManyToMany(mappedBy = "modalidades")
    @JsonIgnore
    private Set<Banca> bancas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Modalidade codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public Modalidade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMenorPalpite() {
        return menorPalpite;
    }

    public Modalidade menorPalpite(Integer menorPalpite) {
        this.menorPalpite = menorPalpite;
        return this;
    }

    public void setMenorPalpite(Integer menorPalpite) {
        this.menorPalpite = menorPalpite;
    }

    public Integer getMaiorPalpite() {
        return maiorPalpite;
    }

    public Modalidade maiorPalpite(Integer maiorPalpite) {
        this.maiorPalpite = maiorPalpite;
        return this;
    }

    public void setMaiorPalpite(Integer maiorPalpite) {
        this.maiorPalpite = maiorPalpite;
    }

    public Integer getQtdePalpites() {
        return qtdePalpites;
    }

    public Modalidade qtdePalpites(Integer qtdePalpites) {
        this.qtdePalpites = qtdePalpites;
        return this;
    }

    public void setQtdePalpites(Integer qtdePalpites) {
        this.qtdePalpites = qtdePalpites;
    }

    public Integer getQtdeMinimaPalpites() {
        return qtdeMinimaPalpites;
    }

    public Modalidade qtdeMinimaPalpites(Integer qtdeMinimaPalpites) {
        this.qtdeMinimaPalpites = qtdeMinimaPalpites;
        return this;
    }

    public void setQtdeMinimaPalpites(Integer qtdeMinimaPalpites) {
        this.qtdeMinimaPalpites = qtdeMinimaPalpites;
    }

    public Integer getQtdeCaracteres() {
        return qtdeCaracteres;
    }

    public Modalidade qtdeCaracteres(Integer qtdeCaracteres) {
        this.qtdeCaracteres = qtdeCaracteres;
        return this;
    }

    public void setQtdeCaracteres(Integer qtdeCaracteres) {
        this.qtdeCaracteres = qtdeCaracteres;
    }

    public Integer getQtdeMinimaCaracteres() {
        return qtdeMinimaCaracteres;
    }

    public Modalidade qtdeMinimaCaracteres(Integer qtdeMinimaCaracteres) {
        this.qtdeMinimaCaracteres = qtdeMinimaCaracteres;
        return this;
    }

    public void setQtdeMinimaCaracteres(Integer qtdeMinimaCaracteres) {
        this.qtdeMinimaCaracteres = qtdeMinimaCaracteres;
    }

    public BigDecimal getMenorValor() {
        return menorValor;
    }

    public Modalidade menorValor(BigDecimal menorValor) {
        this.menorValor = menorValor;
        return this;
    }

    public void setMenorValor(BigDecimal menorValor) {
        this.menorValor = menorValor;
    }

    public BigDecimal getMaiorValor() {
        return maiorValor;
    }

    public Modalidade maiorValor(BigDecimal maiorValor) {
        this.maiorValor = maiorValor;
        return this;
    }

    public void setMaiorValor(BigDecimal maiorValor) {
        this.maiorValor = maiorValor;
    }

    public Boolean isMaiorValorExcessao() {
        return maiorValorExcessao;
    }

    public Modalidade maiorValorExcessao(Boolean maiorValorExcessao) {
        this.maiorValorExcessao = maiorValorExcessao;
        return this;
    }

    public void setMaiorValorExcessao(Boolean maiorValorExcessao) {
        this.maiorValorExcessao = maiorValorExcessao;
    }

    public Boolean isRepeticao() {
        return repeticao;
    }

    public Modalidade repeticao(Boolean repeticao) {
        this.repeticao = repeticao;
        return this;
    }

    public void setRepeticao(Boolean repeticao) {
        this.repeticao = repeticao;
    }

    public String getMascara() {
        return mascara;
    }

    public Modalidade mascara(String mascara) {
        this.mascara = mascara;
        return this;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public Boolean isPalpiteMultiplo() {
        return palpiteMultiplo;
    }

    public Modalidade palpiteMultiplo(Boolean palpiteMultiplo) {
        this.palpiteMultiplo = palpiteMultiplo;
        return this;
    }

    public void setPalpiteMultiplo(Boolean palpiteMultiplo) {
        this.palpiteMultiplo = palpiteMultiplo;
    }

    public Boolean isPalpiteMultiploTerminal() {
        return palpiteMultiploTerminal;
    }

    public Modalidade palpiteMultiploTerminal(Boolean palpiteMultiploTerminal) {
        this.palpiteMultiploTerminal = palpiteMultiploTerminal;
        return this;
    }

    public void setPalpiteMultiploTerminal(Boolean palpiteMultiploTerminal) {
        this.palpiteMultiploTerminal = palpiteMultiploTerminal;
    }

    public Boolean isOrdenar() {
        return ordenar;
    }

    public Modalidade ordenar(Boolean ordenar) {
        this.ordenar = ordenar;
        return this;
    }

    public void setOrdenar(Boolean ordenar) {
        this.ordenar = ordenar;
    }

    public Boolean isPermitePalpiteAleatorio() {
        return permitePalpiteAleatorio;
    }

    public Modalidade permitePalpiteAleatorio(Boolean permitePalpiteAleatorio) {
        this.permitePalpiteAleatorio = permitePalpiteAleatorio;
        return this;
    }

    public void setPermitePalpiteAleatorio(Boolean permitePalpiteAleatorio) {
        this.permitePalpiteAleatorio = permitePalpiteAleatorio;
    }

    public Set<Banca> getBancas() {
        return bancas;
    }

    public Modalidade bancas(Set<Banca> bancas) {
        this.bancas = bancas;
        return this;
    }

    public Modalidade addBanca(Banca banca) {
        this.bancas.add(banca);
        banca.getModalidades().add(this);
        return this;
    }

    public Modalidade removeBanca(Banca banca) {
        this.bancas.remove(banca);
        banca.getModalidades().remove(this);
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
        if (!(o instanceof Modalidade)) {
            return false;
        }
        return id != null && id.equals(((Modalidade) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Modalidade{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nome='" + getNome() + "'" +
            ", menorPalpite=" + getMenorPalpite() +
            ", maiorPalpite=" + getMaiorPalpite() +
            ", qtdePalpites=" + getQtdePalpites() +
            ", qtdeMinimaPalpites=" + getQtdeMinimaPalpites() +
            ", qtdeCaracteres=" + getQtdeCaracteres() +
            ", qtdeMinimaCaracteres=" + getQtdeMinimaCaracteres() +
            ", menorValor=" + getMenorValor() +
            ", maiorValor=" + getMaiorValor() +
            ", maiorValorExcessao='" + isMaiorValorExcessao() + "'" +
            ", repeticao='" + isRepeticao() + "'" +
            ", mascara='" + getMascara() + "'" +
            ", palpiteMultiplo='" + isPalpiteMultiplo() + "'" +
            ", palpiteMultiploTerminal='" + isPalpiteMultiploTerminal() + "'" +
            ", ordenar='" + isOrdenar() + "'" +
            ", permitePalpiteAleatorio='" + isPermitePalpiteAleatorio() + "'" +
            "}";
    }
}
