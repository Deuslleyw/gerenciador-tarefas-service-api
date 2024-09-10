package com.deusley.gerenciadorDeTarefasAPI.service.impl;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ItemRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.TarefaResponse;
import com.deusley.gerenciadorDeTarefasAPI.domain.Item;
import com.deusley.gerenciadorDeTarefasAPI.domain.Tarefa;
import com.deusley.gerenciadorDeTarefasAPI.enums.Estado;
import com.deusley.gerenciadorDeTarefasAPI.exceptions.ObjectNotFoundException;
import com.deusley.gerenciadorDeTarefasAPI.mapper.ItemMapper;
import com.deusley.gerenciadorDeTarefasAPI.mapper.TarefaMapper;
import com.deusley.gerenciadorDeTarefasAPI.repositories.TarefaRepository;
import com.deusley.gerenciadorDeTarefasAPI.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaMapper tarefaMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public TarefaResponse obterPorId(Long id){
        var tarefa = buscarTarefaPorId(id);
        return tarefaMapper.toListaResponse(tarefa);
    }

    @Override
    public List<TarefaResponse> obterTodas(){
        var tarefas = tarefaRepository.findAll();
        return tarefas.stream()
              .map(tarefaMapper::fromListaEntity)
              .toList();
    }

    @Override
    public TarefaResponse criar(ListaRequest listaRequest){
        var tarefaEntity = tarefaMapper.toListaEntity(listaRequest);
        var tarefaSalva = tarefaRepository.save(tarefaEntity);
        return tarefaMapper.toListaResponse(tarefaSalva);
    }

    @Override
    public void deletar(Long id){
        var tarefa = buscarTarefaPorId(id);
        tarefaRepository.delete(tarefa);
    }

    @Override
    public TarefaResponse adicionarItem(Long tarefaId, ItemRequest itemRequest){
        var tarefa = buscarTarefaPorId(tarefaId);
        var item = criarNovoItem(itemRequest);

        tarefa.getItens().add(item);
        var tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toListaResponse(tarefaAtualizada);
    }

    @Override
    public TarefaResponse atualizarItem(Long tarefaId, Long itemId, ItemRequest itemAtualizado){
        var tarefa = buscarTarefaPorId(tarefaId);
        var item = atualizarItemDaTarefa(tarefa, itemId, itemAtualizado);

        tarefa.getItens().add(item);
        var tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toListaResponse(tarefaAtualizada);
    }

    @Override
    public TarefaResponse removerItem(Long tarefaId, Long itemId){
        var tarefa = buscarTarefaPorId(tarefaId);
        var itemParaRemover = buscarItemPorId(tarefa, itemId);

        tarefa.getItens().remove(itemParaRemover);
        var tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toListaResponse(tarefaAtualizada);
    }

    @Override
    public TarefaResponse alterarEstado(Long tarefaId, Long itemId){
        var tarefa = buscarTarefaPorId(tarefaId);
        var itemParaAlterar = buscarItemPorId(tarefa, itemId);

        itemParaAlterar.setEstado(Estado.CONCLUIDO);
        var tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toListaResponse(tarefaAtualizada);
    }


    private Tarefa buscarTarefaPorId(Long id){
        return tarefaRepository.findById(id)
              .orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada"));
    }

    private Item buscarItemPorId(Tarefa tarefa, Long itemId){
        return tarefa.getItens().stream()
              .filter(item -> item.getId().equals(itemId))
              .findFirst()
              .orElseThrow(() -> new ObjectNotFoundException("Item não encontrado"));
    }

    private Item criarNovoItem(ItemRequest itemRequest){
        var item = itemMapper.toItem(itemRequest);
        item.setEstado(Estado.FAZER);
        return item;
    }

    private Item atualizarItemDaTarefa(Tarefa tarefa, Long itemId, ItemRequest itemAtualizado){
        var item = buscarItemPorId(tarefa, itemId);
        item.setTitulo(itemAtualizado.getTitulo());
        item.setEstado(itemAtualizado.getEstado());
        return item;
    }
}
