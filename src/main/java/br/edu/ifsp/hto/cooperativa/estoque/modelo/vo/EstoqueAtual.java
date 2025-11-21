package br.edu.ifsp.hto.cooperativa.estoque.modelo.vo;

public class EstoqueAtual{
    private int associado_id;
    private Produto produto;
    private Armazem armazem;
    private float quantidade;
    
    public EstoqueAtual(int associado_id, Produto produto, Armazem armazem, float quantidade){
        this.associado_id = associado_id;
        this.produto = produto;
        this.armazem = armazem;
        this.quantidade = quantidade;
    }
    
    public int getAssociadoId() {
        return this.associado_id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Armazem getArmazem() {
        return armazem;
    }

    public float getQuantidade() {
        return quantidade;
    }

    // Setters
    public void setAssociadoId(int associado_id) {
        this.associado_id = associado_id;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setArmazem(Armazem armazem) {
        this.armazem = armazem;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
}
