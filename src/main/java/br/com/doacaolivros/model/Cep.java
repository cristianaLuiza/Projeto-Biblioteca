package br.com.doacaolivros.model;

public class Cep {
    private int idCep;
    private String numero;
    private int cidadeId;

    public Cep() {
    }

    public Cep(int idCep, String numero, int cidadeId) {
        this.idCep = idCep;
        this.numero = numero;
        this.cidadeId = cidadeId;
    }

    public int getIdCep() {
        return idCep;
    }

    public void setIdCep(int idCep) {
        this.idCep = idCep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(int cidadeId) {
        this.cidadeId = cidadeId;
    }
}
