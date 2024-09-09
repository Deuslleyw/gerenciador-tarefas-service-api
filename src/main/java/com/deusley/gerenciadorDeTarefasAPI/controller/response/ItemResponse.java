package com.deusley.gerenciadorDeTarefasAPI.controller.response;

import com.deusley.gerenciadorDeTarefasAPI.domain.Tarefa;
import com.deusley.gerenciadorDeTarefasAPI.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemResponse {

    private Long id;
    private String titulo;
    private Estado estado;
    private boolean destacado;
    private Tarefa tarefa;

}
