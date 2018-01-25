package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.InventoryDto;

public interface InventoryService {

    InventoryDto createNewInventory(Long userId, InventoryDto inventoryDto);
    InventoryDto updateInventory(Long userId,InventoryDto inventoryDto);
    InventoryDto getInventoryByUserId(Long userId);
    void deleteInventoryByUserId(Long userId);

}
