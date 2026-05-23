package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Receptor;
import br.com.doacaolivros.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReceptorDao implements CrudDao<Receptor> {
    @Override
    public int inserir(Receptor receptor) throws SQLException {
        String sql = "INSERT INTO Receptores (pessoa_id) VALUES (?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, receptor.getPessoaId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    receptor.setIdReceptor(rs.getInt(1));
                }
            }
        }

        return receptor.getIdReceptor();
    }

    @Override
    public Receptor buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_receptor, pessoa_id FROM Receptores WHERE id_receptor = ?";

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
    public List<Receptor> listarTodos() throws SQLException {
        String sql = "SELECT id_receptor, pessoa_id FROM Receptores ORDER BY id_receptor";
        List<Receptor> receptores = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                receptores.add(mapear(rs));
            }
        }

        return receptores;
    }

    @Override
    public boolean atualizar(Receptor receptor) throws SQLException {
        String sql = "UPDATE Receptores SET pessoa_id = ? WHERE id_receptor = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, receptor.getPessoaId());
            stmt.setInt(2, receptor.getIdReceptor());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Receptores WHERE id_receptor = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Receptor mapear(ResultSet rs) throws SQLException {
        return new Receptor(
                rs.getInt("id_receptor"),
                rs.getInt("pessoa_id")
        );
    }
}
