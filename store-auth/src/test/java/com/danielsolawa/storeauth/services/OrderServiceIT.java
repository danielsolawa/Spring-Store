package com.danielsolawa.storeauth.services;


import com.danielsolawa.storeauth.bootstrap.Bootstrap;
import com.danielsolawa.storeauth.domain.Order;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderServiceIT {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;


    Bootstrap bootstrap;


    @Before
    public void setUp() throws Exception {
        bootstrap = new Bootstrap(userRepository, categoryRepository);
        bootstrap.run();

        System.out.println("Loading initial data...");
    }

    @Test
    public void updateOrder() {
        List<Product> products = getProducts();
        Long userId = getUserId();

        User user = userRepository.findOne(userId);

        assertTrue(products.size() > 0);
        assertNotNull(user);

        Order order = user.getOrders().get(0);

        assertNotNull(order);

        LocalDateTime oldDate = order.getOrderDate();
        order.setOrderDate(LocalDateTime.now());
        order.setProducts(products);

        user.getOrders().remove(0);
        user.addOrder(order);

        //save changes
        userRepository.save(user);

        //fetch updated user
        User updatedUser = userRepository.findOne(userId);
        Order updatedOrder = updatedUser.getOrders().get(0);

        assertNotNull(updatedOrder);
        assertThat(updatedOrder.getOrderDate(), not(equalTo(oldDate)));
        assertThat(updatedOrder.getProducts(), hasSize(3));

    }


    private Long getUserId(){
        return userRepository.findAll().get(0).getId();
    }



    private List<Product> getProducts(){

        return categoryRepository.findAll().get(0).getProducts();
    }
}
