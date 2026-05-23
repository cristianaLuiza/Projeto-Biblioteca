package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Cep;
import br.com.doacaolivros.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CepDao implements CrudDao<Cep> {
    @Override
    public int inserir(Cep cep) throws SQLException {
        String sql = "INSERT INTO Ceps (numero, cidade_id) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cep.getNumero());
            stmt.setInt(2, cep.getCidadeId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cep.setIdCep(rs.getInt(1));
                }
            }
        }

        return cep.getIdCep();
    }

    @Override
    public Cep buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_cep, numero, cidade_id FROM Ceps WHERE id_cep = ?";

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
    public List<Cep> listarTodos() throws SQLException {
        String sql = "SELECT id_cep, numero, cidade_id FROM Ceps ORDER BY id_cep";
        List<Cep> ceps = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ceps.add(mapear(rs));
            }
        }

        return ceps;
    }

    @Override
    public boolean atualizar(Cep cep) throws SQLException {
        String sql = "UPDATE Ceps SET numero = ?, cidade_id = ? WHERE id_cep = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cep.getNumero());
            stmt.setInt(2, cep.getCidadeId());
            stmt.setInt(3, cep.getIdCep());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Ceps WHERE id_cep = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Cep mapear(ResultSet rs) throws SQLException {
        return new Cep(
                rs.getInt("id_cep"),
                rs.getString("numero"),
                rs.getInt("cidade_id")
        );
    }
}
