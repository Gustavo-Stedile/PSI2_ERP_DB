package br.edu.ifsp.hto.cooperativa.planejamento.visao.area;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

public class PainelMenuArea extends JPanel {
    
    public PainelMenuArea(PainelArea parent) {
        setLayout(null); // Usamos layout nulo para posicionamento manual (como no wireframe)
        setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("Área");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setForeground(new Color(74, 87, 39));
        titulo.setBounds(100, 50, 200, 40); // Posição do título
        add(titulo);
        
        // Botão Cadastrar Área
        JButton btnCadastrar = new JButton("Cadastrar Área");
        btnCadastrar.setPreferredSize(new Dimension(200, 50));
        btnCadastrar.setBounds(100, 150, 200, 50);
        btnCadastrar.setBackground(Color.WHITE);
        btnCadastrar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnCadastrar.addActionListener(e -> parent.navegarPara(AreaCard.CADASTRAR_AREA.name()));
        add(btnCadastrar);
        
        // Botão Gerenciar Área
        JButton btnGerenciar = new JButton("Gerenciar Área");
        btnGerenciar.setPreferredSize(new Dimension(200, 50));
        btnGerenciar.setBounds(320, 150, 200, 50);
        btnGerenciar.setBackground(Color.WHITE);
        btnGerenciar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btnGerenciar.addActionListener(e -> parent.navegarPara(AreaCard.GERENCIAR_AREAS.name()));
        add(btnGerenciar);
    }
}