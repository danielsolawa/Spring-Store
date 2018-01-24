package com.danielsolawa.storeauth.mappers;


import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.dtos.InventoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    Inventory inventoryDtoToInventory(InventoryDto inventoryDto);
    InventoryDto inventoryToInventoryDto(Inventory inventory);
}
