package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Doador;
import br.com.doacaolivros.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoadorDao implements CrudDao<Doador> {
    @Override
    public int inserir(Doador doador) throws SQLException {
        String sql = "INSERT INTO Doadores (pessoa_id) VALUES (?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, doador.getPessoaId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    doador.setIdDoador(rs.getInt(1));
                }
            }
        }

        return doador.getIdDoador();
    }

    @Override
    public Doador buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_doador, pessoa_id FROM Doadores WHERE id_doador = ?";

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
    public List<Doador> listarTodos() throws SQLException {
        String sql = "SELECT id_doador, pessoa_id FROM Doadores ORDER BY id_doador";
        List<Doador> doadores = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                doadores.add(mapear(rs));
            }
        }

        return doadores;
    }

    @Override
    public boolean atualizar(Doador doador) throws SQLException {
        String sql = "UPDATE Doadores SET pessoa_id = ? WHERE id_doador = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doador.getPessoaId());
            stmt.setInt(2, doador.getIdDoador());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Doadores WHERE id_doador = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Doador mapear(ResultSet rs) throws SQLException {
        return new Doador(
                rs.getInt("id_doador"),
                rs.getInt("pessoa_id")
        );
    }
}
