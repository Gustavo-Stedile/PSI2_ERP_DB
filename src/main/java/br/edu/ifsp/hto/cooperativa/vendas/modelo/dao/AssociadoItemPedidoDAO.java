package br.edu.ifsp.hto.cooperativa.vendas.modelo.dao;

import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.AssociadoItemPedidoVO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AssociadoItemPedidoDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cooperativaBD?serverTimezone=UTC&useSSL=false",
                "root", "");
    }

    public String adicionar(AssociadoItemPedidoVO a) {
        String sql = "INSERT INTO associado_item_pedido (associado_id, item_pedido_id, quantidade_atribuida) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, a.getAssociadoId());
            stmt.setLong(2, a.getItemPedidoId());
            stmt.setBigDecimal(3, a.getQuantidadeAtribuida());

            int affected = stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next())
                    a.setId(keys.getLong(1));
            }
            return affected > 0 ? "Registro cadastrado." : "Nenhuma linha inserida.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }
    }

    public AssociadoItemPedidoVO buscarId(long id) {
        AssociadoItemPedidoVO a = null;
        String sql = "SELECT * FROM associado_item_pedido WHERE id = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    a = new AssociadoItemPedidoVO();
                    a.setId(rs.getLong("id"));
                    a.setAssociadoId(rs.getLong("associado_id"));
                    a.setItemPedidoId(rs.getLong("item_pedido_id"));
                    a.setQuantidadeAtribuida(rs.getBigDecimal("quantidade_atribuida"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    public List<AssociadoItemPedidoVO> obterTodos() {
        List<AssociadoItemPedidoVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM associado_item_pedido";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AssociadoItemPedidoVO a = new AssociadoItemPedidoVO();
                a.setId(rs.getLong("id"));
                a.setAssociadoId(rs.getLong("associado_id"));
                a.setItemPedidoId(rs.getLong("item_pedido_id"));
                a.setQuantidadeAtribuida(rs.getBigDecimal("quantidade_atribuida"));
                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String atualizar(AssociadoItemPedidoVO a) {
        String sql = "UPDATE associado_item_pedido SET associado_id=?, item_pedido_id=?, quantidade_atribuida=? WHERE id=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, a.getAssociadoId());
            stmt.setLong(2, a.getItemPedidoId());
            stmt.setBigDecimal(3, a.getQuantidadeAtribuida());
            stmt.setLong(4, a.getId());

            int affected = stmt.executeUpdate();
            return affected > 0 ? "Atualizado." : "Nenhuma linha atualizada.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }
    }

    public String remover(long id) {
        String sql = "DELETE FROM associado_item_pedido WHERE id=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Removido." : "Nenhuma linha removida.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }
    }
}
