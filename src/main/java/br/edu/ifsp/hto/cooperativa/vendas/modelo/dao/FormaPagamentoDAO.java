package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.FormaPagamentoVO;

public class FormaPagamentoDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/cooperativaBD?serverTimezone=UTC&useSSL=false",
            "root", "");
    }

    public String adicionar(FormaPagamentoVO f) {
        String sql = "INSERT INTO forma_pagamento (descricao) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, f.getDescricao());
            int affected = stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) { if (keys.next()) f.setId(keys.getLong(1)); }
            return affected > 0 ? "Forma de pagamento cadastrada." : "Nenhuma linha inserida.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }

    public FormaPagamentoVO buscarId(long id) {
        FormaPagamentoVO f = null;
        String sql = "SELECT * FROM forma_pagamento WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    f = new FormaPagamentoVO();
                    f.setId(rs.getLong("id"));
                    f.setDescricao(rs.getString("descricao"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return f;
    }

    public List<FormaPagamentoVO> obterTodos() {
        List<FormaPagamentoVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM forma_pagamento";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                FormaPagamentoVO f = new FormaPagamentoVO();
                f.setId(rs.getLong("id"));
                f.setDescricao(rs.getString("descricao"));
                lista.add(f);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public String atualizar(FormaPagamentoVO f) {
        String sql = "UPDATE forma_pagamento SET descricao=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, f.getDescricao());
            stmt.setLong(2, f.getId());
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Atualizado." : "Nenhuma linha atualizada.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }

    public String remover(long id) {
        String sql = "DELETE FROM forma_pagamento WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Removido." : "Nenhuma linha removida.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }
}
