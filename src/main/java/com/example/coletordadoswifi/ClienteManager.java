package com.example.coletordadoswifi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteManager {

    public void addCliente(Cliente cliente) {
        String insertSQL = "INSERT INTO clientes (nome, cpf, telefone, nome_wifi, senha_wifi, endereco) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getTelefone());
            pstmt.setString(4, cliente.getNomeWifi());
            pstmt.setString(5, cliente.getSenhaWifi());
            pstmt.setString(6, cliente.getEndereco());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente getCliente(String cpf) {
        String querySQL = "SELECT * FROM clientes WHERE cpf = ?";
        Cliente cliente = null;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(querySQL)) {

            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("telefone"),
                    rs.getString("nome_wifi"),
                    rs.getString("senha_wifi"),
                    rs.getString("endereco")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public boolean removeCliente(String cpf) {
        String deleteSQL = "DELETE FROM clientes WHERE cpf = ?";
        boolean isDeleted = false;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, cpf);
            int rowsAffected = pstmt.executeUpdate();

            isDeleted = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }
}
