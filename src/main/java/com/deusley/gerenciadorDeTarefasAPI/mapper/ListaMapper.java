package com.deusley.gerenciadorDeTarefasAPI.mapper;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ListaRequest;
import com.deusley.gerenciadorDeTarefasAPI.domain.Lista;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.ListaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ListaMapper {

    ListaResponse toListaResponse(Lista lista);
    ListaResponse fromListaEntity(Lista lista);

    ListaResponse toListaRequest(ListaRequest listaRequest);

    Lista toListaEntity(ListaRequest listaRequest);
}
