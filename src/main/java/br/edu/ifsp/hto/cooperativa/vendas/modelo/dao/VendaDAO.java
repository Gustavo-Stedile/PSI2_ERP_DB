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

import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.VendaVO;

public class VendaDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cooperativaBD?serverTimezone=UTC&useSSL=false",
                "root", "");
    }

    public String adicionar(VendaVO v) {
        String sql = "INSERT INTO venda (projeto_id, associado_id, pedido_id, data_compra, valor_total, data_entrega, forma_pagamento_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, v.getProjetoId());
            stmt.setLong(2, v.getAssociadoId());
            stmt.setLong(3, v.getPedidoId());
            stmt.setTimestamp(4, v.getDataCompra() != null ? Timestamp.valueOf(v.getDataCompra()) : null);
            stmt.setBigDecimal(5, v.getValorTotal());
            stmt.setTimestamp(6, v.getDataEntrega() != null ? Timestamp.valueOf(v.getDataEntrega()) : null);
            stmt.setLong(7, v.getFormaPagamentoId());

            int affected = stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next())
                    v.setId(keys.getLong(1));
            }
            return affected > 0 ? "Venda cadastrada." : "Nenhuma linha inserida.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }
    }

    public VendaVO buscarId(long id) {
        VendaVO v = null;
        String sql = "SELECT * FROM venda WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    v = new VendaVO();
                    v.setId(rs.getLong("id"));
                    v.setProjetoId(rs.getLong("projeto_id"));
                    v.setAssociadoId(rs.getLong("associado_id"));
                    v.setPedidoId(rs.getLong("pedido_id"));
                    Timestamp t1 = rs.getTimestamp("data_compra");
                    v.setDataCompra(t1 != null ? t1.toLocalDateTime() : null);
                    v.setValorTotal(rs.getBigDecimal("valor_total"));
                    Timestamp t2 = rs.getTimestamp("data_entrega");
                    v.setDataEntrega(t2 != null ? t2.toLocalDateTime() : null);
                    v.setFormaPagamentoId(rs.getLong("forma_pagamento_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    public List<VendaVO> obterTodos() {
        List<VendaVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM venda";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                VendaVO v = new VendaVO();
                v.setId(rs.getLong("id"));
                v.setProjetoId(rs.getLong("projeto_id"));
                v.setAssociadoId(rs.getLong("associado_id"));
                v.setPedidoId(rs.getLong("pedido_id"));
                Timestamp t1 = rs.getTimestamp("data_compra");
                v.setDataCompra(t1 != null ? t1.toLocalDateTime() : null);
                v.setValorTotal(rs.getBigDecimal("valor_total"));
                Timestamp t2 = rs.getTimestamp("data_entrega");
                v.setDataEntrega(t2 != null ? t2.toLocalDateTime() : null);
                v.setFormaPagamentoId(rs.getLong("forma_pagamento_id"));
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String atualizar(VendaVO v) {
        String sql = "UPDATE venda SET projeto_id=?, associado_id=?, pedido_id=?, data_compra=?, valor_total=?, data_entrega=?, forma_pagamento_id=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, v.getProjetoId());
            stmt.setLong(2, v.getAssociadoId());
            stmt.setLong(3, v.getPedidoId());
            stmt.setTimestamp(4, v.getDataCompra() != null ? Timestamp.valueOf(v.getDataCompra()) : null);
            stmt.setBigDecimal(5, v.getValorTotal());
            stmt.setTimestamp(6, v.getDataEntrega() != null ? Timestamp.valueOf(v.getDataEntrega()) : null);
            stmt.setLong(7, v.getFormaPagamentoId());
            stmt.setLong(8, v.getId());
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Venda atualizada." : "Nenhuma linha atualizada.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }
    }

    public String remover(long id) {
        String sql = "DELETE FROM venda WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Venda removida." : "Nenhuma linha removida.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }
    }
}
