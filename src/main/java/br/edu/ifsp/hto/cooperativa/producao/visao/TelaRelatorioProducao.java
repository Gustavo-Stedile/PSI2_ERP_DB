package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import br.edu.ifsp.hto.cooperativa.producao.controle.RelatorioProducaoController;
import br.edu.ifsp.hto.cooperativa.producao.visao.components.CardResumo;
import br.edu.ifsp.hto.cooperativa.producao.visao.components.MenuLateral;
import br.edu.ifsp.hto.cooperativa.producao.visao.components.RoundedBorder;

import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;

// Mantida a estrutura original (extends JFrame)
public class TelaRelatorioProducao extends JFrame {

    private RelatorioProducaoController controller;

    public TelaRelatorioProducao(RelatorioProducaoController controller) {
        this.controller = controller;

        configurarTela();
        montarLayout();
    }

    // Método original
    private void configurarTela() {
        setTitle("Tela Inicial - Produção");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    // Método original, mas com o layout do conteúdo central corrigido
    private void montarLayout() {

        // CORES (Definidas localmente, como no original)
        Color verdeEscuro = new Color(63, 72, 23);
        Color verdeClaro = new Color(157, 170, 61);
        Color cinzaFundo = new Color(235, 235, 235);

        // NAVBAR (Original)
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // MENU LATERAL (Modificado para aceitar 'this' para navegação)
        MenuLateral menu = new MenuLateral(this);
        add(menu, BorderLayout.WEST);

        // CONTEÚDO CENTRAL (Original)
        JPanel conteudo = new JPanel(new GridBagLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        // Padronizando os insets (espaçamentos)
        gbc.insets = new Insets(20, 20, 20, 20); 
        gbc.fill = GridBagConstraints.NONE;
        
        // --- LINHA 0: BOTÃO VOLTAR E TÍTULO ---

        // BOTÃO VOLTAR
         JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.setBackground(verdeClaro);
        btnVoltar.setBorder(new RoundedBorder(20));
        btnVoltar.setPreferredSize(new Dimension(160, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        conteudo.add(btnVoltar, gbc);

        // TÍTULO
        JLabel lblTitulo = new JLabel("Produção", SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(verdeEscuro);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(lblTitulo, gbc);
        
        // o que o centraliza vertical e horizontalmente.
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        conteudo.add(lblTitulo, gbc);


        // --- LINHA 2: COMBO ---
        // (Pulamos a linha 1 para dar espaço)
        JComboBox<String> comboArea = new JComboBox<>(controller.getAreasPlantio());
        comboArea.setFont(new Font("Arial", Font.PLAIN, 18));
        comboArea.setPreferredSize(new Dimension(350, 45));
        comboArea.setBorder(new RoundedBorder(15));

        gbc.gridx = 0;
        gbc.gridy = 2; // Linha 2
        gbc.gridwidth = 4; // Ocupa as 4 colunas
        gbc.weightx = 0;   // Reseta o peso
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza o combo
        conteudo.add(comboArea, gbc);

        // --- LINHA 3: PAINEL RESUMO (cards) ---
        JPanel painelResumo = new JPanel(new GridLayout(1, 4, 40, 10));
        painelResumo.setOpaque(false);

        painelResumo.add(new CardResumo("Custos e materiais", "0", verdeEscuro));
        painelResumo.add(new CardResumo("Problemas Registrados", "0", verdeEscuro));
        painelResumo.add(new CardResumo("Plantações Colhidas", "0", verdeEscuro));
        painelResumo.add(new CardResumo("Tempo Médio", "0h", verdeEscuro));

        gbc.gridy = 3; // Linha 3
        gbc.gridwidth = 4;
        gbc.insets = new Insets(20, 40, 20, 40); // Insets originais do painel
        gbc.fill = GridBagConstraints.HORIZONTAL; // Estica horizontalmente
        conteudo.add(painelResumo, gbc);

        // --- LINHA 4: TABELA ---
        String[] colunas = {"Cultura", "Produção em Kg", "% Concluídas", "Custo Total"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(32);
        tabela.setFont(new Font("Arial", Font.PLAIN, 16));
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 40));
        tabela.getTableHeader().setBackground(verdeClaro);

        // centralizar texto da tabela
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        gbc.gridy = 4; // Linha 4
        gbc.gridwidth = 4;
        gbc.weighty = 1; // Ocupa todo o espaço vertical restante
        gbc.fill = GridBagConstraints.BOTH; // Estica nos dois sentidos
        gbc.insets = new Insets(10, 20, 20, 20); // Insets para a tabela
        conteudo.add(new JScrollPane(tabela), gbc);
    }
}