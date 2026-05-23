package br.com.doacaolivros.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=DoacaoLivros;encrypt=true;trustServerCertificate=true;";
    private static final String USUARIO = "sa";
    private static final String SENHA = "sua_senha";

    private Conexao() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
