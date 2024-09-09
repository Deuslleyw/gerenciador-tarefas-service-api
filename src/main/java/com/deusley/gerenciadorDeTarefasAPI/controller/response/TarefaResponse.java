package com.deusley.gerenciadorDeTarefasAPI.controller.response;

import com.deusley.gerenciadorDeTarefasAPI.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaResponse {


    private Long id;
    private String nome;
    private List<Item> itens = new ArrayList<>();


}
