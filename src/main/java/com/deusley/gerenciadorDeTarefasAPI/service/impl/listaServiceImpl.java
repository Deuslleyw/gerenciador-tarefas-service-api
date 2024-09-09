package com.deusley.gerenciadorDeTarefasAPI.service.impl;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.ListaResponse;
import com.deusley.gerenciadorDeTarefasAPI.exceptions.ObjectNotFoundException;
import com.deusley.gerenciadorDeTarefasAPI.mapper.ListaMapper;
import com.deusley.gerenciadorDeTarefasAPI.repositories.ListaRepository;
import com.deusley.gerenciadorDeTarefasAPI.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class listaServiceImpl implements ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ListaMapper listaMapper;


    @Override
    public ListaResponse obterPorId(Long id) {
        var lista = listaRepository.findById(id);
        return listaMapper.toListaResponse(lista.orElseThrow(
                () -> new ObjectNotFoundException("Lista não encontrada")
        ));
    }

    @Override
    public List<ListaResponse> obterTodasAsListas() {
        var listas = listaRepository.findAll();
        return listas.stream().map(listaMapper::fromListaEntity).toList();

    }

    @Override
    public ListaResponse criarLista(ListaRequest listaRequest) {
        var listaEntity = listaMapper.toListaEntity(listaRequest);
        var response = listaRepository.save(listaEntity);
        return listaMapper.toListaResponse(response);
    }

    @Override
    public void deletarLista(Long id) {
        obterPorId(id);
        listaRepository.deleteById(id);

    }


}




