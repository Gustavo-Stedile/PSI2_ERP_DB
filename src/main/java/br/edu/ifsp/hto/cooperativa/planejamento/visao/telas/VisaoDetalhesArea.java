package br.edu.ifsp.hto.cooperativa.planejamento.visao.telas;

import br.edu.ifsp.hto.cooperativa.planejamento.controle.PlanejamentoControle;
import br.edu.ifsp.hto.cooperativa.planejamento.modelo.VO.AreaComTalhoesVO;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.base.VisaoBase;
import br.edu.ifsp.hto.cooperativa.planejamento.visao.estilo.Tema;

import javax.swing.*;
import java.awt.*;

public class VisaoDetalhesArea extends VisaoBase {

    private AreaComTalhoesVO at;
    private PlanejamentoControle pc = new PlanejamentoControle();

    public VisaoDetalhesArea(int idArea) {
        // Passamos o nome da área para o título do cabeçalho
        super("");
        at = pc.buscarAreaComTalhoes(idArea);
        setTitle("Detalhes da Área: " + at.getArea().getNome());
    }

    @Override
    protected JPanel getPainelConteudo() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Tema.COR_BRANCA);

        JLabel label = new JLabel("Aqui entrarão as especificidades (Talhões, Safra, etc)", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(Color.GRAY);

        painel.add(label, BorderLayout.CENTER);
        
        // Botãozinho para voltar (opcional, mas ajuda a testar)
        JButton btnVoltar = new JButton("Voltar para Lista");
        btnVoltar.addActionListener(e -> abrirAreas()); // Usa o método herdado da VisaoBase
        
        JPanel painelSul = new JPanel(); // Painel só para o botão não ficar gigante
        painelSul.setOpaque(false);
        painelSul.add(btnVoltar);
        
        painel.add(painelSul, BorderLayout.SOUTH);

        return painel;
    }
}