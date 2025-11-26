package br.edu.ifsp.hto.cooperativa.producao.controle;

import br.edu.ifsp.hto.cooperativa.producao.modelo.RegistrarProblemasModel;

public class RegistrarProblemasController {

    private RegistrarProblemasModel model;

    public RegistrarProblemasController(RegistrarProblemasModel model) {
        this.model = model;
    }

    public String[] getListaProblemas() {
        return model.getProblemas();
    }

    public String[] getListaCulturas() {
        return model.getCulturasAtivas();
    }
}