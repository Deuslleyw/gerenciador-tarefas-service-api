package com.deusley.gerenciadorDeTarefasAPI.service.impl;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ItemRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.TarefaResponse;
import com.deusley.gerenciadorDeTarefasAPI.domain.Item;
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
    public TarefaResponse obterPorId(Long id) {
        var lista = tarefaRepository.findById(id);
        return tarefaMapper.toListaResponse(lista.orElseThrow(
                () -> new ObjectNotFoundException("Lista não encontrada")
        ));
    }

    @Override
    public List<TarefaResponse> obterTodas() {
        var listas = tarefaRepository.findAll();
        return listas.stream().map(tarefaMapper::fromListaEntity).toList();

    }

    @Override
    public TarefaResponse criar(ListaRequest listaRequest) {
        var listaEntity = tarefaMapper.toListaEntity(listaRequest);
        var response = tarefaRepository.save(listaEntity);
        return tarefaMapper.toListaResponse(response);
    }

    @Override
    public void deletar(Long id) {
        obterPorId(id);
        tarefaRepository.deleteById(id);

    }

    @Override
    public TarefaResponse adicionarItem(Long tarefaId, ItemRequest itemRequest) {

        var tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new ObjectNotFoundException(
                "Lista não encontrada"));
        var item =  itemMapper.toItem(itemRequest);

        item.setEstado(Estado.FAZER);
        tarefa.getItens().add(item);
        var tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toListaResponse(tarefaAtualizada);

    }

    @Override
    public TarefaResponse atualizarItem(Long tarefaId, Long itemId, ItemRequest itemAtualizado) {
        var tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new ObjectNotFoundException(
                "Tarefa não encontrada"));
        Item item = new Item();

        item.setTitulo(itemAtualizado.getTitulo());
        item.setEstado(itemAtualizado.getEstado());
        tarefa.getItens().add(item);
        var tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toListaResponse(tarefaAtualizada);
    }
    @Override
    public TarefaResponse removerItem(Long tarefaId, Long itemId) {
        var tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new ObjectNotFoundException(
                "Tarefa não encontrada"));

        Item itemParaRemover = tarefa.getItens().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Item não encontrado"));
        tarefa.getItens().remove(itemParaRemover);

        var tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toListaResponse(tarefaAtualizada);
    }

    @Override
    public TarefaResponse alterarEstado(Long tarefaId, Long itemId) {
        var tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new ObjectNotFoundException(
                "Tarefa não encontrada"));

        Item itemParaAlterar = tarefa.getItens().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Item não encontrado"));
        itemParaAlterar.setEstado(Estado.CONCLUIDO);
        tarefa.getItens().remove(itemParaAlterar);

        var tarefaAtualizada = tarefaRepository.save(tarefa);
        return tarefaMapper.toListaResponse(tarefaAtualizada);
    }


}




