package com.danielsolawa.storeauth.controllers;


import com.danielsolawa.storeauth.dtos.InventoryDto;
import com.danielsolawa.storeauth.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(InventoryController.BASE_URL)
public class InventoryController {

    public static final String BASE_URL = "/api/v1/users";

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/{userId}/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryDto createNewInventory(@PathVariable Long userId, @RequestBody InventoryDto inventoryDto){


        return inventoryService.createNewInventory(inventoryDto);
    }

    @GetMapping("/{userId}/inventory")
    @ResponseStatus(HttpStatus.OK)
    public InventoryDto getInventoryByUserId(@PathVariable Long userId){

        return inventoryService.getInventoryByUserId(userId);
    }

    @PutMapping("/{userId}/inventory")
    @ResponseStatus(HttpStatus.OK)
    public InventoryDto updateInventory(@PathVariable Long userId, @RequestBody InventoryDto inventoryDto){
        return inventoryService.updateInventory(inventoryDto);
    }


    @DeleteMapping("/{userId}/inventory")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInventory(@PathVariable  Long userId){
        inventoryService.deleteInventoryByUserId(userId);
    }
}
