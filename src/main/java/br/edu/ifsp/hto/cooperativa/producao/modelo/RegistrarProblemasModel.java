package br.edu.ifsp.hto.cooperativa.producao.modelo;

public class RegistrarProblemasModel {

    public String[] getProblemas() {
        // Dados de exemplo
        return new String[] {
            "Selecione um problema...",
            "Praga (Ex: Lagartas)",
            "Doença (Ex: Míldio)",
            "Equipamento quebrado",
            "Falta de irrigação",
            "Outro"
        };
    }

    public String[] getCulturasAtivas() {
        // Dados de exemplo (use \n para quebra de linha)
        // O Renderer que criei na View vai formatar isso
        return new String[] {
            "ALFACE\n03/2025 - 06/2026",
            "RÚCULA\n08/2025 - 01/2026",
            "REPOLHO\n06/2025 - 06/2026",
            "TOMATE CEREJA\n07/2025 - 12/2026",
            "MANJERICÃO\n01/2025 - 01/2027"
        };
    }

    // Lógica para salvar no banco de dados/arquivo iria aqui
    // public boolean registrarProblema(...) { ... }
}
