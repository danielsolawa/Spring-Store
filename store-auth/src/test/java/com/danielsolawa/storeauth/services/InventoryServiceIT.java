package com.danielsolawa.storeauth.services;


import com.danielsolawa.storeauth.bootstrap.Bootstrap;
import com.danielsolawa.storeauth.domain.Inventory;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InventoryServiceIT {


    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Before
    public void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(userRepository, categoryRepository);
        bootstrap.run();
    }

    @Test
    public void createInventory() {
        User user = new User();
        user.setUsername("John");
        user.setPassword("password");

        User savedUser = userRepository.save(user);
        Long savedUserId = savedUser.getId();

        assertNotNull(savedUser);

        Inventory inventory = new Inventory();
        inventory.setUser(user);

        savedUser.setInventory(inventory);

        userRepository.save(savedUser);

        User userWithInventory = userRepository.findOne(savedUserId);

        assertNotNull(userWithInventory.getInventory());

    }




}
