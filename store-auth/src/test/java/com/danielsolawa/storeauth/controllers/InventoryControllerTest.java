package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.InventoryDto;
import com.danielsolawa.storeauth.services.InventoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InventoryControllerTest extends AbstractControllerTest{


    @InjectMocks
    InventoryController inventoryController;

    @Mock
    InventoryService inventoryService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();

    }


    @Test
    public void getInventoryByUserId() throws Exception {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setId(1L);
        inventoryDto.setProducts(Arrays.asList(new Product()));

        given(inventoryService.getInventoryByUserId(anyLong())).willReturn(inventoryDto);

        mockMvc.perform(get(InventoryController.BASE_URL + "/1/inventory")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(1)));

        then(inventoryService).should().getInventoryByUserId(anyLong());
    }

    @Test
    public void updateInventory() throws Exception {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setId(1L);
        inventoryDto.setProducts(Arrays.asList(new Product()));

        given(inventoryService.updateInventory(any(InventoryDto.class))).willReturn(inventoryDto);

        mockMvc.perform(put(InventoryController.BASE_URL + "/1/inventory")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(inventoryDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(1)));

        then(inventoryService).should().updateInventory(any(InventoryDto.class));

    }

    @Test
    public void deleteInventory() throws Exception {

        mockMvc.perform(delete(InventoryController.BASE_URL + "/1/inventory")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(inventoryService).should().deleteInventoryByUserId(anyLong());

    }
}