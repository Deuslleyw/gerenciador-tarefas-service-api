package com.deusley.api_listas.service;

import com.deusley.api_listas.domain.Lista;
import com.deusley.api_listas.dto.ListaDTO;

import java.util.List;

public interface ListaService {

    Lista obterPorId(Long id);

    List<ListaDTO> obterTodasAsListas();

    Lista criarLista(Lista lista);

    void deletarLista(Long id);


}
