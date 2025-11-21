package br.edu.ifsp.hto.cooperativa.estoque.visao;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TelaHistorico {

    public static void main(String[] args) {

        JFrame janela = new JFrame("Histórico");
        janela.setSize(1200, 700);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        /* MENU LATERAL  */

        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.Y_AXIS));
        painelMenu.setBorder(new EmptyBorder(30, 10, 15, 10));
        painelMenu.setBackground(new Color(55, 61, 13));

        String[] opcoes = {
                "Registro de Saída", "Registro de Produção", "Visão de Estoque",
                "Inventário", "Cadastro de Produto", "Histórico", "Relatório"
        };

        for (String opcao : opcoes) {
            painelMenu.add(criarBotao(opcao));
            painelMenu.add(Box.createVerticalStrut(10));
        }

        /* PAINEL CENTRAL */

        JPanel painelCentral = new JPanel(null); // Layout manual para parecer com Figma
        painelCentral.setBackground(new Color(244, 245, 247)); // Fundo do Figma
        painelCentral.setBorder(new EmptyBorder(20, 20, 20, 20));

        /* CAMPO PRODUTO (ESQUERDA) */

        JLabel lblProduto = new JLabel("Produto");
        lblProduto.setBounds(180, 40, 200, 20);

        JTextField txtProduto = new JTextField();
        txtProduto.setBounds(180, 60, 260, 32);
        txtProduto.setBorder(new LineBorder(Color.GRAY));

        /* COMBOS (PARTE SUPERIOR DIREITA)*/

        JComboBox<String> comboTempo = new JComboBox<>(new String[]{
                "Últimos 30 dias", "Últimos 7 dias", "Últimos 90 dias"
        });
        comboTempo.setBounds(480, 60, 150, 32);

        JComboBox<String> comboTipo = new JComboBox<>(new String[]{
                "Todos", "Entradas", "Saídas"
        });
        comboTipo.setBounds(640, 60, 150, 32);

        /*CARDS DE RESUMO*/

        JPanel cardSaldo = criarCardResumo("Saldo atual", "25");
        cardSaldo.setBounds(480, 110, 110, 70);

        JPanel cardEntrada = criarCardResumo("Entradas", "30");
        cardEntrada.setBounds(600, 110, 110, 70);

        JPanel cardSaida = criarCardResumo("Saídas", "5");
        cardSaida.setBounds(720, 110, 110, 70);

        /* TIMELINE (PAINEL BRANCO) */

        JPanel timelineCard = new JPanel();
        timelineCard.setLayout(new BoxLayout(timelineCard, BoxLayout.Y_AXIS));
        timelineCard.setBackground(Color.WHITE);
        timelineCard.setBorder(new LineBorder(new Color(220, 220, 220), 1));
        timelineCard.setBounds(470, 200, 380, 350);

        timelineCard.add(Box.createVerticalStrut(15));
        timelineCard.add(criarLinhaTimeline("03/09/2025", "Entrada", "+10 unid.", "Saldo: 25", new Color(50, 180, 70)));
        timelineCard.add(Box.createVerticalStrut(20));
        timelineCard.add(criarLinhaTimeline("02/09/2025", "Saída", "−5 unid.", "Saldo: 15", new Color(180, 150, 20)));
        timelineCard.add(Box.createVerticalStrut(20));
        timelineCard.add(criarLinhaTimeline("01/09/2025", "Entrada", "+20 unid.", "Saldo: 20", new Color(210, 60, 50)));

        /*ADICIONA AO PAINEL */

        painelCentral.add(lblProduto);
        painelCentral.add(txtProduto);
        painelCentral.add(comboTempo);
        painelCentral.add(comboTipo);

        painelCentral.add(cardSaldo);
        painelCentral.add(cardEntrada);
        painelCentral.add(cardSaida);

        painelCentral.add(timelineCard);

        janela.add(painelMenu, BorderLayout.WEST);
        janela.add(painelCentral, BorderLayout.CENTER);

        janela.setVisible(true);
    }


    /* BOTÕES DO MENU */
    private static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        Dimension tamanho = new Dimension(130, 30);
        botao.setPreferredSize(tamanho);
        botao.setMaximumSize(tamanho);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setFocusPainted(false);
        return botao;
    }

    /* CARD DE RESUMO IGUAL AO FIGMA */
    private static JPanel criarCardResumo(String titulo, String valor) {
        JPanel card = new JPanel(null);
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(new Color(220, 220, 220), 1));
        card.setLayout(null);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTitulo.setBounds(10, 5, 100, 15);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Arial", Font.BOLD, 22));
        lblValor.setBounds(10, 25, 100, 30);

        card.add(lblTitulo);
        card.add(lblValor);

        return card;
    }

    /* LINHA DA TIMELINE  */
    private static JPanel criarLinhaTimeline(String data, String tipo, String qtd, String saldo, Color cor) {

        JPanel linha = new JPanel(null);
        linha.setPreferredSize(new Dimension(300, 80));
        linha.setBackground(Color.WHITE);

        // Ícone redondo colorido
        JPanel circulo = new JPanel();
        circulo.setBackground(cor);
        circulo.setBounds(20, 10, 18, 18);
        circulo.setBorder(new LineBorder(Color.DARK_GRAY, 1));
        circulo.setOpaque(true);

        JLabel lblData = new JLabel(data);
        lblData.setBounds(50, 5, 200, 20);

        JLabel lblTipo = new JLabel(tipo);
        lblTipo.setBounds(50, 25, 200, 20);

        JLabel lblQtd = new JLabel("  " + qtd);
        lblQtd.setBounds(50, 40, 200, 20);

        JLabel lblSaldo = new JLabel(saldo);
        lblSaldo.setBounds(50, 55, 200, 20);

        linha.add(circulo);
        linha.add(lblData);
        linha.add(lblTipo);
        linha.add(lblQtd);
        linha.add(lblSaldo);

        return linha;
    }
}
