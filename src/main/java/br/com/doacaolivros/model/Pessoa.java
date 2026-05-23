package br.com.doacaolivros.model;

import java.time.LocalDateTime;

public class Pessoa {
    private int idPessoa;
    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;
    private int cepId;

    public Pessoa() {
    }

    public Pessoa(int idPessoa, String nome, String email, String senha, LocalDateTime dataCriacao, int cepId) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.cepId = cepId;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getCepId() {
        return cepId;
    }

    public void setCepId(int cepId) {
        this.cepId = cepId;
    }
}
