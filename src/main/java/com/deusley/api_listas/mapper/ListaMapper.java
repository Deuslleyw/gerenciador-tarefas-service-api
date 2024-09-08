package com.deusley.api_listas.mapper;

import com.deusley.api_listas.controller.request.ListaRequest;
import com.deusley.api_listas.domain.Lista;
import com.deusley.api_listas.controller.response.ListaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ListaMapper {

    ListaResponse toListaResponse(Lista lista);
    ListaResponse fromListaEntity(Lista lista);

    ListaResponse toListaRequest(ListaRequest listaRequest);


    Lista toListaEntity(ListaRequest listaRequest);
}
