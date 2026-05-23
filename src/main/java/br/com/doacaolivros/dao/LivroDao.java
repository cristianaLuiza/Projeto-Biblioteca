package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Livro;
import br.com.doacaolivros.util.Conexao;
import br.com.doacaolivros.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivroDao implements CrudDao<Livro> {
    @Override
    public int inserir(Livro livro) throws SQLException {
        String sql = "INSERT INTO Livros (titulo, genero, sinopse, autor, isbn, edicao, ano_publicacao, status_id, doador_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preencherStatement(stmt, livro);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    livro.setIdLivro(rs.getInt(1));
                }
            }
        }

        return livro.getIdLivro();
    }

    @Override
    public Livro buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_livro, titulo, genero, sinopse, autor, isbn, edicao, ano_publicacao, status_id, doador_id "
                + "FROM Livros WHERE id_livro = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }

        return null;
    }

    @Override
    public List<Livro> listarTodos() throws SQLException {
        String sql = "SELECT id_livro, titulo, genero, sinopse, autor, isbn, edicao, ano_publicacao, status_id, doador_id "
                + "FROM Livros ORDER BY id_livro";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                livros.add(mapear(rs));
            }
        }

        return livros;
    }

    @Override
    public boolean atualizar(Livro livro) throws SQLException {
        String sql = "UPDATE Livros SET titulo = ?, genero = ?, sinopse = ?, autor = ?, isbn = ?, edicao = ?, "
                + "ano_publicacao = ?, status_id = ?, doador_id = ? WHERE id_livro = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            preencherStatement(stmt, livro);
            stmt.setInt(10, livro.getIdLivro());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Livros WHERE id_livro = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private void preencherStatement(PreparedStatement stmt, Livro livro) throws SQLException {
        stmt.setString(1, livro.getTitulo());
        stmt.setString(2, livro.getGenero());
        stmt.setString(3, livro.getSinopse());
        stmt.setString(4, livro.getAutor());
        stmt.setString(5, livro.getIsbn());
        stmt.setString(6, livro.getEdicao());
        JdbcUtils.setIntegerOrNull(stmt, 7, livro.getAnoPublicacao());
        stmt.setInt(8, livro.getStatusId());
        stmt.setInt(9, livro.getDoadorId());
    }

    private Livro mapear(ResultSet rs) throws SQLException {
        int ano = rs.getInt("ano_publicacao");
        Integer anoPublicacao = rs.wasNull() ? null : ano;

        return new Livro(
                rs.getInt("id_livro"),
                rs.getString("titulo"),
                rs.getString("genero"),
                rs.getString("sinopse"),
                rs.getString("autor"),
                rs.getString("isbn"),
                rs.getString("edicao"),
                anoPublicacao,
                rs.getInt("status_id"),
                rs.getInt("doador_id")
        );
    }
}
