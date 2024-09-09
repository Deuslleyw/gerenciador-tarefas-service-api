package com.deusley.gerenciadorDeTarefasAPI.service;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.ListaResponse;

import java.util.List;

public interface ListaService {

    ListaResponse obterPorId(Long id);

    List<ListaResponse> obterTodasAsListas();

    ListaResponse criarLista(ListaRequest listaRequest);

    void deletarLista(Long id);


}
