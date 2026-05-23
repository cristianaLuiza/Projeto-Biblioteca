package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Interesse;
import br.com.doacaolivros.util.Conexao;
import br.com.doacaolivros.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InteresseDao implements CrudDao<Interesse> {
    @Override
    public int inserir(Interesse interesse) throws SQLException {
        String sql = "INSERT INTO Interesse (livro_id, receptor_id, status_id) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, interesse.getLivroId());
            stmt.setInt(2, interesse.getReceptorId());
            stmt.setInt(3, interesse.getStatusId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    interesse.setIdInteresse(rs.getInt(1));
                }
            }
        }

        return interesse.getIdInteresse();
    }

    @Override
    public Interesse buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_interesse, livro_id, receptor_id, data_interesse, status_id FROM Interesse WHERE id_interesse = ?";

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
    public List<Interesse> listarTodos() throws SQLException {
        String sql = "SELECT id_interesse, livro_id, receptor_id, data_interesse, status_id FROM Interesse ORDER BY id_interesse";
        List<Interesse> interesses = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                interesses.add(mapear(rs));
            }
        }

        return interesses;
    }

    @Override
    public boolean atualizar(Interesse interesse) throws SQLException {
        String sql = "UPDATE Interesse SET livro_id = ?, receptor_id = ?, data_interesse = ?, status_id = ? WHERE id_interesse = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, interesse.getLivroId());
            stmt.setInt(2, interesse.getReceptorId());
            JdbcUtils.setLocalDateTimeOrNull(stmt, 3, interesse.getDataInteresse());
            stmt.setInt(4, interesse.getStatusId());
            stmt.setInt(5, interesse.getIdInteresse());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Interesse WHERE id_interesse = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Interesse mapear(ResultSet rs) throws SQLException {
        return new Interesse(
                rs.getInt("id_interesse"),
                rs.getInt("livro_id"),
                rs.getInt("receptor_id"),
                JdbcUtils.getLocalDateTime(rs, "data_interesse"),
                rs.getInt("status_id")
        );
    }
}
