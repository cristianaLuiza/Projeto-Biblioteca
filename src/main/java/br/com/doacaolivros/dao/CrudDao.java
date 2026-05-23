package br.com.doacaolivros.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> {
    int inserir(T entidade) throws SQLException;

    T buscarPorId(int id) throws SQLException;

    List<T> listarTodos() throws SQLException;

    boolean atualizar(T entidade) throws SQLException;

    boolean excluir(int id) throws SQLException;
}
