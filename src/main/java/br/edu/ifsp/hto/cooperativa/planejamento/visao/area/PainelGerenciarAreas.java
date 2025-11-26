package br.edu.ifsp.hto.cooperativa.planejamento.visao.area;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Certifique-se de que AreaCard (o enum) está acessível
// Se não estiver, você precisará importar ou definir o enum AreaCard

public class PainelGerenciarAreas extends JPanel {
    
    private PainelArea parent;
    // DECLARAÇÃO DA REFERÊNCIA AO PAINEL DE DESTINO
    private PainelDetalhesArea detalhesArea; 
    
    // CONSTRUTOR ATUALIZADO: Recebe a referência do PainelDetalhesArea
    public PainelGerenciarAreas(PainelArea parent, PainelDetalhesArea detalhesArea) {
        this.parent = parent;
        this.detalhesArea = detalhesArea; // Salva a referência para uso
        
        setLayout(new GridBagLayout()); 
        setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titulo = new JLabel("Gerenciar Áreas");
        // ... (Configuração do título) ...
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(74, 87, 39));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        add(titulo, gbc);
        
        gbc.gridwidth = 1; 
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Selecione a Área:"), gbc);
        
        // Dropdown
        String[] areas = {"Selecione...", "Área 1", "Área 2", "Área 3"}; // Adicionando "Selecione..."
        JComboBox<String> comboAreas = new JComboBox<>(new DefaultComboBoxModel<>(areas));
        comboAreas.setMinimumSize(new Dimension(200, 30));
        comboAreas.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(comboAreas, gbc);
        
        // --- LÓGICA DE AÇÃO PARA IR PARA A SEGUNDA PÁGINA ---
        comboAreas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém o item selecionado
                String areaSelecionada = (String) comboAreas.getSelectedItem();
                
                // Verifica se é uma área válida (não a instrução inicial)
                if (areaSelecionada != null && !areaSelecionada.equals("Selecione...")) {
                    // 1. Carrega os dados no painel de detalhes (prepara a "segunda página")
                    // Você deve ter um método carregarArea na classe PainelDetalhesArea
                    detalhesArea.carregarArea(areaSelecionada);
                    
                    // 2. Navega para o painel de detalhes
                    // AreaCard.DETALHES_AREA.name() deve ser a string que identifica o painel no CardLayout do PainelArea
                    parent.navegarPara(AreaCard.DETALHES_AREA.name());
                }
            }
        });
        
        // Botão Cancelar (para voltar ao Menu Área)
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> parent.navegarPara(AreaCard.MENU_AREA.name()));
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnCancelar, gbc); // Alterado de "Voltar ao Menu Área" para "Cancelar"
    }
}