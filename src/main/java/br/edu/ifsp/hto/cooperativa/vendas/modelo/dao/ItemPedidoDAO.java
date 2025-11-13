package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ItemPedidoVO;

public class ItemPedidoDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/cooperativaBD?serverTimezone=UTC&useSSL=false",
            "root", "");
    }

    public String adicionar(ItemPedidoVO i) {
        String sql = "INSERT INTO item_pedido (pedido_id, produto_id, quantidade_total, valor_unitario, valor_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, i.getPedidoId());
            stmt.setLong(2, i.getProdutoId());
            stmt.setBigDecimal(3, i.getQuantidadeTotal());
            stmt.setBigDecimal(4, i.getValorUnitario());
            stmt.setBigDecimal(5, i.getValorTotal());
            int affected = stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) { if (keys.next()) i.setId(keys.getLong(1)); }
            return affected > 0 ? "Item cadastrado." : "Nenhuma linha inserida.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }

    public ItemPedidoVO buscarId(long id) {
        ItemPedidoVO i = null;
        String sql = "SELECT * FROM item_pedido WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    i = new ItemPedidoVO();
                    i.setId(rs.getLong("id"));
                    i.setPedidoId(rs.getLong("pedido_id"));
                    i.setProdutoId(rs.getLong("produto_id"));
                    i.setQuantidadeTotal(rs.getBigDecimal("quantidade_total"));
                    i.setValorUnitario(rs.getBigDecimal("valor_unitario"));
                    i.setValorTotal(rs.getBigDecimal("valor_total"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return i;
    }

    public List<ItemPedidoVO> obterTodos() {
        List<ItemPedidoVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM item_pedido";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ItemPedidoVO i = new ItemPedidoVO();
                i.setId(rs.getLong("id"));
                i.setPedidoId(rs.getLong("pedido_id"));
                i.setProdutoId(rs.getLong("produto_id"));
                i.setQuantidadeTotal(rs.getBigDecimal("quantidade_total"));
                i.setValorUnitario(rs.getBigDecimal("valor_unitario"));
                i.setValorTotal(rs.getBigDecimal("valor_total"));
                lista.add(i);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    public String atualizar(ItemPedidoVO i) {
        String sql = "UPDATE item_pedido SET pedido_id=?, produto_id=?, quantidade_total=?, valor_unitario=?, valor_total=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, i.getPedidoId());
            stmt.setLong(2, i.getProdutoId());
            stmt.setBigDecimal(3, i.getQuantidadeTotal());
            stmt.setBigDecimal(4, i.getValorUnitario());
            stmt.setBigDecimal(5, i.getValorTotal());
            stmt.setLong(6, i.getId());
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Item atualizado." : "Nenhuma linha atualizada.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }

    public String remover(long id) {
        String sql = "DELETE FROM item_pedido WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Item removido." : "Nenhuma linha removida.";
        } catch (SQLException e) { e.printStackTrace(); return "Erro: " + e.getMessage(); }
    }
}
