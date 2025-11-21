package br.edu.ifsp.hto.cooperativa.estoque.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaEstoqueAtual extends JFrame {

    private JPanel painelControle;
    private JPanel painelExibicao;

    private JTextField campoProduto;
    private JButton botaoExportar;

    private JTable tabelaEstoque;
    private JScrollPane scrollTabela;

    public TelaEstoqueAtual() {
        setTitle("Visão – Estoque Atual");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
        montarLayout();
    }

    private void inicializarComponentes() {
        painelControle = new JPanel();
        painelControle.setLayout(new FlowLayout(FlowLayout.LEFT));

        campoProduto = new JTextField(25);
        botaoExportar = new JButton("Exportar");

        painelControle.add(new JLabel("Produto:"));
        painelControle.add(campoProduto);
        painelControle.add(botaoExportar);

        painelExibicao = new JPanel(new BorderLayout());

        String[] colunas = {
            "Produto",
            "Categoria",
            "Espécie",
            "Quantidade Atual"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaEstoque = new JTable(modeloTabela);
        tabelaEstoque.setRowHeight(22);

        scrollTabela = new JScrollPane(tabelaEstoque);
        painelExibicao.add(scrollTabela);
    }

    private void montarLayout() {
        add(painelControle, BorderLayout.NORTH);
        add(painelExibicao, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaEstoqueAtual().setVisible(true));
    }
}
