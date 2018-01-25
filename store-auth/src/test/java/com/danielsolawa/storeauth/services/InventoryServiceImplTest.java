package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.dtos.InventoryDto;
import com.danielsolawa.storeauth.mappers.InventoryMapper;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.refEq;

public class InventoryServiceImplTest {

    InventoryService inventoryService;

    InventoryMapper inventoryMapper;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        inventoryMapper = InventoryMapper.INSTANCE;
        inventoryService = new InventoryServiceImpl(userRepository, inventoryMapper);
    }

    @Test
    public void createNewInventory() {
        User user = getUser();
        User userWithInventory = getUserWithInventory();

        given(userRepository.findOne(anyLong())).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(userWithInventory);

        InventoryDto inventoryDto = inventoryService.createNewInventory( new InventoryDto());

        assertThat(inventoryDto.getProducts(), hasSize(3));
        assertThat(inventoryDto.getUser().getUsername(), equalTo(userWithInventory.getUsername()));

        then(userRepository).should().findOne(anyLong());
        then(userRepository).should().save(any(User.class));

    }



    @Test
    public void updateInventory() {
        User user = getUser();
        User userWithInventory = getUserWithInventory();


        given(userRepository.findOne(anyLong())).willReturn(user);
        given(userRepository.save(any(User.class))).willReturn(userWithInventory);


        InventoryDto inventoryDto = inventoryService.updateInventory(new InventoryDto());

        assertThat(inventoryDto.getProducts(), hasSize(3));
        assertEquals(inventoryDto.getUser().getUsername(), "Hannah");

        then(userRepository).should().findOne(anyLong());
        then(userRepository).should().save(any(User.class));


    }

    @Test
    public void getInventoryByUserId() {
        User user = getUserWithInventory();

        given(userRepository.findOne(anyLong())).willReturn(user);

        InventoryDto inventoryDto = inventoryService.getInventoryByUserId(1L);

        assertThat(inventoryDto.getId(), equalTo(1L));
        assertThat(inventoryDto.getProducts(), hasSize(3));

        then(userRepository).should().findOne(anyLong());
    }

    @Test
    public void deleteInventoryByUserId() {

        given(userRepository.findOne(anyLong())).willReturn(new User());

        inventoryService.deleteInventoryByUserId(1L);

        then(userRepository).should().save(any(User.class));


    }

    private User getUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("Hannah");
        user.setPassword("password");

        return user;

    }

    private User getUserWithInventory() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Hannah");
        user.setPassword("password");


        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setProducts(Arrays.asList(new Product(), new Product(), new Product()));
        inventory.setUser(user);

        user.setInventory(inventory);


        return user;
    }



}