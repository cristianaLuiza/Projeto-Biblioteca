package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Status;
import br.com.doacaolivros.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StatusDao implements CrudDao<Status> {
    @Override
    public int inserir(Status status) throws SQLException {
        String sql = "INSERT INTO [Status] (tipo_status, nome_status) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, status.getTipoStatus());
            stmt.setString(2, status.getNomeStatus());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    status.setIdStatus(rs.getInt(1));
                }
            }
        }

        return status.getIdStatus();
    }

    @Override
    public Status buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_status, tipo_status, nome_status FROM [Status] WHERE id_status = ?";

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
    public List<Status> listarTodos() throws SQLException {
        String sql = "SELECT id_status, tipo_status, nome_status FROM [Status] ORDER BY id_status";
        List<Status> statusList = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                statusList.add(mapear(rs));
            }
        }

        return statusList;
    }

    @Override
    public boolean atualizar(Status status) throws SQLException {
        String sql = "UPDATE [Status] SET tipo_status = ?, nome_status = ? WHERE id_status = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.getTipoStatus());
            stmt.setString(2, status.getNomeStatus());
            stmt.setInt(3, status.getIdStatus());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM [Status] WHERE id_status = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Status mapear(ResultSet rs) throws SQLException {
        return new Status(
                rs.getInt("id_status"),
                rs.getString("tipo_status"),
                rs.getString("nome_status")
        );
    }
}
