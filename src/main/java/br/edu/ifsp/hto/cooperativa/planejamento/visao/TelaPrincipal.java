package br.edu.ifsp.hto.cooperativa.planejamento.visao;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.edu.ifsp.hto.cooperativa.planejamento.visao.area.PainelArea;

import java.awt.BorderLayout;
import java.awt.CardLayout;

// A classe principal implementa a interface para receber eventos do menu
public class TelaPrincipal extends JFrame implements MenuLateral.NavegacaoListener {
    
    // Painel que irá conter todos os painéis de conteúdo
    private JPanel cardPanel; 
    private CardLayout cardLayout;

    public TelaPrincipal() {
        super("Aplicação Agrícola - Navegação Seamless");
        
        // 1. Configuração do CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        // 2. Adiciona as telas (cards) ao cardPanel
        // O nome da tela (string) é a chave que será usada para trocar o card
        cardPanel.add(new PainelPlanejamentoProducao(), "Inicio");
        cardPanel.add(new PainelRelatorios(), "Relatorios");
        // ... adicione PainelArea, PainelMateriais, etc. aqui ...

        cardPanel.add(new PainelArea(), "Area");
        cardPanel.add(new JPanel() {{ add(new JLabel("Conteúdo de Materiais")); }}, "Materiais");

        // 3. Cria e adiciona o Menu Lateral
        // Passa 'this' (a própria JFrame) como o listener de navegação
        MenuLateral menu = new MenuLateral(this); 
        
        // 4. Monta o layout principal
        setLayout(new BorderLayout());
        add(menu, BorderLayout.WEST); 
        add(cardPanel, BorderLayout.CENTER); // O cardPanel fica no centro

        // 5. Configurações finais da Janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700); 
        setLocationRelativeTo(null); 
        
        // Opcional: Mostra a primeira tela (Inicio) ao iniciar
        cardLayout.show(cardPanel, "Inicio");
    }

    /**
     * Método da interface NavegacaoListener. Chamado pelo MenuLateral para trocar de tela.
     * @param nomeTela O nome da tela (chave) a ser exibida.
     */
    @Override
    public void navegarPara(String nomeTela) {
        System.out.println("Navegando para: " + nomeTela);
        // O CardLayout troca o painel visível dentro do cardPanel
        cardLayout.show(cardPanel, nomeTela);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
}