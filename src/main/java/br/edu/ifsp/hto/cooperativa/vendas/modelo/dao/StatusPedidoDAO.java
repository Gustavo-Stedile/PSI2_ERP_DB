package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.StatusPedidoVO;

public class StatusPedidoDAO {
     private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/cooperativaBD?serverTimezone=UTC&useSSL=false",
            "root", "");
    }

    public String adicionar(StatusPedidoVO s) {
        String sql = "INSERT INTO status_pedido (descricao) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, s.getDescricao());
            int affected = stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) s.setId(keys.getLong(1));
            }
            return affected > 0 ? "Status cadastrado." : "Nenhuma linha inserida.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao cadastrar status: " + e.getMessage();
        }
    }

    public StatusPedidoVO buscarId(long id) {
        StatusPedidoVO s = null;
        String sql = "SELECT * FROM status_pedido WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    s = new StatusPedidoVO();
                    s.setId(rs.getLong("id"));
                    s.setDescricao(rs.getString("descricao"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return s;
    }

    public List<StatusPedidoVO> obterTodos() {
        List<StatusPedidoVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM status_pedido";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StatusPedidoVO s = new StatusPedidoVO();
                s.setId(rs.getLong("id"));
                s.setDescricao(rs.getString("descricao"));
                lista.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public String atualizar(StatusPedidoVO s) {
        String sql = "UPDATE status_pedido SET descricao=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getDescricao());
            stmt.setLong(2, s.getId());
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Status atualizado." : "Nenhuma linha atualizada.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }

    public String remover(long id) {
        String sql = "DELETE FROM status_pedido WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Status removido." : "Nenhuma linha removida.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }
}
