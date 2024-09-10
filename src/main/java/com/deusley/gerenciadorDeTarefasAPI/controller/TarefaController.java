package com.deusley.gerenciadorDeTarefasAPI.controller;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ItemRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.TarefaResponse;
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


    private final TarefaService tarefaService;
    private final TarefaMapper tarefaMapper;
    private final TarefaRepository tarefaRepository;

    @Autowired
    public TarefaController(TarefaService tarefaService, TarefaMapper tarefaMapper, TarefaRepository tarefaRepository){
        this.tarefaService = tarefaService;
        this.tarefaMapper = tarefaMapper;
        this.tarefaRepository = tarefaRepository;
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> criar(
          @RequestBody ListaRequest request){
        var tarefa = tarefaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefa);

    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> obterTodas(){
        var response = tarefaService.obterTodas();
        return ResponseEntity.ok().body(response);

    }

    @GetMapping(value = "/{tarefaId}")
    public ResponseEntity<TarefaResponse> obterPorId(
          @PathVariable Long tarefaId){
        var lista = tarefaService.obterPorId(tarefaId);
        return ResponseEntity.ok().body(lista);
    }


    @DeleteMapping("/{tarefaId}")
    public ResponseEntity<Void> deleta(@PathVariable Long tarefaId){
        tarefaService.deletar(tarefaId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{tarefaId}/itens")
    public ResponseEntity<Void> adicionarItem(
          @PathVariable Long tarefaId,
          @RequestBody ItemRequest item){
        tarefaService.adicionarItem(tarefaId, item);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{tarefaId}/itens/{itemId}")
    public ResponseEntity<TarefaResponse> atualizarItem(
          @PathVariable Long tarefaId,
          @PathVariable Long itemId,
          @RequestBody ItemRequest itemRequest){

        var response = tarefaService.atualizarItem(tarefaId, itemId, itemRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{tarefaId}/itens/{itemId}")
    public ResponseEntity<Void> removerItem(
          @PathVariable Long tarefaId,
          @PathVariable Long itemId){
        tarefaService.removerItem(tarefaId, itemId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{tarefaId}/itens/{itemId}/estado")
    public ResponseEntity<TarefaResponse> alterarEstado(
          @PathVariable Long tarefaId,
          @PathVariable Long itemId) {

        var response = tarefaService.alterarEstado(tarefaId, itemId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}