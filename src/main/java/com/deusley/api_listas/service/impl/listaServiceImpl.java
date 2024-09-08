package com.deusley.api_listas.service.impl;

import com.deusley.api_listas.controller.request.ListaRequest;
import com.deusley.api_listas.domain.Lista;
import com.deusley.api_listas.controller.response.ListaResponse;
import com.deusley.api_listas.mapper.ListaMapper;
import com.deusley.api_listas.repositories.ListaRepository;
import com.deusley.api_listas.service.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                () -> new RuntimeException("Lista Não Encontrada")
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




