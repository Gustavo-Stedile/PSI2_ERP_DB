package br.edu.ifsp.hto.cooperativa.planejamento.visao.area;

import javax.swing.*;
import java.awt.*;

// Importações e início da classe permanecem os mesmos
public class PainelDetalhesArea extends JPanel {
    
    private String nomeArea;
    private String areaTotal;
    private String phSolo;
    private PainelArea parent; 

    public PainelDetalhesArea(PainelArea parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }
    
    public void carregarArea(String nomeArea) {
        this.nomeArea = nomeArea;
        
        // --- SIMULAÇÃO DE CARREGAMENTO DE DADOS ---
        if (nomeArea.equals("Área 1")) {
            this.areaTotal = "10 m²";
            this.phSolo = "7";
        } else {
            this.areaTotal = "N/A";
            this.phSolo = "N/A";
        }
        
        removeAll();
        reconstruirConteudo();
        revalidate();
        repaint();
    }

    private void reconstruirConteudo() {
        // ... (Seção Topo, Título e Botão Voltar permanecem os mesmos) ...
        
        // --- 1. Topo (Título, Ações e Botão Voltar) ---
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(Color.WHITE);
        painelTopo.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        // Botão Voltar/Sair
        JButton btnVoltarMenuArea = new JButton("Voltar para Menu Área");
        btnVoltarMenuArea.setBackground(Color.LIGHT_GRAY);
        btnVoltarMenuArea.setForeground(Color.BLACK);
        btnVoltarMenuArea.setFocusPainted(false);
        btnVoltarMenuArea.addActionListener(e -> {
            parent.navegarPara(AreaCard.MENU_AREA.name()); 
        });
        
        // Botões de Ação (Editar Talhão, Adicionar Talhão)
        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        painelAcoes.setBackground(Color.WHITE);
        painelAcoes.add(criarBotaoAcao("Adicionar Plano", new Color(118, 142, 59)));
        
        // Combina ações e botão voltar
        JPanel painelAcoesDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelAcoesDireita.setBackground(Color.WHITE);
        painelAcoesDireita.add(painelAcoes);
        painelAcoesDireita.add(btnVoltarMenuArea);

        // Título e Ações
        JPanel painelTituloAcoes = new JPanel(new BorderLayout());
        painelTituloAcoes.setBackground(Color.WHITE);
        JLabel titulo = new JLabel("Área"); 
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        painelTituloAcoes.add(titulo, BorderLayout.WEST);
        painelTituloAcoes.add(painelAcoesDireita, BorderLayout.EAST);

        painelTopo.add(painelTituloAcoes, BorderLayout.NORTH);
        
        // Informações Básicas
        JPanel painelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        painelInfo.setBackground(Color.WHITE);
        painelInfo.add(new JLabel("Nome: " + nomeArea));
        painelInfo.add(new JLabel("Área: " + areaTotal));
        painelInfo.add(new JLabel("pH do solo: " + phSolo));
        painelTopo.add(painelInfo, BorderLayout.CENTER);
        
        // Linha Divisória
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(new Color(118, 142, 59));
        painelTopo.add(separator, BorderLayout.SOUTH);

        add(painelTopo, BorderLayout.NORTH);
        
        // --- 3. Conteúdo (Talhões) ---
        JScrollPane scrollPane = new JScrollPane(criarPainelTalhoesSimulado());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private JButton criarBotaoAcao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        return btn;
    }

    private JPanel criarPainelTalhoesSimulado() {
        JPanel painelTalhoes = new JPanel();
        painelTalhoes.setLayout(new BoxLayout(painelTalhoes, BoxLayout.Y_AXIS));
        painelTalhoes.setBackground(Color.WHITE);
        painelTalhoes.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        painelTalhoes.add(criarTalhaoPanel("Talhão 1", true));
        painelTalhoes.add(Box.createVerticalStrut(10));
        painelTalhoes.add(criarTalhaoPanel("Talhão 2", false));
        painelTalhoes.add(Box.createVerticalStrut(10));
        painelTalhoes.add(criarTalhaoPanel("Talhão 3", false));
        painelTalhoes.add(Box.createVerticalStrut(10));
        painelTalhoes.add(criarTalhaoPanel("Talhão 4", false));

        return painelTalhoes;
    }
    
