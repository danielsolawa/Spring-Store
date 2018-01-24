package com.danielsolawa.storeauth.bootstrap;

import com.danielsolawa.storeauth.domain.Role;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner{


    private final UserRepository userRepository;

    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {

        if(userRepository.count() == 0){
            User user = new User();
            user.setUsername("daniel");
            user.setPassword("password");
            user.setRole(Role.USER);

            userRepository.save(user);


            log.info("saved new user ");
        }


        User savedUser = userRepository.findAll().get(0);

        log.info("User " +  savedUser.toString());
    }
}
