package br.edu.ifsp.hto.cooperativa.planejamento.visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

public class PainelPlanejamentoProducao extends JPanel {

    public PainelPlanejamentoProducao() {
        // Usa BorderLayout para ter o título no topo e o conteúdo abaixo
        setLayout(new BorderLayout(20, 20)); // Espaçamento (hgap, vgap)
        setBackground(Color.WHITE); // Fundo branco

        // --- Título da Tela ---
        JLabel titulo = new JLabel("Planejamento de Produção");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(74, 87, 39)); // Cor do texto do título
        
        // Cria um painel superior para o título com margem
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(Color.WHITE);
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 40, 0, 0)); // Margem superior e esquerda
        painelTitulo.add(titulo, BorderLayout.WEST);

        // Adiciona a faixa verde horizontal no topo do PainelTitulo
        JPanel faixaVerde = new JPanel();
        faixaVerde.setBackground(new Color(118, 142, 59));
        faixaVerde.setPreferredSize(new java.awt.Dimension(0, 50));
        
        // Adiciona a faixa e o título
        JPanel topo = new JPanel(new BorderLayout());
        topo.add(faixaVerde, BorderLayout.NORTH); // Faixa verde no topo
        topo.add(painelTitulo, BorderLayout.CENTER);
        
        add(topo, BorderLayout.NORTH);


        // --- Painel de Cards (Conteúdo Central) ---
        JPanel painelCards = new JPanel(new GridLayout(2, 2, 20, 20)); // Grid 2x2 com espaçamento
        painelCards.setBackground(Color.WHITE);
        // Margem para o painel de cards
        painelCards.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40)); 

        // Adiciona os 4 cards (retângulos)
        painelCards.add(criarCard("Planos Criados: 5"));
        painelCards.add(criarCard("")); // Card vazio 2
        painelCards.add(criarCard("")); // Card vazio 3
        painelCards.add(criarCard("")); // Card vazio 4
        
        add(painelCards, BorderLayout.CENTER);
    }
    
    /**
     * Cria um JPanel representando um dos cards brancos com borda.
     * @param texto O texto a ser exibido dentro do card.
     * @return O JPanel do card.
     */
    private JPanel criarCard(String texto) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        
        // Cria a borda simples preta, como na imagem
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        if (!texto.isEmpty()) {
            JLabel label = new JLabel(texto);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento interno
            card.add(label, BorderLayout.NORTH); // Adiciona o texto no topo do card
        }
        return card;
    }
}