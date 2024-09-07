package com.deusley.api_listas.service.impl;

import com.deusley.api_listas.domain.Lista;
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


    @Override
    public Lista obterPorId(Long id) {
        Optional<Lista> obj = listaRepository.findById(id);
        return obj.orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Lista> obterTodasAsListas() {
        return listaRepository.findAll();
    }

    @Override
    public Lista criarLista(Lista lista) {
        return listaRepository.save(lista);
    }

    @Override
    public void deletarLista(Long id) {
        obterPorId(id);
        listaRepository.deleteById(id);

    }

}

