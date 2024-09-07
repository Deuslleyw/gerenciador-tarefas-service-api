package com.deusley.api_listas.service;

import com.deusley.api_listas.domain.Item;

import java.util.List;

public interface ItemService {

    List<Item> obterItensPorLista(Long listaId);
    Item obterItemPorId(Long itemId);
    Item adicionarItem(Long listaId, Item item);
    Item atualizarItem(Long itemId, Item itemAtualizado);
    void removerItem(Long itemId);

}
