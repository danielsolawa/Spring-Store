package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.InventoryDto;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

public class InventoryMapperTest {

    InventoryMapper inventoryMapper;

    @Before
    public void setUp() throws Exception {

        inventoryMapper = InventoryMapper.INSTANCE;
    }

    @Test
    public void inventoryDtoToInventory() {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setProducts(Arrays.asList(new Product(), new Product()));
        inventoryDto.setUser(new User());

        Inventory inventory = inventoryMapper.inventoryDtoToInventory(inventoryDto);

        assertThat(inventory.getProducts(), hasSize(2));
        assertNotNull(inventory.getUser());

    }

    @Test
    public void inventoryToInventoryDto() {
        Inventory inventory = new Inventory();
        inventory.setProducts(Arrays.asList(new Product(), new Product()));
        inventory.setUser(new User());

        InventoryDto inventoryDto = inventoryMapper.inventoryToInventoryDto(inventory);

        assertThat(inventoryDto.getProducts(), hasSize(2));
        assertNotNull(inventoryDto.getUser());



    }
}