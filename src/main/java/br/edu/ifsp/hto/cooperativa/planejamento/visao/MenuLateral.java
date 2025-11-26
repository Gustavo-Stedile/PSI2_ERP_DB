package br.edu.ifsp.hto.cooperativa.planejamento.visao;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory; // Para a borda do botão "Relatórios"

public class MenuLateral extends JPanel {
    private static final Color COR_FUNDO_MENU = new Color(74, 87, 39);
    private static final Color COR_FUNDO_BOTAO_ATIVO = new Color(118, 142, 59); // Verde mais claro para o ativo/selecionado
    private static final Color COR_FUNDO_BOTAO_INATIVO = Color.WHITE;
    private static final String[] TELAS = {"Inicio", "Area", "Materiais", "Relatorios"};
    
    // Interface para comunicação com o JFrame principal (o "card container")
    private NavegacaoListener listener; 

    // Botão atualmente selecionado, para gerenciar o destaque
    private JButton botaoSelecionado; 

    // Interface funcional para desacoplar a navegação
    public interface NavegacaoListener {
        void navegarPara(String nomeTela);
    }
    
    public MenuLateral(NavegacaoListener listener) {
        this.listener = listener;
        
        setBackground(COR_FUNDO_MENU);
        setPreferredSize(new Dimension(150, 600)); 
        setLayout(new GridLayout(10, 1, 0, 10)); 
        
        add(new JPanel() {{ setOpaque(false); }}); 

        // Cria os botões e define o listener de ação
        for (String nomeTela : TELAS) {
            adicionarBotao(nomeTela);
        }
        
        // Inicialmente, defina o primeiro botão como selecionado
        // O primeiro botão adicionado é o "Início" (índice 1 no GridLayout, 0 na lista de TELAS)
        JButton botaoInicio = (JButton) getComponent(1); 
        selecionarBotao(botaoInicio, "Inicio");
    }

    private void adicionarBotao(String texto) {
        JButton botao = new JButton(texto);
        
        // Estilização padrão (texto escuro, sem foco)
        botao.setForeground(Color.BLACK);
        botao.setFocusPainted(false); 
        botao.setPreferredSize(new Dimension(130, 40));
        
        // Se for o botão "Relatórios" (como no primeiro print), ele tem o fundo escuro
        if (texto.equals("Relatorios")) {
            botao.setBackground(COR_FUNDO_BOTAO_ATIVO);
            botao.setForeground(Color.WHITE); // Texto branco para contraste
            botao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajuste de borda
        } else {
             botao.setBackground(COR_FUNDO_BOTAO_INATIVO);
        }

        // Action Listener para gerenciar a troca de painéis e o destaque
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionarBotao(botao, texto);
                // Chama o método no JFrame principal para trocar o card
                if (listener != null) {
                    listener.navegarPara(texto);
                }
            }
        });

        add(botao);
    }

    /**
     * Gerencia a troca de destaque do botão selecionado.
     * @param novoBotao O botão que acabou de ser clicado.
     * @param nomeTela O nome da tela para navegação.
     */
    private void selecionarBotao(JButton novoBotao, String nomeTela) {
        // Se havia um botão selecionado, retorna-o ao estilo inativo
        if (botaoSelecionado != null) {
            botaoSelecionado.setBackground(COR_FUNDO_BOTAO_INATIVO);
            botaoSelecionado.setForeground(Color.BLACK);
            // Remove o estilo ativo (se houver, como a borda/cor diferente)
            botaoSelecionado.setBorder(UIManager.getBorder("Button.border")); // Retorna a borda padrão do Swing
        }

        // Define o novo estilo para o botão clicado (estilo 'ativo')
        novoBotao.setBackground(COR_FUNDO_BOTAO_ATIVO);
        novoBotao.setForeground(Color.WHITE);
        
        // Estilo específico para o botão ativo (se necessário)
        if (nomeTela.equals("Relatorios")) {
            novoBotao.setBackground(COR_FUNDO_BOTAO_ATIVO); // Mantém o verde escuro/ativo
        }

        // Atualiza a referência
        botaoSelecionado = novoBotao;
    }
}