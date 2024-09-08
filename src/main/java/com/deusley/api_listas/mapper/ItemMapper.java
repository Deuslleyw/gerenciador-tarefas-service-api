package com.deusley.api_listas.mapper;

import com.deusley.api_listas.controller.request.ItemRequest;
import com.deusley.api_listas.domain.Item;
import com.deusley.api_listas.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItemEntity(ItemDTO itemDTO);

    ItemDTO fromItemEntity(Item item);

    ItemDTO toItemRequest(ItemRequest itemRequest);


}
