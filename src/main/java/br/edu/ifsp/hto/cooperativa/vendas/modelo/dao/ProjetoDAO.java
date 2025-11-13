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

import br.edu.ifsp.hto.cooperativa.vendas.modelo.vo.ProjetoVO;

public class ProjetoDAO {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cooperativaBD?serverTimezone=UTC&useSSL=false",
                "root", "");
    }

    public String adicionar(ProjetoVO p) {
        String sql = "INSERT INTO projeto (nome_projeto, data_criacao, data_final, orcamento) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNomeProjeto());
            stmt.setTimestamp(2, p.getDataCriacao() != null ? Timestamp.valueOf(p.getDataCriacao()) : null);
            stmt.setTimestamp(3, p.getDataFinal() != null ? Timestamp.valueOf(p.getDataFinal()) : null);
            stmt.setBigDecimal(4, p.getOrcamento());

            int affected = stmt.executeUpdate();
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next())
                    p.setId(keys.getLong(1));
            }
            return affected > 0 ? "Projeto cadastrado com sucesso!" : "Nenhuma linha inserida.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao cadastrar projeto: " + e.getMessage();
        }
    }

    public ProjetoVO buscarId(long id) {
        ProjetoVO p = null;
        String sql = "SELECT * FROM projeto WHERE id = ?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    p = new ProjetoVO();
                    p.setId(rs.getLong("id"));
                    p.setNomeProjeto(rs.getString("nome_projeto"));
                    Timestamp t1 = rs.getTimestamp("data_criacao");
                    p.setDataCriacao(t1 != null ? t1.toLocalDateTime() : null);
                    Timestamp t2 = rs.getTimestamp("data_final");
                    p.setDataFinal(t2 != null ? t2.toLocalDateTime() : null);
                    p.setOrcamento(rs.getBigDecimal("orcamento"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public List<ProjetoVO> obterTodos() {
        List<ProjetoVO> lista = new ArrayList<>();
        String sql = "SELECT * FROM projeto";
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ProjetoVO p = new ProjetoVO();
                p.setId(rs.getLong("id"));
                p.setNomeProjeto(rs.getString("nome_projeto"));
                Timestamp t1 = rs.getTimestamp("data_criacao");
                p.setDataCriacao(t1 != null ? t1.toLocalDateTime() : null);
                Timestamp t2 = rs.getTimestamp("data_final");
                p.setDataFinal(t2 != null ? t2.toLocalDateTime() : null);
                p.setOrcamento(rs.getBigDecimal("orcamento"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public String atualizar(ProjetoVO p) {
        String sql = "UPDATE projeto SET nome_projeto=?, data_criacao=?, data_final=?, orcamento=? WHERE id=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNomeProjeto());
            stmt.setTimestamp(2, p.getDataCriacao() != null ? Timestamp.valueOf(p.getDataCriacao()) : null);
            stmt.setTimestamp(3, p.getDataFinal() != null ? Timestamp.valueOf(p.getDataFinal()) : null);
            stmt.setBigDecimal(4, p.getOrcamento());
            stmt.setLong(5, p.getId());
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Projeto atualizado com sucesso!" : "Nenhuma linha atualizada.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar projeto: " + e.getMessage();
        }
    }

    public String remover(long id) {
        String sql = "DELETE FROM projeto WHERE id=?";
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0 ? "Projeto removido com sucesso!" : "Nenhuma linha removida.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao remover projeto: " + e.getMessage();
        }
    }
}
