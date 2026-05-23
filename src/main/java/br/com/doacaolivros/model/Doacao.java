package br.com.doacaolivros.model;

import java.time.LocalDateTime;

public class Doacao {
    private int idDoacao;
    private int interesseId;
    private int confirmacaoDoadorId;
    private int confirmacaoReceptorId;
    private LocalDateTime dataDoacao;

    public Doacao() {
    }

    public Doacao(int idDoacao, int interesseId, int confirmacaoDoadorId, int confirmacaoReceptorId,
                  LocalDateTime dataDoacao) {
        this.idDoacao = idDoacao;
        this.interesseId = interesseId;
        this.confirmacaoDoadorId = confirmacaoDoadorId;
        this.confirmacaoReceptorId = confirmacaoReceptorId;
        this.dataDoacao = dataDoacao;
    }

    public int getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(int idDoacao) {
        this.idDoacao = idDoacao;
    }

    public int getInteresseId() {
        return interesseId;
    }

    public void setInteresseId(int interesseId) {
        this.interesseId = interesseId;
    }

    public int getConfirmacaoDoadorId() {
        return confirmacaoDoadorId;
    }

    public void setConfirmacaoDoadorId(int confirmacaoDoadorId) {
        this.confirmacaoDoadorId = confirmacaoDoadorId;
    }

    public int getConfirmacaoReceptorId() {
        return confirmacaoReceptorId;
    }

    public void setConfirmacaoReceptorId(int confirmacaoReceptorId) {
        this.confirmacaoReceptorId = confirmacaoReceptorId;
    }

    public LocalDateTime getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDateTime dataDoacao) {
        this.dataDoacao = dataDoacao;
    }
}
