package br.com.doacaolivros.model;

public class Doador {
    private int idDoador;
    private int pessoaId;

    public Doador() {
    }

    public Doador(int idDoador, int pessoaId) {
        this.idDoador = idDoador;
        this.pessoaId = pessoaId;
    }

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }
}
