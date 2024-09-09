package com.deusley.gerenciadorDeTarefasAPI.service;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ItemRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.ItemResponse;
import com.deusley.gerenciadorDeTarefasAPI.domain.Item;

import java.util.List;

public interface ItemService {

    List<Item> obterItensPorLista(Long listaId);
    Item obterItemPorId(Long itemId);
    Item adicionarItem(Long listaId, Item item);
    ItemResponse atualizarItem(Long itemId, ItemRequest itemAtualizado);
    void removerItem(Long itemId);

}
