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
            product1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et nibh a purus elementum elementum sit amet vitae odio. Fusce sed urna id lectus dapibus bibendum ut sed neque." +
                    " Duis fermentum sagittis sem, in posuere tortor venenatis et. Quisque at ex dolor. Maecenas placerat interdum suscipit. Nullam venenatis quis nisl vel laoreet. Praesent semper sodales lorem, at maximus ligula aliquet nec. " +
                    "Pellentesque quis odio congue, lobortis leo eu, volutpat sapien. Nam finibus magna vel metus facilisis, vitae porta ligula porta. Nulla facilisi. Aliquam eget ex sit amet lorem porttitor viverra. Integer tincidunt, est ac posuere pretium, lectus nisi maximus tellus, ac bibendum risus sem id ex. " +
                    "Proin dapibus ligula efficitur ligula sollicitudin, sit amet fringilla ex volutpat.");
            product1.setCategory(category1);

            Product product2 = new Product();
            product2.setName("The Beatles");
            product2.setPrice(23.00);
            product2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et nibh a purus elementum elementum sit amet vitae odio. Fusce sed urna id lectus dapibus bibendum ut sed neque." +
                    " Duis fermentum sagittis sem, in posuere tortor venenatis et. Quisque at ex dolor. Maecenas placerat interdum suscipit. Nullam venenatis quis nisl vel laoreet. Praesent semper sodales lorem, at maximus ligula aliquet nec. " +
                    "Pellentesque quis odio congue, lobortis leo eu, volutpat sapien. Nam finibus magna vel metus facilisis, vitae porta ligula porta. Nulla facilisi. Aliquam eget ex sit amet lorem porttitor viverra. Integer tincidunt, est ac posuere pretium, lectus nisi maximus tellus, ac bibendum risus sem id ex. " +
                    "Proin dapibus ligula efficitur ligula sollicitudin, sit amet fringilla ex volutpat.");
            product2.setCategory(category1);

            Product product3 = new Product();
            product3.setName("Elvis Presley");
            product3.setPrice(28.00);
            product3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et nibh a purus elementum elementum sit amet vitae odio. Fusce sed urna id lectus dapibus bibendum ut sed neque." +
                    " Duis fermentum sagittis sem, in posuere tortor venenatis et. Quisque at ex dolor. Maecenas placerat interdum suscipit. Nullam venenatis quis nisl vel laoreet. Praesent semper sodales lorem, at maximus ligula aliquet nec. " +
                    "Pellentesque quis odio congue, lobortis leo eu, volutpat sapien. Nam finibus magna vel metus facilisis, vitae porta ligula porta. Nulla facilisi. Aliquam eget ex sit amet lorem porttitor viverra. Integer tincidunt, est ac posuere pretium, lectus nisi maximus tellus, ac bibendum risus sem id ex. " +
                    "Proin dapibus ligula efficitur ligula sollicitudin, sit amet fringilla ex volutpat.");
            product3.setCategory(category1);


            Product product4 = new Product();
            product4.setName("Honda");
            product4.setPrice(122228.00);
            product4.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et nibh a purus elementum elementum sit amet vitae odio. Fusce sed urna id lectus dapibus bibendum ut sed neque." +
                    " Duis fermentum sagittis sem, in posuere tortor venenatis et. Quisque at ex dolor. Maecenas placerat interdum suscipit. Nullam venenatis quis nisl vel laoreet. Praesent semper sodales lorem, at maximus ligula aliquet nec. " +
                    "Pellentesque quis odio congue, lobortis leo eu, volutpat sapien. Nam finibus magna vel metus facilisis, vitae porta ligula porta. Nulla facilisi. Aliquam eget ex sit amet lorem porttitor viverra. Integer tincidunt, est ac posuere pretium, lectus nisi maximus tellus, ac bibendum risus sem id ex. " +
                    "Proin dapibus ligula efficitur ligula sollicitudin, sit amet fringilla ex volutpat.");
            product4.setCategory(category2);

            Product product5 = new Product();
            product5.setName("Ford");
            product5.setPrice(15228.00);
            product5.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras et nibh a purus elementum elementum sit amet vitae odio. Fusce sed urna id lectus dapibus bibendum ut sed neque." +
                    " Duis fermentum sagittis sem, in posuere tortor venenatis et. Quisque at ex dolor. Maecenas placerat interdum suscipit. Nullam venenatis quis nisl vel laoreet. Praesent semper sodales lorem, at maximus ligula aliquet nec. " +
                    "Pellentesque quis odio congue, lobortis leo eu, volutpat sapien. Nam finibus magna vel metus facilisis, vitae porta ligula porta. Nulla facilisi. Aliquam eget ex sit amet lorem porttitor viverra. Integer tincidunt, est ac posuere pretium, lectus nisi maximus tellus, ac bibendum risus sem id ex. " +
                    "Proin dapibus ligula efficitur ligula sollicitudin, sit amet fringilla ex volutpat.");
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
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            admin.setEnabled(true);
            admin.setAccountNonExpired(true);
            admin.setAccountNonLocked(true);
            admin.setCredentialsNonExpired(true);


            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.setUser(user);



            Inventory inventory= new Inventory();

            user.setInventory(inventory);
            user.addOrder(order);


            User savedUser =  userRepository.save(user);
            userRepository.save(admin);


            log.info("saved new user at " + savedUser.getOrders().get(0).getOrderDate().toString());
        }



    }


}
