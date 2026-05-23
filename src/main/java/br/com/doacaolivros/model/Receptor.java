package br.com.doacaolivros.model;

public class Receptor {
    private int idReceptor;
    private int pessoaId;

    public Receptor() {
    }

    public Receptor(int idReceptor, int pessoaId) {
        this.idReceptor = idReceptor;
        this.pessoaId = pessoaId;
    }

    public int getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(int idReceptor) {
        this.idReceptor = idReceptor;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }
}
