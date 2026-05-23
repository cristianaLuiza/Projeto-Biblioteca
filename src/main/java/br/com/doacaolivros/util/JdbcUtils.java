package br.com.doacaolivros.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

public class JdbcUtils {
    private JdbcUtils() {
    }

    public static LocalDateTime getLocalDateTime(ResultSet rs, String coluna) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(coluna);
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }

    public static void setLocalDateTimeOrNull(PreparedStatement stmt, int indice, LocalDateTime valor) throws SQLException {
        if (valor == null) {
            stmt.setNull(indice, Types.TIMESTAMP);
        } else {
            stmt.setTimestamp(indice, Timestamp.valueOf(valor));
        }
    }

    public static void setIntegerOrNull(PreparedStatement stmt, int indice, Integer valor) throws SQLException {
        if (valor == null) {
            stmt.setNull(indice, Types.INTEGER);
        } else {
            stmt.setInt(indice, valor);
        }
    }
}
