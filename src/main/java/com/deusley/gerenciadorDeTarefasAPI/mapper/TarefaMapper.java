package com.deusley.gerenciadorDeTarefasAPI.mapper;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.TarefaResponse;
import com.deusley.gerenciadorDeTarefasAPI.domain.Tarefa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefaResponse toListaResponse(Tarefa tarefa);
    TarefaResponse fromListaEntity(Tarefa tarefa);
    Tarefa toListaEntity(ListaRequest listaRequest);

}
