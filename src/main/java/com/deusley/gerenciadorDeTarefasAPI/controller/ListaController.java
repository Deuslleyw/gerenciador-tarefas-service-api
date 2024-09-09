package com.deusley.gerenciadorDeTarefasAPI.controller;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.ListaResponse;
import com.deusley.gerenciadorDeTarefasAPI.mapper.ListaMapper;
import com.deusley.gerenciadorDeTarefasAPI.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @Autowired
    private ListaMapper listaMapper;

    @GetMapping
    public ResponseEntity<List<ListaResponse>> obterTodasAsListas() {
        var response = listaService.obterTodasAsListas();
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ListaResponse> obterListaPorId(@PathVariable Long id) {
        var lista = listaService.obterPorId(id);
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<ListaResponse> criarLista(@RequestBody ListaRequest listaRequest) {
        var tarefa = listaService.criarLista(listaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLista(@PathVariable Long id) {
        listaService.deletarLista(id);
        return ResponseEntity.noContent().build();
    }
}
