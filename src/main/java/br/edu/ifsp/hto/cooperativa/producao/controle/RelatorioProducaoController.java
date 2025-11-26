package br.edu.ifsp.hto.cooperativa.producao.controle;

import br.edu.ifsp.hto.cooperativa.producao.modelo.RelatorioProducaoModel;

public class RelatorioProducaoController {

    private RelatorioProducaoModel model;

    public RelatorioProducaoController(RelatorioProducaoModel model) {
        this.model = model;
    }

    public String[] getAreasPlantio() {
        return model.getAreas(); 
    }
}
