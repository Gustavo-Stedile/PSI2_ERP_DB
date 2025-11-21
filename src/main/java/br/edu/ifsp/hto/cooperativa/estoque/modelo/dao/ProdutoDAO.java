package br.edu.ifsp.hto.cooperativa.estoque.modelo.dao;

import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Produto;
import br.edu.ifsp.hto.cooperativa.estoque.modelo.vo.Especie;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoDAO {
    private static ProdutoDAO instancia = null;
    private static final EspecieDAO DAO_especie = EspecieDAO.getInstance();
    private static final Map<Integer, Produto> cache = new HashMap<>();
    
    private ProdutoDAO(){}
    public static ProdutoDAO getInstance(){
        if (instancia == null) instancia = new ProdutoDAO();
        return instancia;
    }
    
    public boolean inserir(Produto produto) {
        String sql = "INSERT INTO produto (especie_id, nome, descricao) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produto.getEspecie().getId());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    produto.setId(idGerado);
                    cache.put(idGerado, produto);
                }
            }
            
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
            return false;
        }
    }

    public Produto buscarPorId(int id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }
        
        String sql = "SELECT id, especie_id, nome, descricao FROM movimentacao WHERE id = ?";
        Produto produto = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Especie especie = DAO_especie.buscarPorId(rs.getInt("especie_id"));
                
                produto = new Produto(
                        rs.getInt("id"),
                        especie,
                        rs.getString("nome"),
                        rs.getString("descricao"));
                cache.put(id, produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
        }

        return produto;
    }

    public boolean atualizar(Produto produto) {
        String sql = "UPDATE produto SET id, especie_id, nome, descricao WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, produto.getId());
            stmt.setInt(2, produto.getEspecie().getId());
            stmt.setString(3, produto.getNome());
            stmt.setString(4, produto.getDescricao());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar Produtos: " + e.getMessage());
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, especie_id, nome, descricao FROM produto";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                if (!cache.containsKey(id)) {
                    Especie especie = DAO_especie.buscarPorId(rs.getInt("especie_id"));
                    Produto produto = new Produto(
                            id,
                            especie,
                            rs.getString("nome"),
                            rs.getString("descricao"));
                    cache.put(id, produto);
                }
                produtos.add(cache.get(id));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }
    
    // Outros para alem do CRUD básico.
    
    public Produto buscarPorEspecieId(int especie_id) {
        String sql = "SELECT id, especie_id, nome, descricao FROM movimentacao WHERE especie_id = ?";
        Produto produto = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, especie_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                if (cache.containsKey(id)) {
                    return cache.get(id);
                }
                
                Especie especie = DAO_especie.buscarPorId(rs.getInt("especie_id"));
                
                produto = new Produto(
                        id,
                        especie,
                        rs.getString("nome"),
                        rs.getString("descricao"));
                cache.put(id, produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
        }

        return produto;
    }
}
