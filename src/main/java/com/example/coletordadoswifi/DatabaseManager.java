package com.example.coletordadoswifi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:clienteDB.db"; // URL para SQLite
    private static final String USER = ""; // SQLite não usa usuário e senha
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL); // SQLite não requer usuário e senha
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Criação da tabela se não existir
            String createTableSQL = "CREATE TABLE IF NOT EXISTS clientes (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "nome TEXT, " +
                                    "cpf TEXT UNIQUE, " +
                                    "telefone TEXT, " +
                                    "nome_wifi TEXT, " +
                                    "senha_wifi TEXT, " +
                                    "endereco TEXT)";
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
