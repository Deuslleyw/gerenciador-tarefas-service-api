package com.deusley.api_listas.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    private String titulo;
    private boolean destacado;
    private boolean concluido;

}
