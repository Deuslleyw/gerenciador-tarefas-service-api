package com.deusley.api_listas.service;

import com.deusley.api_listas.domain.Lista;

import java.util.List;

public interface ListaService {

    Lista obterPorId(Long id);

    List<Lista> obterTodasAsListas();

    Lista criarLista(Lista lista);

    void deletarLista(Long id);


}
