package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.InventoryDto;

public interface InventoryService {

    InventoryDto updateInventory(InventoryDto inventoryDto);
    InventoryDto getInventoryByUserId(Long userId);
    void deleteInventoryByUserId(Long userId);

}
