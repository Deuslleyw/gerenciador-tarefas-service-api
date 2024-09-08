package com.deusley.api_listas.dto;

import com.deusley.api_listas.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaDTO {


    private Long id;
    private String nome;
    private List<Item> itens = new ArrayList<>();


}
