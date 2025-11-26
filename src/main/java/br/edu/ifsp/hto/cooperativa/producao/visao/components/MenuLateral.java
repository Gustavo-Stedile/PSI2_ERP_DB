package br.edu.ifsp.hto.cooperativa.producao.visao.components;

import javax.swing.*;

import br.edu.ifsp.hto.cooperativa.producao.controle.*;
import br.edu.ifsp.hto.cooperativa.producao.modelo.*;
import br.edu.ifsp.hto.cooperativa.estoque.visao.TelaRelatorio;
import br.edu.ifsp.hto.cooperativa.producao.*;

import java.awt.*;

public class MenuLateral extends JPanel {

    // Guarda a referência da tela "pai" (TelaProducao ou TelaRegistrarProblemas)
    private JFrame telaAtual;

    public MenuLateral(JFrame telaAtual) { // CONSTRUTOR MODIFICADO
        this.telaAtual = telaAtual;

        Color verdeEscuro = new Color(63, 72, 23);

        setBackground(verdeEscuro);
        setPreferredSize(new Dimension(220, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(30));

        JLabel tituloMenu = new JLabel("Produção");
        tituloMenu.setForeground(Color.WHITE);
        tituloMenu.setFont(new Font("Arial", Font.BOLD, 22));
        tituloMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(tituloMenu);
        add(Box.createVerticalStrut(40));

        // Textos dos botões
        String[] botoesTexto = {"Área de plantio", "Registrar problemas", "Relatório de produção"};

        for (String texto : botoesTexto) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 15));
            botao.setBackground(Color.WHITE);
            botao.setForeground(Color.BLACK);
            botao.setFocusPainted(false);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(180, 50));
            botao.setBorder(BorderFactory.createLineBorder(verdeEscuro, 2));
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(botao);
            add(Box.createVerticalStrut(20));
        }
    }
    // Outros métodos aqui...
}