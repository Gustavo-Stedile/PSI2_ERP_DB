package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Dados de conexão (EXEMPLO)
    private static final String URL = "jdbc:mysql://localhost:3306/seubanco"; 
    private static final String USUARIO = "root";
    private static final String SENHA = "senha";

    // Método que retorna uma conexão.
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}
