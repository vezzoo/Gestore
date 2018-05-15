package it.lucaVezzoli.Application;

import java.io.Serializable;

public class Account implements Serializable {
    private String nome;
    private String password;
    private String errors;

    public Account(String nome, String password, String errors) {
        this.nome = nome;
        this.password = password;
        this.errors = errors;
    }

    public String getNome() {
        return nome;
    }

    public String getPassword() {
        return password;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
