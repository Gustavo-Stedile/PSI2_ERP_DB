package br.edu.ifsp.hto.cooperativa.producao.visao;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import br.edu.ifsp.hto.cooperativa.producao.controle.RegistrarProblemasController;
import br.edu.ifsp.hto.cooperativa.producao.visao.components.MenuLateral;
import br.edu.ifsp.hto.cooperativa.producao.visao.components.RoundedBorder;
import br.edu.ifsp.hto.cooperativa.producao.visao.components.NavBarSuperior;

import java.awt.*;
import java.text.ParseException;

public class TelaRegistrarProblemas extends JFrame {

    private RegistrarProblemasController controller;

    // Definição de cores (reutilizando seu padrão)
    private Color verdeEscuro = new Color(63, 72, 23);
    private Color verdeClaro = new Color(157, 170, 61);
    private Color cinzaFundo = new Color(235, 235, 235);
    private Color verdeLista = new Color(216, 222, 186); // Cor para o fundo da lista (como no wireframe)

    public TelaRegistrarProblemas(RegistrarProblemasController controller) {
        this.controller = controller;

        configurarTela();
        montarLayout();
    }

    private void configurarTela() {
        setTitle("Registrar Problemas - Produção");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void montarLayout() {
        // NAVBAR
        NavBarSuperior navBar = new NavBarSuperior();
        add(navBar, BorderLayout.NORTH);

        // MENU LATERAL
        // ATENÇÃO: Precisaremos modificar o MenuLateral para que os botões funcionem.
        // Veja a Seção 3 (Integração)
        MenuLateral menu = new MenuLateral(this); // Passando 'this' (o JFrame)
        add(menu, BorderLayout.WEST);

        // CONTEÚDO CENTRAL
        JPanel conteudo = new JPanel(new GridBagLayout());
        conteudo.setBackground(cinzaFundo);
        add(conteudo, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- LINHA 0: BOTÃO VOLTAR E TÍTULO ---

        // BOTÃO VOLTAR
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltar.setBackground(verdeClaro);
        btnVoltar.setBorder(new RoundedBorder(20));
        btnVoltar.setPreferredSize(new Dimension(160, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        conteudo.add(btnVoltar, gbc);

        // TÍTULO
        JLabel lblTitulo = new JLabel("Registrar problemas", SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(verdeEscuro);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        conteudo.add(lblTitulo, gbc);

        // Espaçador para empurrar a lista para a direita
        gbc.gridx = 3;
        gbc.weightx = 0.4; // Dá mais espaço para a coluna da lista
        conteudo.add(Box.createHorizontalGlue(), gbc);
        gbc.weightx = 0; // Reseta

        // --- LINHA 1: LABELS DO FORMULÁRIO E LISTA ---

        JLabel lblProblema = new JLabel("Problema");
        lblProblema.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(20, 20, 0, 20); // Mais espaço em cima
        conteudo.add(lblProblema, gbc);

        JLabel lblCultura = new JLabel("Selecionar cultura");
        lblCultura.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        conteudo.add(lblCultura, gbc);

        // --- LINHA 2: COMPONENTES (COMBO E LISTA) ---
        gbc.insets = new Insets(10, 20, 10, 20); // Reseta insets

        JComboBox<String> comboProblema = new JComboBox<>(controller.getListaProblemas());
        comboProblema.setFont(new Font("Arial", Font.PLAIN, 18));
        comboProblema.setPreferredSize(new Dimension(100, 45)); // Altura
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0.6; // Dá mais espaço para a coluna do formulário
        conteudo.add(comboProblema, gbc);

        // LISTA DE CULTURAS
        JList<String> listaCulturas = new JList<>(controller.getListaCulturas());
        listaCulturas.setCellRenderer(new CulturaListRenderer()); // Aplicando o renderer
        listaCulturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCulturas.setFixedCellHeight(70); // Altura de cada item
        
        JScrollPane scrollLista = new JScrollPane(listaCulturas);
        scrollLista.setPreferredSize(new Dimension(300, 100)); // Tamanho inicial
        scrollLista.setBorder(BorderFactory.createLineBorder(verdeEscuro)); // Borda como no wireframe

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 6; // Ocupa 6 linhas de altura
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0; // Ocupa todo o espaço vertical
        conteudo.add(scrollLista, gbc);

        // --- LINHA 3: LABELS (QUANTIDADE E DATA) ---
        gbc.gridheight = 1; // Reseta
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblQtd = new JLabel("Quantidade");
        lblQtd.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3; // Metade do espaço
        conteudo.add(lblQtd, gbc);

        JLabel lblData = new JLabel("Data");
        lblData.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3; // Metade do espaço
        conteudo.add(lblData, gbc);

        // --- LINHA 4: TEXTFIELDS (QUANTIDADE E DATA) ---

        JTextField txtQtd = new JTextField();
        txtQtd.setFont(new Font("Arial", Font.PLAIN, 18));
        txtQtd.setPreferredSize(new Dimension(100, 45));
        gbc.gridx = 0;
        gbc.gridy = 4;
        conteudo.add(txtQtd, gbc);

        JFormattedTextField txtData = null;
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtData = new JFormattedTextField(mascaraData);
            txtData.setFont(new Font("Arial", Font.PLAIN, 18));
            txtData.setPreferredSize(new Dimension(100, 45));
        } catch (ParseException e) {
            e.printStackTrace();
            txtData = new JFormattedTextField(); // Fallback
        }
        gbc.gridx = 1;
        gbc.gridy = 4;
        conteudo.add(txtData, gbc);

        // --- LINHA 5: LABEL OBSERVAÇÕES ---

        JLabel lblObs = new JLabel("Observações");
        lblObs.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        conteudo.add(lblObs, gbc);

        // --- LINHA 6: TEXTAREA OBSERVAÇÕES ---

        JTextArea areaObs = new JTextArea();
        areaObs.setFont(new Font("Arial", Font.PLAIN, 16));
        areaObs.setLineWrap(true);
        areaObs.setWrapStyleWord(true);
        JScrollPane scrollObs = new JScrollPane(areaObs);
        scrollObs.setPreferredSize(new Dimension(100, 150));

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weighty = 0.5; // Dá um peso vertical
        gbc.fill = GridBagConstraints.BOTH;
        conteudo.add(scrollObs, gbc);

        // --- LINHA 7: BOTÃO SALVAR ---

        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalvar.setBackground(verdeClaro);
        btnSalvar.setBorder(new RoundedBorder(20));
        btnSalvar.setPreferredSize(new Dimension(160, 50));

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Padding final
        conteudo.add(btnSalvar, gbc);
    }

    /**
     * Classe interna para renderizar a JList de Culturas
     * com o estilo do Wireframe.
     */
    private class CulturaListRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            
            // O valor é o texto (ex: "ALFACE\n03/2025 - 06/2026")
            // Vamos formatar usando HTML para quebrar a linha
            String texto = value.toString().replace("\n", "<br>");
            String html = "<html><div style='text-align: center; padding: 10px;'>" + 
                          texto + 
                          "</div></html>";

            // Chama o super (JLabel) para configurar o texto
            JLabel label = (JLabel) super.getListCellRendererComponent(list, html, 
                                     index, isSelected, cellHasFocus);

            label.setBackground(verdeLista); // Fundo verde claro
            label.setForeground(verdeEscuro); // Texto verde escuro
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setHorizontalAlignment(SwingConstants.CENTER);

            // Estilo de seleção
            if (isSelected) {
                label.setBackground(verdeEscuro); // Fundo
                label.setForeground(Color.WHITE);   // Texto
            }

            // Adiciona uma borda inferior, exceto no último item
            if (index < list.getModel().getSize() - 1) {
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, verdeEscuro));
            } else {
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }

            return label;
        }
    }
}