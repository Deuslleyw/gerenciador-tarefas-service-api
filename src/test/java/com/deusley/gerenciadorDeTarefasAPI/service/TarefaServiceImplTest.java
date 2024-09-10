package com.deusley.gerenciadorDeTarefasAPI.service;

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
import com.deusley.gerenciadorDeTarefasAPI.service.impl.TarefaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceImplTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private TarefaMapper tarefaMapper;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private TarefaServiceImpl tarefaService;

    @Test
    public void deveObterPorIdComSucesso(){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        TarefaResponse tarefaResponse = new TarefaResponse();

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaMapper.toListaResponse(tarefa)).thenReturn(tarefaResponse);

        TarefaResponse result = tarefaService.obterPorId(1L);

        assertNotNull(result);
        verify(tarefaRepository, times(1)).findById(1L);
        verify(tarefaMapper, times(1)).toListaResponse(tarefa);
    }

    @Test
    public void deveLancarErroQuandoTarefaNaoForEncontrada(){
        when(tarefaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> tarefaService.obterPorId(1L));

        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    public void deveObterTodasTarefasComSucesso(){
        Tarefa tarefa = new Tarefa();
        TarefaResponse tarefaResponse = new TarefaResponse();
        when(tarefaRepository.findAll()).thenReturn(Collections.singletonList(tarefa));
        when(tarefaMapper.fromListaEntity(tarefa)).thenReturn(tarefaResponse);

        List<TarefaResponse> result = tarefaService.obterTodas();

        assertEquals(1, result.size());
        verify(tarefaRepository, times(1)).findAll();
    }

    @Test
    public void deveCriarTarefaComSucesso(){
        ListaRequest listaRequest = new ListaRequest();
        Tarefa tarefa = new Tarefa();
        TarefaResponse tarefaResponse = new TarefaResponse();

        when(tarefaMapper.toListaEntity(listaRequest)).thenReturn(tarefa);
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        when(tarefaMapper.toListaResponse(tarefa)).thenReturn(tarefaResponse);

        TarefaResponse result = tarefaService.criar(listaRequest);

        assertNotNull(result);
        verify(tarefaRepository, times(1)).save(tarefa);
    }

    @Test
    public void deveDeletarTarefaComSucesso(){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        tarefaService.deletar(1L);

        verify(tarefaRepository, times(1)).delete(tarefa);
    }

    @Test
    public void deveAdicionarItemComSucesso(){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        ItemRequest itemRequest = new ItemRequest();
        Item item = new Item();
        TarefaResponse tarefaResponse = new TarefaResponse();

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(itemMapper.toItem(itemRequest)).thenReturn(item);
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        when(tarefaMapper.toListaResponse(tarefa)).thenReturn(tarefaResponse);

        TarefaResponse result = tarefaService.adicionarItem(1L, itemRequest);

        assertNotNull(result);
        verify(tarefaRepository, times(1)).save(tarefa);
    }

    @Test
    public void deveAtualizarItemComSucesso() {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);

        Item item = new Item();
        item.setId(1L);
        tarefa.getItens().add(item);

        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setTitulo("Novo Título");
        itemRequest.setEstado(Estado.CONCLUIDO);

        TarefaResponse tarefaResponse = new TarefaResponse();

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        when(tarefaMapper.toListaResponse(tarefa)).thenReturn(tarefaResponse);

        TarefaResponse result = tarefaService.atualizarItem(1L, 1L, itemRequest);


        assertNotNull(result);
        verify(tarefaRepository, times(1)).save(tarefa);

        assertEquals("Novo Título", item.getTitulo());
        assertEquals(Estado.CONCLUIDO, item.getEstado());
    }


    @Test
    public void deveRemoverItemComSucesso(){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        Item item = new Item();
        item.setId(1L);
        tarefa.getItens().add(item);
        TarefaResponse tarefaResponse = new TarefaResponse();

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        when(tarefaMapper.toListaResponse(tarefa)).thenReturn(tarefaResponse);

        TarefaResponse result = tarefaService.removerItem(1L, 1L);

        assertNotNull(result);
        verify(tarefaRepository, times(1)).save(tarefa);
    }

    @Test
    public void deveAlterarEstadoItemComSucesso(){
        Tarefa tarefa = new Tarefa();
        tarefa.setId(1L);
        Item item = new Item();
        item.setId(1L);
        tarefa.getItens().add(item);
        TarefaResponse tarefaResponse = new TarefaResponse();

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa)).thenReturn(tarefa);
        when(tarefaMapper.toListaResponse(tarefa)).thenReturn(tarefaResponse);

        TarefaResponse result = tarefaService.alterarEstado(1L, 1L);

        assertNotNull(result);
        verify(tarefaRepository, times(1)).save(tarefa);
    }
}
