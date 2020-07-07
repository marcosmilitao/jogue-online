package net.jogueonline.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * entity LimiteVenda{\ncodigoRevendedor Long,\nvendaDia BigDecimal,\ndebitoAtual BigDecimal,\nlimite BigDecimal\n}
 */
@ApiModel(description = "entity LimiteVenda{\ncodigoRevendedor Long,\nvendaDia BigDecimal,\ndebitoAtual BigDecimal,\nlimite BigDecimal\n}")
@Entity
@Table(name = "custom_user")
public class CustomUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "banca")
    private String banca;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @ManyToMany(mappedBy = "customUsers")
    @JsonIgnore
    private Set<Banca> bancas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public CustomUser login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getBanca() {
        return banca;
    }

    public CustomUser banca(String banca) {
        this.banca = banca;
        return this;
    }

    public void setBanca(String banca) {
        this.banca = banca;
    }

    public User getUser() {
        return user;
    }

    public CustomUser user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Banca> getBancas() {
        return bancas;
    }

    public CustomUser bancas(Set<Banca> bancas) {
        this.bancas = bancas;
        return this;
    }

    public CustomUser addBanca(Banca banca) {
        this.bancas.add(banca);
        banca.getCustomUsers().add(this);
        return this;
    }

    public CustomUser removeBanca(Banca banca) {
        this.bancas.remove(banca);
        banca.getCustomUsers().remove(this);
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
        if (!(o instanceof CustomUser)) {
            return false;
        }
        return id != null && id.equals(((CustomUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CustomUser{" +
            "id=" + getId() +
            ", login='" + getLogin() + "'" +
            ", banca='" + getBanca() + "'" +
            "}";
    }
}
