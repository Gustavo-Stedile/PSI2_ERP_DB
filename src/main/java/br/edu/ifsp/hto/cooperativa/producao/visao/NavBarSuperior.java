package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import java.awt.*;

public class NavBarSuperior extends JPanel {

    public NavBarSuperior() {
        setLayout(new BorderLayout());
        setBackground(new Color(157, 170, 61)); // verdeClaro

        // ===== ÍCONE HAMBÚRGUER (desenhado manualmente, mesmo estilo visual) =====
        JButton btnMenu = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                int w = getWidth();
                int h = getHeight();
                int barWidth = 20;
                int barHeight = 3;
                int x = (w - barWidth) / 2;
                int y = (h - 3 * barHeight - 8) / 2;

                for (int i = 0; i < 3; i++) {
                    g2.fillRoundRect(x, y + i * (barHeight + 4), barWidth, barHeight, 5, 5);
                }
            }
        };
        btnMenu.setPreferredSize(new Dimension(50, 40));
        btnMenu.setFocusPainted(false);
        btnMenu.setBorderPainted(false);
        btnMenu.setBackground(new Color(157, 170, 61));
        btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // ===== BOTÃO SAIR =====
        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Arial", Font.BOLD, 16));
        btnSair.setFocusPainted(false);
        btnSair.setBackground(new Color(63, 72, 23)); // verdeEscuro
        btnSair.setForeground(Color.WHITE);
        btnSair.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Painel interno com espaçamento
        JPanel painelEsquerda = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        painelEsquerda.setOpaque(false);
        painelEsquerda.add(btnMenu);

        JPanel painelDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        painelDireita.setOpaque(false);
        painelDireita.add(btnSair);

        add(painelEsquerda, BorderLayout.WEST);
        add(painelDireita, BorderLayout.EAST);
    }
}
