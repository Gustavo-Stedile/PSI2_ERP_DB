package br.edu.ifsp.hto.cooperativa.estoque.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaInventario extends JFrame {

    private JPanel painelControle;
    private JPanel painelBotoes;
    private JScrollPane scrollTabela;

    private JTextField campoProduto;
    private JTextField campoArmazem;

    private JButton botaoImportar;
    private JButton botaoExportar;
    private JButton botaoSalvar;

    private JTable tabelaInventario;

    public TelaInventario() {
        setTitle("Visão – Inventário");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarComponentes();
        montarLayout();
    }

    private void inicializarComponentes() {

        painelControle = new JPanel(new FlowLayout(FlowLayout.LEFT));

        campoProduto = new JTextField(20);
        campoArmazem = new JTextField(15);

        botaoImportar = new JButton("Importar");

        painelControle.add(new JLabel("Produto:"));
        painelControle.add(campoProduto);

        painelControle.add(new JLabel("Armazém:"));
        painelControle.add(campoArmazem);

        painelControle.add(botaoImportar);

        String[] colunas = {
                "Produto",
                "Categoria",
                "Espécie",
                "Armazém",
                "Quantidade"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaInventario = new JTable(modeloTabela);
        tabelaInventario.setRowHeight(22);

        scrollTabela = new JScrollPane(tabelaInventario);

        painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        botaoExportar = new JButton("Exportar");
        botaoSalvar = new JButton("Salvar");

        painelBotoes.add(botaoExportar);
        painelBotoes.add(botaoSalvar);
    }

    private void montarLayout() {
        add(painelControle, BorderLayout.NORTH);
        add(scrollTabela, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaInventario().setVisible(true));
    }
}
