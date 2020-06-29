package net.jogueonline.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * entity RevendedorStatus{\ntotalDevedor Long,\ntotalAtivos Long,\ncomMovimentacao Long,\nsemMovimentacao Long,\ndata Instant,\ntotalRevenderes Long\n}
 */
@ApiModel(description = "entity RevendedorStatus{\ntotalDevedor Long,\ntotalAtivos Long,\ncomMovimentacao Long,\nsemMovimentacao Long,\ndata Instant,\ntotalRevenderes Long\n}")
@Entity
@Table(name = "banca")
public class Banca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "estado")
    private String estado;

    @Column(name = "limite_descarga", precision = 21, scale = 2)
    private BigDecimal limiteDescarga;

    @Column(name = "limite_premiacao", precision = 21, scale = 2)
    private BigDecimal limitePremiacao;

    @Column(name = "limite_baixa_automatica", precision = 21, scale = 2)
    private BigDecimal limiteBaixaAutomatica;

    @Column(name = "limite_horario_encerramento")
    private Long limiteHorarioEncerramento;

    @Column(name = "mensagem_pule_1")
    private String mensagemPule1;

    @Column(name = "mensagem_pule_2")
    private String mensagemPule2;

    @Column(name = "mensagem_pule_3")
    private String mensagemPule3;

    @Column(name = "data")
    private Instant data;

    @OneToMany(mappedBy = "banca")
    private Set<Promotor> promotors = new HashSet<>();

    @OneToMany(mappedBy = "banca")
    private Set<Terminal> cadastroTerminals = new HashSet<>();

    @OneToMany(mappedBy = "banca")
    private Set<Aposta> apostas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "banca_modalidade",
               joinColumns = @JoinColumn(name = "banca_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "modalidade_id", referencedColumnName = "id"))
    private Set<Modalidade> modalidades = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "banca_loteria",
               joinColumns = @JoinColumn(name = "banca_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "loteria_id", referencedColumnName = "id"))
    private Set<Loteria> loterias = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "banca_custom_user",
               joinColumns = @JoinColumn(name = "banca_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "custom_user_id", referencedColumnName = "id"))
    private Set<CustomUser> customUsers = new HashSet<>();

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

    public Banca codigo(Long codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public Banca nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public Banca cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public Banca telefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public Banca estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getLimiteDescarga() {
        return limiteDescarga;
    }

    public Banca limiteDescarga(BigDecimal limiteDescarga) {
        this.limiteDescarga = limiteDescarga;
        return this;
    }

    public void setLimiteDescarga(BigDecimal limiteDescarga) {
        this.limiteDescarga = limiteDescarga;
    }

    public BigDecimal getLimitePremiacao() {
        return limitePremiacao;
    }

    public Banca limitePremiacao(BigDecimal limitePremiacao) {
        this.limitePremiacao = limitePremiacao;
        return this;
    }

    public void setLimitePremiacao(BigDecimal limitePremiacao) {
        this.limitePremiacao = limitePremiacao;
    }

    public BigDecimal getLimiteBaixaAutomatica() {
        return limiteBaixaAutomatica;
    }

    public Banca limiteBaixaAutomatica(BigDecimal limiteBaixaAutomatica) {
        this.limiteBaixaAutomatica = limiteBaixaAutomatica;
        return this;
    }

    public void setLimiteBaixaAutomatica(BigDecimal limiteBaixaAutomatica) {
        this.limiteBaixaAutomatica = limiteBaixaAutomatica;
    }

    public Long getLimiteHorarioEncerramento() {
        return limiteHorarioEncerramento;
    }

    public Banca limiteHorarioEncerramento(Long limiteHorarioEncerramento) {
        this.limiteHorarioEncerramento = limiteHorarioEncerramento;
        return this;
    }

    public void setLimiteHorarioEncerramento(Long limiteHorarioEncerramento) {
        this.limiteHorarioEncerramento = limiteHorarioEncerramento;
    }

    public String getMensagemPule1() {
        return mensagemPule1;
    }

    public Banca mensagemPule1(String mensagemPule1) {
        this.mensagemPule1 = mensagemPule1;
        return this;
    }

    public void setMensagemPule1(String mensagemPule1) {
        this.mensagemPule1 = mensagemPule1;
    }

    public String getMensagemPule2() {
        return mensagemPule2;
    }

    public Banca mensagemPule2(String mensagemPule2) {
        this.mensagemPule2 = mensagemPule2;
        return this;
    }

    public void setMensagemPule2(String mensagemPule2) {
        this.mensagemPule2 = mensagemPule2;
    }

    public String getMensagemPule3() {
        return mensagemPule3;
    }

    public Banca mensagemPule3(String mensagemPule3) {
        this.mensagemPule3 = mensagemPule3;
        return this;
    }

    public void setMensagemPule3(String mensagemPule3) {
        this.mensagemPule3 = mensagemPule3;
    }

    public Instant getData() {
        return data;
    }

    public Banca data(Instant data) {
        this.data = data;
        return this;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public Set<Promotor> getPromotors() {
        return promotors;
    }

    public Banca promotors(Set<Promotor> promotors) {
        this.promotors = promotors;
        return this;
    }

    public Banca addPromotor(Promotor promotor) {
        this.promotors.add(promotor);
        promotor.setBanca(this);
        return this;
    }

    public Banca removePromotor(Promotor promotor) {
        this.promotors.remove(promotor);
        promotor.setBanca(null);
        return this;
    }

    public void setPromotors(Set<Promotor> promotors) {
        this.promotors = promotors;
    }

    public Set<Terminal> getCadastroTerminals() {
        return cadastroTerminals;
    }

    public Banca cadastroTerminals(Set<Terminal> terminals) {
        this.cadastroTerminals = terminals;
        return this;
    }

    public Banca addCadastroTerminal(Terminal terminal) {
        this.cadastroTerminals.add(terminal);
        terminal.setBanca(this);
        return this;
    }

    public Banca removeCadastroTerminal(Terminal terminal) {
        this.cadastroTerminals.remove(terminal);
        terminal.setBanca(null);
        return this;
    }

    public void setCadastroTerminals(Set<Terminal> terminals) {
        this.cadastroTerminals = terminals;
    }

    public Set<Aposta> getApostas() {
        return apostas;
    }

    public Banca apostas(Set<Aposta> apostas) {
        this.apostas = apostas;
        return this;
    }

    public Banca addAposta(Aposta aposta) {
        this.apostas.add(aposta);
        aposta.setBanca(this);
        return this;
    }

    public Banca removeAposta(Aposta aposta) {
        this.apostas.remove(aposta);
        aposta.setBanca(null);
        return this;
    }

    public void setApostas(Set<Aposta> apostas) {
        this.apostas = apostas;
    }

    public Set<Modalidade> getModalidades() {
        return modalidades;
    }

    public Banca modalidades(Set<Modalidade> modalidades) {
        this.modalidades = modalidades;
        return this;
    }

    public Banca addModalidade(Modalidade modalidade) {
        this.modalidades.add(modalidade);
        modalidade.getBancas().add(this);
        return this;
    }

    public Banca removeModalidade(Modalidade modalidade) {
        this.modalidades.remove(modalidade);
        modalidade.getBancas().remove(this);
        return this;
    }

    public void setModalidades(Set<Modalidade> modalidades) {
        this.modalidades = modalidades;
    }

    public Set<Loteria> getLoterias() {
        return loterias;
    }

    public Banca loterias(Set<Loteria> loterias) {
        this.loterias = loterias;
        return this;
    }

    public Banca addLoteria(Loteria loteria) {
        this.loterias.add(loteria);
        loteria.getBancas().add(this);
        return this;
    }

    public Banca removeLoteria(Loteria loteria) {
        this.loterias.remove(loteria);
        loteria.getBancas().remove(this);
        return this;
    }

    public void setLoterias(Set<Loteria> loterias) {
        this.loterias = loterias;
    }

    public Set<CustomUser> getCustomUsers() {
        return customUsers;
    }

    public Banca customUsers(Set<CustomUser> customUsers) {
        this.customUsers = customUsers;
        return this;
    }

    public Banca addCustomUser(CustomUser customUser) {
        this.customUsers.add(customUser);
        customUser.getBancas().add(this);
        return this;
    }

    public Banca removeCustomUser(CustomUser customUser) {
        this.customUsers.remove(customUser);
        customUser.getBancas().remove(this);
        return this;
    }

    public void setCustomUsers(Set<CustomUser> customUsers) {
        this.customUsers = customUsers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Banca)) {
            return false;
        }
        return id != null && id.equals(((Banca) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Banca{" +
            "id=" + getId() +
            ", codigo=" + getCodigo() +
            ", nome='" + getNome() + "'" +
            ", cidade='" + getCidade() + "'" +
            ", telefone='" + getTelefone() + "'" +
            ", estado='" + getEstado() + "'" +
            ", limiteDescarga=" + getLimiteDescarga() +
            ", limitePremiacao=" + getLimitePremiacao() +
            ", limiteBaixaAutomatica=" + getLimiteBaixaAutomatica() +
            ", limiteHorarioEncerramento=" + getLimiteHorarioEncerramento() +
            ", mensagemPule1='" + getMensagemPule1() + "'" +
            ", mensagemPule2='" + getMensagemPule2() + "'" +
            ", mensagemPule3='" + getMensagemPule3() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
