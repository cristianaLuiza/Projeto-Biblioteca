package br.com.doacaolivros.dao;

import br.com.doacaolivros.model.Doacao;
import br.com.doacaolivros.util.Conexao;
import br.com.doacaolivros.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDao implements CrudDao<Doacao> {
    @Override
    public int inserir(Doacao doacao) throws SQLException {
        String sql = "INSERT INTO Doacoes (interesse_id, confirmacao_doador_id, confirmacao_receptor_id) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, doacao.getInteresseId());
            stmt.setInt(2, doacao.getConfirmacaoDoadorId());
            stmt.setInt(3, doacao.getConfirmacaoReceptorId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    doacao.setIdDoacao(rs.getInt(1));
                }
            }
        }

        return doacao.getIdDoacao();
    }

    @Override
    public Doacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT id_doacao, interesse_id, confirmacao_doador_id, confirmacao_receptor_id, data_doacao "
                + "FROM Doacoes WHERE id_doacao = ?";

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
    public List<Doacao> listarTodos() throws SQLException {
        String sql = "SELECT id_doacao, interesse_id, confirmacao_doador_id, confirmacao_receptor_id, data_doacao "
                + "FROM Doacoes ORDER BY id_doacao";
        List<Doacao> doacoes = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                doacoes.add(mapear(rs));
            }
        }

        return doacoes;
    }

    @Override
    public boolean atualizar(Doacao doacao) throws SQLException {
        String sql = "UPDATE Doacoes SET interesse_id = ?, confirmacao_doador_id = ?, confirmacao_receptor_id = ?, "
                + "data_doacao = ? WHERE id_doacao = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doacao.getInteresseId());
            stmt.setInt(2, doacao.getConfirmacaoDoadorId());
            stmt.setInt(3, doacao.getConfirmacaoReceptorId());
            JdbcUtils.setLocalDateTimeOrNull(stmt, 4, doacao.getDataDoacao());
            stmt.setInt(5, doacao.getIdDoacao());

            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM Doacoes WHERE id_doacao = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Doacao mapear(ResultSet rs) throws SQLException {
        return new Doacao(
                rs.getInt("id_doacao"),
                rs.getInt("interesse_id"),
                rs.getInt("confirmacao_doador_id"),
                rs.getInt("confirmacao_receptor_id"),
                JdbcUtils.getLocalDateTime(rs, "data_doacao")
        );
    }
}
