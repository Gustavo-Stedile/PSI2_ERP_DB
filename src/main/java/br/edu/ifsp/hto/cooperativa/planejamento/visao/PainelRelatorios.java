package br.edu.ifsp.hto.cooperativa.planejamento.visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;

public class PainelRelatorios extends JPanel {
    public PainelRelatorios() {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY); // Cor diferente para visualização
        
        JLabel label = new JLabel("Tela de Relatórios");
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setHorizontalAlignment(JLabel.CENTER);
        
        add(label, BorderLayout.CENTER);
    }
}