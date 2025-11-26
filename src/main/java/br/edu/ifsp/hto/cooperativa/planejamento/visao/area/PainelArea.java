package br.edu.ifsp.hto.cooperativa.planejamento.visao.area;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.BorderLayout;

// Enum para facilitar a referência aos cards internos
enum AreaCard {
    MENU_AREA,
    CADASTRAR_AREA,
    GERENCIAR_AREAS,
    DETALHES_AREA, // NOVO CARD
    CADASTRAR_PLANO 
}

public class PainelArea extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Se você estiver utilizando o principalListener (para voltar ao Início),
    // o construtor deve recebê-lo. Se não, o construtor sem argumentos está ok.
    public PainelArea() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.WHITE);
        
        PainelDetalhesArea detalhesArea = new PainelDetalhesArea(this);
        PainelGerenciarAreas gerenciarAreas = new PainelGerenciarAreas(this, detalhesArea);
        
        // NOVO: Criar o painel de cadastro de plano
        PainelCadastrarPlano cadastrarPlano = new PainelCadastrarPlano(this);
        
        // 2. Adiciona os painéis internos ao CardPanel
        cardPanel.add(new PainelMenuArea(this), AreaCard.MENU_AREA.name());
        cardPanel.add(new PainelCadastrarArea(this), AreaCard.CADASTRAR_AREA.name());
        cardPanel.add(gerenciarAreas, AreaCard.GERENCIAR_AREAS.name()); 
        cardPanel.add(detalhesArea, AreaCard.DETALHES_AREA.name()); 
        cardPanel.add(cadastrarPlano, AreaCard.CADASTRAR_PLANO.name()); // Adici
        // Adiciona o painel de cards ao centro do PainelArea
        add(cardPanel, BorderLayout.CENTER);
        
        // Exibe o menu inicial da Área
        navegarPara(AreaCard.MENU_AREA.name());
    }
    
    // Método público para a navegação interna
    public void navegarPara(String nomeCard) {
        cardLayout.show(cardPanel, nomeCard);
    }
}