package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Timestamp;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.PedidoVO;

public class PedidoDAO {
     private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/cooperativaBD?serverTimezone=UTC&useSSL=false",
            "root", "");
    }

    public String adicionar(PedidoVO p) {
        String sql = "INSERT INTO pedido (projeto_id, associado_id, data_criacao, status_pedido_id, valor_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, p.getProjetoId());
            stmt.setLong(2, p.getAssociadoId());
            stmt.setTimestamp(3, p.getDataCriacao() != null ? Timestamp.valueOf(p.getDataCriacao()) : null);
            stmt.setLong(4, p.getStatusPedidoId());
            stmt.setBigDecimal(5, p.getValorTotal());
            int affected = stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) { if (keys.next()) p.setId(keys.getLong(1)); }
            return affected > 0 ? "Pedido cadastrado." : "Nenhuma linha inserida.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }

    public PedidoVO buscarId(long id) {
        PedidoVO p = null;
        String sql = "SELECT * FROM pedido WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new PedidoVO();
                    p.setId(rs.getLong("id"));
                    p.setProjetoId(rs.getLong("projeto_id"));
                    p.setAssociadoId(rs.getLong("associado_id"));
                    Timestamp t = rs.getTimestamp("data_criacao");
                    p.setDataCriacao(t != null ? t.toLocalDateTime() : null);
                    p.setStatusPedidoId(rs.getLong("status_pedido_id"));
                    p.setValorTotal(rs.getBigDecimal("valor_total"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return p;
    }

    public List<PedidoVO> obterTodos() {
        List<PedidoVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PedidoVO p = new PedidoVO();
                p.setId(rs.getLong("id"));
                p.setProjetoId(rs.getLong("projeto_id"));
                p.setAssociadoId(rs.getLong("associado_id"));
                Timestamp t = rs.getTimestamp("data_criacao");
                p.setDataCriacao(t != null ? t.toLocalDateTime() : null);
                p.setStatusPedidoId(rs.getLong("status_pedido_id"));
                p.setValorTotal(rs.getBigDecimal("valor_total"));
                lista.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public String atualizar(PedidoVO p) {
        String sql = "UPDATE pedido SET projeto_id=?, associado_id=?, data_criacao=?, status_pedido_id=?, valor_total=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, p.getProjetoId());
            stmt.setLong(2, p.getAssociadoId());
            stmt.setTimestamp(3, p.getDataCriacao() != null ? Timestamp.valueOf(p.getDataCriacao()) : null);
            stmt.setLong(4, p.getStatusPedidoId());
            stmt.setBigDecimal(5, p.getValorTotal());
            stmt.setLong(6, p.getId());
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Pedido atualizado." : "Nenhuma linha atualizada.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }

    public String remover(long id) {
        String sql = "DELETE FROM pedido WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Pedido removido." : "Nenhuma linha removida.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }
}
