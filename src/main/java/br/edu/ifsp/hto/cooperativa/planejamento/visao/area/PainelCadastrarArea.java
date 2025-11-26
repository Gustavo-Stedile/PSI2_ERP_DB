package br.edu.ifsp.hto.cooperativa.planejamento.visao.area;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

public class PainelCadastrarArea extends JPanel {
    
    public PainelCadastrarArea(PainelArea parent) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titulo = new JLabel("Cadastrar Área");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(74, 87, 39));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        add(titulo);
        add(Box.createVerticalStrut(30));

        // Painel para organizar os campos (usando GridLayout para 2 colunas: Label e Campo)
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 20)); // 4 linhas, 2 colunas, espaçamento
        formPanel.setBackground(Color.WHITE);
        formPanel.setMaximumSize(new Dimension(400, 200));

        // Campos de formulário
        formPanel.add(new JLabel("ID Dono:"));
        formPanel.add(new JTextField(20));
        
        formPanel.add(new JLabel("Nome da Área:"));
        formPanel.add(new JTextField(20));
        
        formPanel.add(new JLabel("Área total (m²):"));
        formPanel.add(new JTextField(20));
        
        formPanel.add(new JLabel("PH do solo:"));
        formPanel.add(new JTextField(20));

        add(formPanel);
        add(Box.createVerticalStrut(40));

        // --- Container para Salvar e Cancelar ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0)); // FlowLayout para os botões
        painelBotoes.setBackground(Color.WHITE);

        // Botão SALVAR (Lógica existente)
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalvar.setBackground(new Color(118, 142, 59)); 
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setPreferredSize(new Dimension(150, 40));
        btnSalvar.addActionListener(e -> {
            // Lógica de salvar aqui
            System.out.println("Área salva. Voltando ao menu de Área.");
            parent.navegarPara(AreaCard.MENU_AREA.name()); // Volta ao menu após salvar
        });
        
        // Botão CANCELAR (Novo)
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setBackground(Color.LIGHT_GRAY); // Cor neutra para Cancelar
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(150, 40));
        btnCancelar.addActionListener(e -> {
            // Ação de cancelar: apenas retorna ao menu da área
            System.out.println("Cadastro cancelado. Voltando ao menu de Área.");
            parent.navegarPara(AreaCard.MENU_AREA.name()); 
        });

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        
        add(painelBotoes);
        add(Box.createVerticalGlue());
    }
}