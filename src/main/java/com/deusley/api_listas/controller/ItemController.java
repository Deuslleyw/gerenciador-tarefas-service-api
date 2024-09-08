package com.deusley.api_listas.controller;

import com.deusley.api_listas.controller.response.ListaResponse;
import com.deusley.api_listas.domain.Item;
import com.deusley.api_listas.domain.Lista;
import com.deusley.api_listas.mapper.ListaMapper;
import com.deusley.api_listas.repositories.ListaRepository;
import com.deusley.api_listas.service.ItemService;
import com.deusley.api_listas.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas/{listaId}/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ListaService listaService;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ListaMapper listaMapper;



    @GetMapping
    public List<Item> obterItensPorLista(@PathVariable Long listaId) {
        var lista = listaService.obterPorId(listaId);
        return lista.getItens();
    }

    @PostMapping
    public Item adicionarItem(@PathVariable Long listaId, @RequestBody Item item) {
        var lista = obterListaPorId(listaId);
        item.setLista(lista);
        return itemService.adicionarItem(listaId, item);
    }

    @PutMapping("/{itemId}")
    public Item atualizarItem(@PathVariable Long itemId, @RequestBody Item itemAtualizado) {
        var item = itemService.obterItemPorId(itemId);
        item.setTitulo(itemAtualizado.getTitulo());
        item.setDestacado(itemAtualizado.isDestacado());
        item.setConcluido(itemAtualizado.isConcluido());
        return itemService.adicionarItem(itemId,item);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long itemId) {
        itemService.removerItem(itemId);
        return ResponseEntity.noContent().build();
    }
    private Lista obterListaPorId(Long id){
        return listaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Lista Não Encontrada!"));

    }

}
