package net.jogueonline.domain;

import java.io.Serializable;

public class TerminalUserInfo implements Serializable {

    private String nome;
    private Long id;
    private String serialTerminal;
    private String email;
    private String revendedor;

    public String getSerialTerminal() {
        return serialTerminal;
    }

    public void setSerialTerminal(String serialTerminal) {
        this.serialTerminal = serialTerminal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRevendedor() {
        return revendedor;
    }

    public void setRevendedor(String revendedor) {
        this.revendedor = revendedor;
    }

    @Override
    public String toString() {
        return "TerminalUserInfo{" +
            "nome='" + nome + '\'' +
            ", id=" + id +
            ", serialTerminal='" + serialTerminal + '\'' +
            ", email='" + email + '\'' +
            ", revendedor='" + revendedor + '\'' +
            '}';
    }
}
