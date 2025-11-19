package br.edu.ifsp.hto.cooperativa.financeiro.modelo.dao;
// Java
import java.util.ArrayList;
// Banco
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
// Package
import br.edu.ifsp.hto.cooperativa.financeiro.modelo.vo.DespesaVO;

public class DespesaDAO {
    private Connection connection = null;
    
    public DespesaDAO (
        Connection connection
    ) {
        this.connection = connection;
    }
    
    public DespesaVO buscarId (long id) {
        DespesaVO despesa = null;
        try {
            PreparedStatement stmt = connection.prepareStatement ("SELECT * FROM despesa WHERE id = ?");
            
            stmt.setLong(1, id);
            
            ResultSet rs = stmt.executeQuery();
            
            despesa = new DespesaVO(                 
                    rs.getLong("id"),
                    rs.getLong("associado_Id"),
                    rs.getString("categoria_gasto"),
                    rs.getString("destinatario"),
                    rs.getDouble("valor_gasto"),
                    rs.getString("data_transacao"),
                    rs.getString("descricao_despesa")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesa;
    }
    
    public ArrayList<DespesaVO> buscarAssociadoId (long associado_id) {
        ArrayList<DespesaVO> despesas = new ArrayList<DespesaVO>();
        try {
            
            PreparedStatement stmt = connection.prepareStatement ("SELECT * FROM despesa WHERE associado_id = ?");
            
            stmt.setLong(1, associado_id);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DespesaVO despesa = new DespesaVO(
                    rs.getLong("id"),
                    rs.getLong("associado_Id"),
                    rs.getString("categoria_gasto"),
                    rs.getString("destinatario"),
                    rs.getDouble("valor_gasto"),
                    rs.getString("data_transacao"),
                    rs.getString("descricao_despesa")
                );
                despesas.add(despesa);
            }                  
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }
    
    public String adicionar (DespesaVO despesa) {
        try {
            
            PreparedStatement stmt = connection.prepareStatement ("INSERT INTO despesa VALUES (?,?,?,?,?,?,?)");
            
            stmt.setLong(1, despesa.buscaId());
            stmt.setLong(2, despesa.buscaAssociado_id());
            stmt.setString(3, despesa.buscaCategoria_gasto());
            stmt.setString(4, despesa.buscaDestinatario());
            stmt.setDouble(5, despesa.buscaValor_gasto());
            stmt.setString(6, despesa.buscaData_transacao());
            stmt.setString(7, despesa.buscaDescricao_despesa());
            
            stmt.executeQuery();                    
        } catch (SQLException e) {
            e.printStackTrace();
            return "Excessão ao tentar inserir nova despesa.";
        }
        return "Despesa inserida com sucesso.";
    }
    
    public String remover (long id) {
        try {
            
            PreparedStatement stmt = connection.prepareStatement ("DELETE FROM despesa WHERE id = ?");
            
            stmt.setLong(1, id);
            
            stmt.executeQuery();                    
        } catch (SQLException e) {          
            e.printStackTrace();
            return "Excessão ao tentar remover despesa de id " + id + ".";
        }
        return "Despesa de id " + id + " removida com sucesso.";
    }
    
    // Atualiza todas as informações de uma determinada despesa EXCETO id e associado_id
    public String atualizar (DespesaVO despesa) {
        try {
            
            PreparedStatement stmt = connection.prepareStatement (
                    "UPDATE despesa SET "
                            + "categoria_gasto = ?,"
                            + "destinatario = ?,"
                            + "valor_gasto = ?,"
                            + "data_transacao = ?,"
                            + "descricao_despesa = ?"
                    + "WHERE id = ?"
                            
            );
            
            stmt.setString(1, despesa.buscaCategoria_gasto());
            stmt.setString(2, despesa.buscaDestinatario());
            stmt.setDouble(3, despesa.buscaValor_gasto());
            stmt.setString(4, despesa.buscaData_transacao());
            stmt.setString(5, despesa.buscaDescricao_despesa());
            stmt.setLong(6, despesa.buscaId());
            
            stmt.executeQuery();                    
        } catch (SQLException e) {          
            e.printStackTrace();
            return "Exceção ao tentar atualizar despesa de id " + despesa.buscaId() + ".";
        }
        return "Despesa de id " + despesa.buscaId() + "atualizada com sucesso.";
    }
    
    public ArrayList<DespesaVO> buscarTodasDespesas () {
       ArrayList<DespesaVO> despesas = new ArrayList<DespesaVO>();
        try {
            
            PreparedStatement stmt = connection.prepareStatement ("SELECT * FROM despesa");
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DespesaVO despesa = new DespesaVO(
                    rs.getLong("id"),
                    rs.getLong("associado_Id"),
                    rs.getString("categoria_gasto"),
                    rs.getString("destinatario"),
                    rs.getDouble("valor_gasto"),
                    rs.getString("data_transacao"),
                    rs.getString("descricao_despesa")
                );
                despesas.add(despesa);
            }                  
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return despesas;
    }
}
