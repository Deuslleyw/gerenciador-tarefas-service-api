package com.deusley.api_listas.service.impl;

import com.deusley.api_listas.domain.Item;
import com.deusley.api_listas.repositories.ItemRepository;
import com.deusley.api_listas.repositories.ListaRepository;
import com.deusley.api_listas.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ListaRepository listaRepository;

    @Override
    public List<Item> obterItensPorLista(Long listaId) {
        var lista = listaRepository.findById(listaId).orElseThrow(() -> new IllegalArgumentException(
                "Não foi possivel encontrar a lista"));
        return lista.getItens();
    }

    @Override
    public Item obterItemPorId(Long itemId) {
        Optional<Item> itemObj = itemRepository.findById(itemId);
        return itemObj.orElseThrow(() -> new IllegalArgumentException("Não encontrado"));
    }

    @Override
    public Item adicionarItem(Long listaId, Item item) {
        var lista = listaRepository.findById(listaId).orElseThrow(() -> new IllegalArgumentException(
                "Lista não encontrada"));
        item.setLista(lista);
        return itemRepository.save(item);

    }

    @Override
    public Item atualizarItem(Long itemId, Item itemAtualizado) {
        var item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException(
                "Item não encontrado"));
        item.setTitulo(itemAtualizado.getTitulo());
        item.setDestacado(itemAtualizado.isDestacado());
        item.setConcluido(itemAtualizado.isConcluido());
        return itemRepository.save(item);
    }
    @Override
    public void removerItem(Long itemId) {
        obterItemPorId(itemId);
        itemRepository.deleteById(itemId);

    }
}
