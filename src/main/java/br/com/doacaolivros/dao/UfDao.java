package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Uf;
import br.com.doacaolivros.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UfDao implements CrudDao<Uf> {
    @Override
    public int inserir(Uf uf) throws SQLException {
        String sql = "INSERT INTO Ufs (sigla, nome) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, uf.getSigla());
            stmt.setString(2, uf.getNome());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    uf.setIdUf(rs.getInt(1));
                }
            }
        }

        return uf.getIdUf();
    }

    @Override
    public Uf buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_uf, sigla, nome FROM Ufs WHERE id_uf = ?";

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
    public List<Uf> listarTodos() throws SQLException {
        String sql = "SELECT id_uf, sigla, nome FROM Ufs ORDER BY id_uf";
        List<Uf> ufs = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ufs.add(mapear(rs));
            }
        }

        return ufs;
    }

    @Override
    public boolean atualizar(Uf uf) throws SQLException {
        String sql = "UPDATE Ufs SET sigla = ?, nome = ? WHERE id_uf = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, uf.getSigla());
            stmt.setString(2, uf.getNome());
            stmt.setInt(3, uf.getIdUf());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Ufs WHERE id_uf = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Uf mapear(ResultSet rs) throws SQLException {
        return new Uf(
                rs.getInt("id_uf"),
                rs.getString("sigla"),
                rs.getString("nome")
        );
    }
}
