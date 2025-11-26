package br.edu.ifsp.hto.cooperativa.planejamento.visao;

public interface ParametroListener {
    // Método que a tela de destino implementará para receber os dados
    void receberParametros(Object... params);
}