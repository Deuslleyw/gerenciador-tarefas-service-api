package com.deusley.api_listas.dto;

import com.deusley.api_listas.domain.Lista;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO {

    private Long id;
    private String titulo;
    private boolean destacado;
    private boolean concluido;
    private Lista lista;

}
