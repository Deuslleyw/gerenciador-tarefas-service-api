package com.deusley.api_listas.service;

import com.deusley.api_listas.controller.request.ListaRequest;
import com.deusley.api_listas.domain.Lista;
import com.deusley.api_listas.controller.response.ListaResponse;

import java.util.List;

public interface ListaService {

    ListaResponse obterPorId(Long id);

    List<ListaResponse> obterTodasAsListas();

    ListaResponse criarLista(ListaRequest listaRequest);

    void deletarLista(Long id);


}
