package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Tipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TipoDAO {
    private static TipoDAO instancia = null;
    private static final Map<Integer, Tipo> cache = new HashMap<>();
    
    private TipoDAO(){}
    public static TipoDAO getInstance(){
        if (instancia == null) instancia = new TipoDAO();
        return instancia;
    }
    
    public boolean inserir(Tipo tipo) {
        String sql = "INSERT INTO tipo (nome) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo.getNome());
            stmt.executeUpdate();
            
            // Recupera o ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    tipo.setId(idGerado); // atualiza o objeto com o novo ID
                    cache.put(idGerado, tipo);
                }
            }
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir tipo: " + e.getMessage());
            return false;
        }
    }

    public Tipo buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, nome FROM tipo WHERE id = ?";
        Tipo tipo = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tipo = new Tipo(rs.getInt("id"), rs.getString("nome"));
                cache.put(id, tipo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar tipo por ID: " + e.getMessage());
        }

        return tipo;
    }

    public boolean atualizar(Tipo tipo) {
        String sql = "UPDATE tipo SET nome = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo.getNome());
            stmt.setInt(2, tipo.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tipo: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM tipo WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir tipo: " + e.getMessage());
            return false;
        }
    }

    public List<Tipo> listarTodos() {
        List<Tipo> tipos = new ArrayList<>();
        String sql = "SELECT id, nome FROM tipo ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                
                if (!cache.containsKey(id)) {
                    Tipo tipo = new Tipo(
                        id,
                        rs.getString("nome")
                    );
                    cache.put(id, tipo);
                }
                tipos.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar origens: " + e.getMessage());
        }

        return tipos;
    }
}
