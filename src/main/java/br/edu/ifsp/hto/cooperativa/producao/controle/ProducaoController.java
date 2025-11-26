package br.edu.ifsp.hto.cooperativa.producao.controle;

import br.edu.ifsp.hto.cooperativa.producao.modelo.ProducaoModel;

public class ProducaoController {

    private ProducaoModel model;

    public ProducaoController(ProducaoModel model) {
        this.model = model;
    }

    public String[] getAreasPlantio() {
        return model.getAreas(); 
    }
}
