package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.ConnectionFactory;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Origem;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrigemDAO {
    private static OrigemDAO instancia = null;
    private static final Map<Integer, Origem> cache = new HashMap<>();
    
    private OrigemDAO(){}
    public static OrigemDAO getInstance(){
        if (instancia == null) instancia = new OrigemDAO();
        return instancia;
    }

    public boolean inserir(Origem origem) {
        String sql = "INSERT INTO origem (nome) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, origem.getNome());
            stmt.executeUpdate();
            
            // Recupera o ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    origem.setId(idGerado); // atualiza o objeto com o novo ID
                    cache.put(idGerado, origem);
                }
            }
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir origem: " + e.getMessage());
            return false;
        }
    }

    public Origem buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, nome FROM origem WHERE id = ?";
        Origem origem = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                origem = new Origem(rs.getInt("id"), rs.getString("nome"));
                cache.put(id, origem);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar origem por ID: " + e.getMessage());
        }

        return origem;
    }

    public boolean atualizar(Origem origem) {
        String sql = "UPDATE origem SET nome = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, origem.getNome());
            stmt.setInt(2, origem.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar origem: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM origem WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir origem: " + e.getMessage());
            return false;
        }
    }

    public List<Origem> listarTodas() {
        List<Origem> origens = new ArrayList<>();
        String sql = "SELECT id, nome FROM origem ORDER BY nome";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                
                if (!cache.containsKey(id)) {
                    Origem origem = new Origem(
                        id,
                        rs.getString("nome")
                    );
                    cache.put(id, origem);
                }
                origens.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar origens: " + e.getMessage());
        }

        return origens;
    }
}
