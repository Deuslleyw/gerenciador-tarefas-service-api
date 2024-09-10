package com.deusley.gerenciadorDeTarefasAPI.enums;

public enum Estado {
    FAZER("A Fazer"),
    CONCLUIDO("Concluído");

    private String descricao;

    Estado(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
