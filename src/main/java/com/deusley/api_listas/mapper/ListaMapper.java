package com.deusley.api_listas.mapper;

import com.deusley.api_listas.domain.Lista;
import com.deusley.api_listas.dto.ListaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ListaMapper {

    Lista toListaEntity(ListaDTO listaDTO);
    ListaDTO fromListaEntity(Lista lista);

}
