package com.deusley.api_listas.controller;

import com.deusley.api_listas.domain.Lista;
import com.deusley.api_listas.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @GetMapping
    public List<Lista> obterTodasAsListas() {

        return listaService.obterTodasAsListas();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lista> obterListaPorId(@PathVariable Long id) {
        var lista = listaService.obterPorId(id);
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public Lista criarLista(@RequestBody Lista lista) {
        return listaService.criarLista(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLista(@PathVariable Long id) {
        listaService.deletarLista(id);
        return ResponseEntity.noContent().build();
    }
}