    /**
     * Cria um painel de Talhão com funcionalidade de expansão/colapso.
     */
    private JPanel criarTalhaoPanel(String nome, boolean expandidoInicialmente) {
        JPanel talhaoPanel = new JPanel();
        talhaoPanel.setLayout(new BoxLayout(talhaoPanel, BoxLayout.Y_AXIS));
        talhaoPanel.setBackground(Color.WHITE);
        
        // O conteúdo que será escondido/mostrado (AGORA SÃO PLANOS)
        JPanel painelConteudo = criarPainelPlanosSimulado(); 
        
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        headerPanel.setBackground(Color.WHITE);
        
        JLabel nomeLabel = new JLabel(nome);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(nomeLabel, BorderLayout.WEST);

        // --- Container para os Botões de Controle e Seta ---
        JPanel painelControle = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        painelControle.setBackground(Color.WHITE);
        
        JButton btnEditar = new JButton("Editar Talhão");
        btnEditar.setBackground(new Color(118, 142, 59));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFocusPainted(false);
        btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // ALTERADO: Botão Adicionar Plano
        JButton btnAdicionar = new JButton("Adicionar Plano"); 
        btnAdicionar.setBackground(new Color(118, 142, 59));
        btnAdicionar.setForeground(Color.WHITE);
        btnAdicionar.setFocusPainted(false);

        // NOVO: Adiciona o Listener
        btnAdicionar.addActionListener(e -> {
            // Aqui você navegará para o formulário de cadastro de plano
            parent.navegarPara(AreaCard.CADASTRAR_PLANO.name()); 
            // OBS: Se você precisar passar o ID do Talhão para o formulário,
            // você precisará estender este método de navegação.
        });
        
        JLabel seta = new JLabel(expandidoInicialmente ? "▲" : "▼");
        seta.setFont(new Font("Arial", Font.BOLD, 14));
        
        painelControle.add(btnEditar);   
        painelControle.add(btnAdicionar); 
        painelControle.add(seta);       
        
        headerPanel.add(painelControle, BorderLayout.EAST);
        
        talhaoPanel.add(headerPanel);

        // Estado inicial
        painelConteudo.setVisible(expandidoInicialmente);
        btnAdicionar.setVisible(expandidoInicialmente);
        btnEditar.setVisible(expandidoInicialmente);
        
        if (expandidoInicialmente) {
            talhaoPanel.add(Box.createVerticalStrut(10));
        }
        talhaoPanel.add(painelConteudo);
        
        // LÓGICA DE EXPANSÃO/COLAPSO
        headerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boolean visivel = painelConteudo.isVisible();
                
                painelConteudo.setVisible(!visivel);
                btnAdicionar.setVisible(!visivel);
                btnEditar.setVisible(!visivel);
                
                seta.setText(visivel ? "▼" : "▲"); 
                
                talhaoPanel.revalidate();
                talhaoPanel.repaint();
                parent.revalidate();
            }
        });
        
        return talhaoPanel;
    }

    /**
     * ALTERADO: Cria o painel que lista os Planos (antigos Canteiros).
     */
    private JPanel criarPainelPlanosSimulado() {
        JPanel painelPlanos = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        painelPlanos.setBackground(Color.WHITE);
        painelPlanos.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Simulação dos Planos
        painelPlanos.add(criarPlanoCard("Plano 1", "Milho"));
        painelPlanos.add(criarPlanoCard("Plano 2", "Soja"));
        painelPlanos.add(criarPlanoCard("Plano 3", "Alface"));
        
        return painelPlanos;
    }
    
    /**
     * ALTERADO: Cria um card individual para cada Plano.
     */
    private JPanel criarPlanoCard(String nome, String cultura) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(120, 100));
        
        // Rótulo de fechamento (X)
        JLabel closeLabel = new JLabel("X");
        closeLabel.setForeground(Color.RED);
        closeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        closeLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        card.add(closeLabel, BorderLayout.NORTH);
        
        // Informações
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(new JLabel(nome, SwingConstants.CENTER));
        infoPanel.add(new JLabel(cultura, SwingConstants.CENTER));
        card.add(infoPanel, BorderLayout.CENTER);
        
        // Botão "Ver"
        JButton btnVer = new JButton("Ver");
        btnVer.setBackground(new Color(118, 142, 59));
        btnVer.setForeground(Color.WHITE);
        btnVer.setFocusPainted(false);
        card.add(btnVer, BorderLayout.SOUTH);
        
        return card;
    }
}