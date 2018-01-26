package com.danielsolawa.storeauth.bootstrap;

import com.danielsolawa.storeauth.domain.*;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import com.danielsolawa.storeauth.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner{


    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Bootstrap(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        loadProducts();
        loadUsers();
    }

    private void loadProducts() {
        if(categoryRepository.count() == 0){

            Category category =  new Category();
            category.setName("Music");

            Product product1 = new Product();
            product1.setName("Queen");
            product1.setPrice(25.00);
            product1.setCategory(category);

            Product product2 = new Product();
            product2.setName("The Beatles");
            product2.setPrice(23.00);
            product2.setCategory(category);

            Product product3 = new Product();
            product3.setName("Elvis Presley");
            product3.setPrice(28.00);
            product3.setCategory(category);



            category.addProduct(product1);
            category.addProduct(product2);
            category.addProduct(product3);

            categoryRepository.save(category).getId();


            log.info("loaded products");
        }
    }

    private void loadUsers() {
        if(userRepository.count() == 0){
            User user = new User();
            user.setUsername("user");
            user.setPassword("password");
            user.setRole(Role.USER);


            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.setUser(user);



            Inventory inventory= new Inventory();

            user.setInventory(inventory);
            user.addOrder(order);


            User savedUser =  userRepository.save(user);


            log.info("saved new user at " + savedUser.getOrders().get(0).getOrderDate().toString());
        }



    }


}
