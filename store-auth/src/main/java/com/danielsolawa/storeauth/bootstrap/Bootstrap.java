package com.danielsolawa.storeauth.bootstrap;

import com.danielsolawa.storeauth.domain.*;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import com.danielsolawa.storeauth.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Profile("dev")
@Component
public class Bootstrap implements CommandLineRunner{


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public Bootstrap(PasswordEncoder passwordEncoder, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.passwordEncoder = passwordEncoder;
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

            Category category1 =  new Category();
            category1.setName("Music");

            Category category2 =  new Category();
            category2.setName("Cars");

            Product product1 = new Product();
            product1.setName("Queen");
            product1.setPrice(25.00);
            product1.setCategory(category1);

            Product product2 = new Product();
            product2.setName("The Beatles");
            product2.setPrice(23.00);
            product2.setCategory(category1);

            Product product3 = new Product();
            product3.setName("Elvis Presley");
            product3.setPrice(28.00);
            product3.setCategory(category1);


            Product product4 = new Product();
            product4.setName("Honda");
            product4.setPrice(122228.00);
            product4.setCategory(category2);

            Product product5 = new Product();
            product5.setName("Ford");
            product5.setPrice(15228.00);
            product5.setCategory(category2);



            category1.addProduct(product1);
            category1.addProduct(product2);
            category1.addProduct(product3);

            category2.addProduct(product4);
            category2.addProduct(product5);

            categoryRepository.save(category1);
            categoryRepository.save(category2);


            log.info("loaded products");
        }
    }

    private void loadUsers() {
        if(userRepository.count() == 0){
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole(Role.USER);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);


            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.setUser(user);



            Inventory inventory= new Inventory();

            user.setInventory(inventory);
            user.addOrder(order);

            userRepository.save(admin);
            User savedUser =  userRepository.save(user);


            log.info("saved new user at " + savedUser.getOrders().get(0).getOrderDate().toString());
        }



    }


}
