package com.deusley.gerenciadorDeTarefasAPI.service;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ItemRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.TarefaResponse;

import java.util.List;

public interface TarefaService {

    TarefaResponse obterPorId(Long id);

    List<TarefaResponse> obterTodas();

    TarefaResponse criar(ListaRequest listaRequest);

    void deletar(Long id);

    TarefaResponse adicionarItem(Long tarefaId, ItemRequest itemRequest);

    TarefaResponse atualizarItem(Long tarefaId, ItemRequest itemAtualizado);

    TarefaResponse removerItem(Long tarefaId, Long itemId);

    TarefaResponse alterarEstado(Long tarefaId, Long itemId);

}
