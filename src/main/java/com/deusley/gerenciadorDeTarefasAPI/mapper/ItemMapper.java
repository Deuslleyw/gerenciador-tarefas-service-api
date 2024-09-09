package com.deusley.gerenciadorDeTarefasAPI.mapper;

import com.deusley.gerenciadorDeTarefasAPI.controller.request.ItemRequest;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.ListaResponse;
import com.deusley.gerenciadorDeTarefasAPI.domain.Item;
import com.deusley.gerenciadorDeTarefasAPI.controller.response.ItemResponse;
import com.deusley.gerenciadorDeTarefasAPI.domain.Lista;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {


    ItemResponse toItemResponse(Item item);
    Item toItemEntity(ItemResponse itemResponse);

    ItemResponse fromItemEntity(Item item);

    ItemResponse toItemRequest(ItemRequest itemRequest);


}
