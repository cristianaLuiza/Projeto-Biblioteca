package br.com.doacaolivros.model;

public class Uf {
    private int idUf;
    private String sigla;
    private String nome;

    public Uf() {
    }

    public Uf(int idUf, String sigla, String nome) {
        this.idUf = idUf;
        this.sigla = sigla;
        this.nome = nome;
    }

    public int getIdUf() {
        return idUf;
    }

    public void setIdUf(int idUf) {
        this.idUf = idUf;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
