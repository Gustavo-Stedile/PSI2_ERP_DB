package br.edu.ifsp.hto.cooperativa.planejamento.visao.area;

import javax.swing.*;
import java.awt.*;
import java.util.Date; // Necessário para simular datas

public class PainelCadastrarPlano extends JPanel {
    
    private PainelArea parent;

    public PainelCadastrarPlano(PainelArea parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Container principal do formulário (usando BoxLayout para empilhar)
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        formContainer.setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Cadastrar Novo Plano");
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setForeground(new Color(74, 87, 39));
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        formContainer.add(titulo);
        formContainer.add(Box.createVerticalStrut(30));

        // Painel para organizar os campos (usando GridLayout para 2 colunas: Label e Campo)
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 15)); 
        formPanel.setBackground(Color.WHITE);
        formPanel.setMaximumSize(new Dimension(600, 300));
        
        // --- Campos do Formulário (Baseados na Tabela SQL) ---
        
        // 1. nome_plano (VARCHAR)
        formPanel.add(new JLabel("Nome do Plano:"));
        formPanel.add(new JTextField(20));
        
        // 2. Especie_id (BIGINT - Simulado como ComboBox)
        formPanel.add(new JLabel("Espécie:"));
        formPanel.add(new JComboBox<>(new String[]{"Milho", "Soja", "Trigo"}));
        
        // 3. area_cultivo (DECIMAL)
        formPanel.add(new JLabel("Área de Cultivo (m²):"));
        formPanel.add(new JTextField(20));
        
        // 4. Talhao_id (BIGINT - Não é necessário aqui, pois será passado ao salvar)
        
        // 5. data_inicio (TIMESTAMP)
        formPanel.add(new JLabel("Data Início:"));
        formPanel.add(new JTextField(new Date().toString().substring(0, 10))); // Exemplo de data
        
        // 6. data_fim (TIMESTAMP)
        formPanel.add(new JLabel("Data Fim:"));
        formPanel.add(new JTextField()); 

        // 7. descricao (TEXT - Usando JTextArea para multilinhas)
        formPanel.add(new JLabel("Descrição:"));
        JTextArea descricaoArea = new JTextArea(3, 20);
        descricaoArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        formPanel.add(new JScrollPane(descricaoArea));
        
        // 8. observacoes (TEXT - Usando JTextArea)
        formPanel.add(new JLabel("Observações:"));
        JTextArea obsArea = new JTextArea(3, 20);
        obsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        formPanel.add(new JScrollPane(obsArea));

        formContainer.add(formPanel);
        formContainer.add(Box.createVerticalStrut(40));

        // --- Botões Salvar e Cancelar ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        painelBotoes.setBackground(Color.WHITE);

        // Botão Salvar
        JButton btnSalvar = new JButton("Salvar Plano");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalvar.setBackground(new Color(118, 142, 59));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(150, 40));
        btnSalvar.addActionListener(e -> {
            // Lógica para salvar os dados no banco de dados aqui.
            JOptionPane.showMessageDialog(this, "Plano salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Ao salvar, retorna para a tela de Detalhes da Área
            parent.navegarPara(AreaCard.DETALHES_AREA.name()); 
        });

        // Botão Cancelar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCancelar.setBackground(Color.LIGHT_GRAY);
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setPreferredSize(new Dimension(150, 40));
        btnCancelar.addActionListener(e -> {
            // Ação de cancelar: retorna para a tela de Detalhes da Área
            parent.navegarPara(AreaCard.DETALHES_AREA.name()); 
        });

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        
        formContainer.add(painelBotoes);
        formContainer.add(Box.createVerticalGlue());

        add(formContainer, BorderLayout.CENTER);
    }
}