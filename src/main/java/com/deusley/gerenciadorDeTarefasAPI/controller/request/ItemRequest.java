package com.deusley.gerenciadorDeTarefasAPI.controller.request;

import com.deusley.gerenciadorDeTarefasAPI.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    private Long itemId;
    private String titulo;
    private Estado estado;
    private boolean destacado;

}
