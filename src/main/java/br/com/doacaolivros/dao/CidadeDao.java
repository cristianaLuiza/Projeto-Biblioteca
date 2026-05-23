package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Cidade;
import br.com.doacaolivros.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CidadeDao implements CrudDao<Cidade> {
    @Override
    public int inserir(Cidade cidade) throws SQLException {
        String sql = "INSERT INTO Cidades (nome, uf_id) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getUfId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cidade.setIdCidade(rs.getInt(1));
                }
            }
        }

        return cidade.getIdCidade();
    }

    @Override
    public Cidade buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_cidade, nome, uf_id FROM Cidades WHERE id_cidade = ?";

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
    public List<Cidade> listarTodos() throws SQLException {
        String sql = "SELECT id_cidade, nome, uf_id FROM Cidades ORDER BY id_cidade";
        List<Cidade> cidades = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cidades.add(mapear(rs));
            }
        }

        return cidades;
    }

    @Override
    public boolean atualizar(Cidade cidade) throws SQLException {
        String sql = "UPDATE Cidades SET nome = ?, uf_id = ? WHERE id_cidade = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getUfId());
            stmt.setInt(3, cidade.getIdCidade());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Cidades WHERE id_cidade = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Cidade mapear(ResultSet rs) throws SQLException {
        return new Cidade(
                rs.getInt("id_cidade"),
                rs.getString("nome"),
                rs.getInt("uf_id")
        );
    }
}
