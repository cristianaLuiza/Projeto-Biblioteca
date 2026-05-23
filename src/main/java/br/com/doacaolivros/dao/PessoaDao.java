package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Pessoa;
import br.com.doacaolivros.util.Conexao;
import br.com.doacaolivros.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDao implements CrudDao<Pessoa> {
    @Override
    public int inserir(Pessoa pessoa) throws SQLException {
        String sql = "INSERT INTO Pessoas (nome, email, senha, cep_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getSenha());
            stmt.setInt(4, pessoa.getCepId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pessoa.setIdPessoa(rs.getInt(1));
                }
            }
        }

        return pessoa.getIdPessoa();
    }

    @Override
    public Pessoa buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_pessoa, nome, email, senha, data_criacao, cep_id FROM Pessoas WHERE id_pessoa = ?";

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
    public List<Pessoa> listarTodos() throws SQLException {
        String sql = "SELECT id_pessoa, nome, email, senha, data_criacao, cep_id FROM Pessoas ORDER BY id_pessoa";
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pessoas.add(mapear(rs));
            }
        }

        return pessoas;
    }

    @Override
    public boolean atualizar(Pessoa pessoa) throws SQLException {
        String sql = "UPDATE Pessoas SET nome = ?, email = ?, senha = ?, data_criacao = ?, cep_id = ? WHERE id_pessoa = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getSenha());
            JdbcUtils.setLocalDateTimeOrNull(stmt, 4, pessoa.getDataCriacao());
            stmt.setInt(5, pessoa.getCepId());
            stmt.setInt(6, pessoa.getIdPessoa());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Pessoas WHERE id_pessoa = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Pessoa mapear(ResultSet rs) throws SQLException {
        return new Pessoa(
                rs.getInt("id_pessoa"),
                rs.getString("nome"),
                rs.getString("email"),
                rs.getString("senha"),
                JdbcUtils.getLocalDateTime(rs, "data_criacao"),
                rs.getInt("cep_id")
        );
    }
}
