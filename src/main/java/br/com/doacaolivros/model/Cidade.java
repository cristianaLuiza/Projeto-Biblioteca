package br.com.doacaolivros.model;

public class Cidade {
    private int idCidade;
    private String nome;
    private int ufId;

    public Cidade() {
    }

    public Cidade(int idCidade, String nome, int ufId) {
        this.idCidade = idCidade;
        this.nome = nome;
        this.ufId = ufId;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getUfId() {
        return ufId;
    }

    public void setUfId(int ufId) {
        this.ufId = ufId;
    }
}
