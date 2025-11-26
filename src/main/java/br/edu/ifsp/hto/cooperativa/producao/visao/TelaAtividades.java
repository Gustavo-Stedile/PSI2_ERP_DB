package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.net.URI;
// import java.net.URL;


import java.awt.*;

public class TelaAtividades extends JFrame {

    public TelaAtividades() {
        setTitle("Tela Atividades");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ======= CORES =======
        Color verdeEscuro = new Color(63, 72, 23);
        Color verdeClaro = new Color(157, 170, 61);
        Color cinzaFundo = new Color(240, 240, 240);

        // ======= NAVBAR SUPERIOR =======
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // ======= MENU LATERAL =======
        JPanel menuLateral = new JPanel();
        menuLateral.setBackground(verdeEscuro);
        menuLateral.setPreferredSize(new Dimension(220, getHeight()));
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));

        menuLateral.add(Box.createVerticalStrut(30));

        JLabel tituloMenu = new JLabel("Produção", SwingConstants.CENTER);
        tituloMenu.setForeground(Color.WHITE);
        tituloMenu.setFont(new Font("Arial", Font.BOLD, 22));
        tituloMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuLateral.add(tituloMenu);

        menuLateral.add(Box.createVerticalStrut(40));

        String[] botoes = {"Área de plantio", "Registrar problemas", "Relatório de produção"};
        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 15));
            botao.setBackground(Color.WHITE);
            botao.setForeground(Color.BLACK);
            botao.setFocusPainted(false);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(180, 50));
            botao.setPreferredSize(new Dimension(180, 50));
            botao.setBorder(BorderFactory.createLineBorder(verdeEscuro, 2));
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            menuLateral.add(botao);
            menuLateral.add(Box.createVerticalStrut(20));
        }

        add(menuLateral, BorderLayout.WEST);

        // ======= CONTEÚDO PRINCIPAL =======
        JPanel conteudo = new JPanel();
        conteudo.setBackground(cinzaFundo);
        conteudo.setLayout(new GridBagLayout());
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 0;

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBackground(verdeClaro);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setPreferredSize(new Dimension(120, 45));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(btnVoltar, gbc);

        JLabel lblTitulo = new JLabel("Atividade: Preparar Solo", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(verdeEscuro);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(lblTitulo, gbc);

        JPanel painelResumo = new JPanel(new GridLayout(1, 3, 40, 20));
        painelResumo.setOpaque(false);
        String[] textos = {
                "Custo Total: R$ 660,00",
                "Data Prevista: 18/02/2026",
                "Prioridade: Alta"
        };

        for (String texto : textos) {
            JPanel box = new JPanel(new BorderLayout());
            box.setBackground(Color.WHITE);
            box.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
            lbl.setFont(new Font("Arial", Font.BOLD, 20));
            lbl.setForeground(verdeEscuro);
            box.add(lbl, BorderLayout.CENTER);
            painelResumo.add(box);
        }

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.weighty = 0.3;
        conteudo.add(painelResumo, gbc);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setOpaque(false);

        JLabel lblAFazer = new JLabel("Materiais Necessários");
        lblAFazer.setFont(new Font("Arial", Font.BOLD, 28));
        lblAFazer.setForeground(verdeEscuro);
        painelTitulo.add(lblAFazer, BorderLayout.WEST);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        painelBotoes.setOpaque(false);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEditar.setBackground(verdeClaro);
        btnEditar.setForeground(Color.BLACK);
        btnEditar.setFocusPainted(false);
        btnEditar.setPreferredSize(new Dimension(120, 38));
        btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        painelBotoes.add(btnEditar);

        JButton btnConcluir = new JButton("Marcar como concluído");
        btnConcluir.setFont(new Font("Arial", Font.BOLD, 16));
        btnConcluir.setBackground(verdeClaro);
        btnConcluir.setForeground(Color.BLACK);
        btnConcluir.setFocusPainted(false);
        btnConcluir.setPreferredSize(new Dimension(220, 38));
        btnConcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        painelBotoes.add(btnConcluir);

        painelTitulo.add(painelBotoes, BorderLayout.EAST);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(painelTitulo, gbc);


        ImageIcon iconEdit = null;
        ImageIcon iconDelete = null;

        try {
            iconEdit = new ImageIcon(URI.create("https://img.icons8.com/ios-glyphs/24/edit--v1.png").toURL());
            iconDelete = new ImageIcon(URI.create("https://img.icons8.com/ios-glyphs/24/trash--v1.png").toURL());
        } catch (Exception e) {
            e.printStackTrace();
        }



        String[] colunas = {"ID", "Nome", "Tipo", "Uni. Medida","Custo Uni.", "Qtd. Materiais", "Custo Tot.", "  ", "  "};
        Object[][] dados = {
        {1, "Semente milho", "Semente", "kg", "25,00", 20, "500,00", iconEdit,  iconDelete},
        {2, "Água", "Insumo", "m³", "2,00", 50, "100,00",  iconEdit,  iconDelete},
        {3, "Diesel", "Combustível", "L", "6,00", 10, "60,00",  iconEdit,  iconDelete}
        };

        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 7 || columnIndex == 8) {
                return Icon.class; 
            }
            return Object.class;
    }
        };

        JTable tabela = new JTable(modelo);
        tabela.setFont(new Font("Arial", Font.PLAIN, 16));
        tabela.setRowHeight(38);
        tabela.getTableHeader().setBackground(verdeClaro);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tabela.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabela.getColumnCount() - 2; i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(center);
        }


        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);  
        tabela.getColumnModel().getColumn(1).setPreferredWidth(160); 
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120); 
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);  
        tabela.getColumnModel().getColumn(4).setPreferredWidth(100);  
        tabela.getColumnModel().getColumn(5).setPreferredWidth(120);  
        tabela.getColumnModel().getColumn(6).setPreferredWidth(100);  
        tabela.getColumnModel().getColumn(7).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(8).setPreferredWidth(50);

        JScrollPane scrollTabela = new JScrollPane(tabela);
        gbc.gridy = 3;
        gbc.weighty = 1;
        conteudo.add(scrollTabela, gbc);

        JButton btnAtrelar = new JButton("+ Atrelar Material");
        btnAtrelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAtrelar.setForeground(Color.BLACK);

        btnAtrelar.setContentAreaFilled(false);
        btnAtrelar.setBorderPainted(false);
        btnAtrelar.setFocusPainted(false);

        btnAtrelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridy = 4;        
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        conteudo.add(btnAtrelar, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaAtividades tela = new TelaAtividades();
            tela.setVisible(true);
        });
    }
}
