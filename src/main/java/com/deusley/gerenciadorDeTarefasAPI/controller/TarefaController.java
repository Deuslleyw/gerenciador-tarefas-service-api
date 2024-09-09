package com.deusley.gerenciadorDeTarefasAPI.controller;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ItemRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.TarefaResponse;
import com.deusley.gerenciadorDeTarefasAPI.domain.Item;
import com.deusley.gerenciadorDeTarefasAPI.domain.Tarefa;
import com.deusley.gerenciadorDeTarefasAPI.exceptions.ObjectNotFoundException;
import com.deusley.gerenciadorDeTarefasAPI.mapper.TarefaMapper;
import com.deusley.gerenciadorDeTarefasAPI.repositories.TarefaRepository;
import com.deusley.gerenciadorDeTarefasAPI.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private TarefaMapper tarefaMapper;
    @Autowired
    private TarefaRepository tarefaRepository;


    @PostMapping
    public ResponseEntity<TarefaResponse> criar(@RequestBody ListaRequest listaRequest) {
        var tarefa = tarefaService.criar(listaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);

    }

    @PostMapping("/{tarefaId}/itens")
    public ResponseEntity<Void> adicionarItem(@PathVariable Long tarefaId, @RequestBody ItemRequest item) {
        tarefaService.adicionarItem(tarefaId, item);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{tarefaId}/itens")
    public ResponseEntity<TarefaResponse> atualizarItem(
            @PathVariable Long tarefaId,
            @RequestBody ItemRequest itemRequest) {

        var response = tarefaService.atualizarItem(tarefaId, itemRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{tarefaId}/itens/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long tarefaId,
                                            @PathVariable Long itemId) {
        tarefaService.removerItem(tarefaId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> obterTodasAsTarefas() {
        var response = tarefaService.obterTodas();
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TarefaResponse> obterListaPorId(@PathVariable Long id) {
        var lista = tarefaService.obterPorId(id);
        return ResponseEntity.ok().body(lista);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleta(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}