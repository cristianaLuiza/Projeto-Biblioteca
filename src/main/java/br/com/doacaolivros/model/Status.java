package br.com.doacaolivros.model;

public class Status {
    private int idStatus;
    private String tipoStatus;
    private String nomeStatus;

    public Status() {
    }

    public Status(int idStatus, String tipoStatus, String nomeStatus) {
        this.idStatus = idStatus;
        this.tipoStatus = tipoStatus;
        this.nomeStatus = nomeStatus;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getTipoStatus() {
        return tipoStatus;
    }

    public void setTipoStatus(String tipoStatus) {
        this.tipoStatus = tipoStatus;
    }

    public String getNomeStatus() {
        return nomeStatus;
    }

    public void setNomeStatus(String nomeStatus) {
        this.nomeStatus = nomeStatus;
    }
}
