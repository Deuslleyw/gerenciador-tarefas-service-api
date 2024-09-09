package com.deusley.gerenciadorDeTarefasAPI.controller.response;

import com.deusley.gerenciadorDeTarefasAPI.domain.Lista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemResponse {

    private Long id;
    private String titulo;
    private boolean destacado;
    private boolean concluido;
    private Lista lista;

}
