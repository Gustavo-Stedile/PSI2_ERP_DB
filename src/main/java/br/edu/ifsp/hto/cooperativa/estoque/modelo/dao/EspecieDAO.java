package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Categoria;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Especie;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EspecieDAO {
    private static EspecieDAO instancia = null;
    private static final CategoriaDAO DAO_categoria = CategoriaDAO.getInstance();
    private static final Map<Integer, Especie> cache = new HashMap<>();
    
    private EspecieDAO(){}
    public static EspecieDAO getInstance(){
        if (instancia == null) instancia = new EspecieDAO();
        return instancia;
    }
    
    public boolean inserir(Especie especie) {
        String sql = "INSERT INTO especie (categoria_id, nome, descricao, tempo_colheita, rendimento_kg_m2) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, especie.getCategoria().getId());
            stmt.setString(2, especie.getNome());
            stmt.setString(3, especie.getDescricao());
            stmt.setInt(4, especie.getTempo_colheita());
            stmt.setFloat(5, especie.getRendimento_kg_m2());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    especie.setId(idGerado);
                    cache.put(idGerado, especie);
                }
            }
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir especie: " + e.getMessage());
            return false;
        }
    }

    public Especie buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, categoria_id, nome, descricao, tempo_colheita, rendimento_kg_m2 FROM especie WHERE id = ?";
        Especie especie = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria categoria = DAO_categoria.buscarPorId(rs.getInt("categoria_id"));
                
                especie = new Especie(
                        rs.getInt("id"),
                        categoria,
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("tempo_colheita"),
                        rs.getFloat("rendimento_kg_m2"));
                cache.put(id, especie);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar especie por ID: " + e.getMessage());
        }

        return especie;
    }

    public boolean atualizar(Especie especie) {
        String sql = "UPDATE especie SET categoria_id = ?, nome = ?, descricao = ?, tempo_colheita = ?, rendimento_kg_m2 = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, especie.getCategoria().getId());
            stmt.setString(2, especie.getNome());
            stmt.setString(3, especie.getDescricao());
            stmt.setInt(4, especie.getTempo_colheita());
            stmt.setFloat(5, especie.getRendimento_kg_m2());
            stmt.setInt(6, especie.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar especie: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM especie WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir especie: " + e.getMessage());
            return false;
        }
    }

    public List<Especie> listarTodas() {
        List<Especie> especies = new ArrayList<>();
        String sql = "SELECT id, categoria_id, nome, descricao, tempo_colheita, rendimento_kg_m2 FROM especie";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                
                if (!cache.containsKey(id)) {
                    Categoria categoria = DAO_categoria.buscarPorId(rs.getInt("categoria_id"));
                    Especie especie = new Especie(
                            rs.getInt("id"),
                            categoria,
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getInt("tempo_colheita"),
                            rs.getFloat("rendimento_kg_m2"));
                    cache.put(id, especie);
                }
                especies.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar especies: " + e.getMessage());
        }

        return especies;
    }
}
