package br.com.doacaolivros.model;

import java.time.LocalDateTime;

public class Interesse {
    private int idInteresse;
    private int livroId;
    private int receptorId;
    private LocalDateTime dataInteresse;
    private int statusId;

    public Interesse() {
    }

    public Interesse(int idInteresse, int livroId, int receptorId, LocalDateTime dataInteresse, int statusId) {
        this.idInteresse = idInteresse;
        this.livroId = livroId;
        this.receptorId = receptorId;
        this.dataInteresse = dataInteresse;
        this.statusId = statusId;
    }

    public int getIdInteresse() {
        return idInteresse;
    }

    public void setIdInteresse(int idInteresse) {
        this.idInteresse = idInteresse;
    }

    public int getLivroId() {
        return livroId;
    }

    public void setLivroId(int livroId) {
        this.livroId = livroId;
    }

    public int getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(int receptorId) {
        this.receptorId = receptorId;
    }

    public LocalDateTime getDataInteresse() {
        return dataInteresse;
    }

    public void setDataInteresse(LocalDateTime dataInteresse) {
        this.dataInteresse = dataInteresse;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
