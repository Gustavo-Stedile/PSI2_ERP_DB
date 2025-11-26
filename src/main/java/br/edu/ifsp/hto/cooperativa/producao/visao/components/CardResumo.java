package br.edu.ifsp.hto.cooperativa.producao.visao.components;

import javax.swing.*;
import java.awt.*;

public class CardResumo extends JPanel {

    public CardResumo(String titulo, String valor, Color corTexto) {
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(new RoundedBorder(20));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setForeground(corTexto);

        JLabel lblValor = new JLabel(valor, SwingConstants.CENTER);
        lblValor.setFont(new Font("Arial", Font.BOLD, 26));
        lblValor.setForeground(corTexto);

        add(lblTitulo, BorderLayout.NORTH);
        add(lblValor, BorderLayout.CENTER);
    }
}
