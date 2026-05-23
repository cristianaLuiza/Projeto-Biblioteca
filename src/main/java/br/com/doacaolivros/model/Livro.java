package br.com.doacaolivros.model;

public class Livro {
    private int idLivro;
    private String titulo;
    private String genero;
    private String sinopse;
    private String autor;
    private String isbn;
    private String edicao;
    private Integer anoPublicacao;
    private int statusId;
    private int doadorId;

    public Livro() {
    }

    public Livro(int idLivro, String titulo, String genero, String sinopse, String autor, String isbn,
                 String edicao, Integer anoPublicacao, int statusId, int doadorId) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopse = sinopse;
        this.autor = autor;
        this.isbn = isbn;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.statusId = statusId;
        this.doadorId = doadorId;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getDoadorId() {
        return doadorId;
    }

    public void setDoadorId(int doadorId) {
        this.doadorId = doadorId;
    }
}
