package com.danielsolawa.storeauth.services;


import com.danielsolawa.storeauth.bootstrap.Bootstrap;
import com.danielsolawa.storeauth.domain.Role;
import com.danielsolawa.storeauth.domain.User;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import com.danielsolawa.storeauth.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserControllerIT {

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
    public void createUser() {
        User user = new User();
        user.setUsername("Thomas");
        user.setPassword("password");
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);

        Long id = savedUser.getId();

        User foundUser = userRepository.findOne(id);

        assertNotNull(foundUser);
        assertThat(user.getUsername(), equalTo(foundUser.getUsername()));
        assertThat(user.getPassword(), equalTo(foundUser.getPassword()));
        assertThat(user.getRole(), equalTo(foundUser.getRole()));


        //delete created user

        userRepository.delete(id);

        User deletedUser = userRepository.findOne(id);

        assertNull(deletedUser);

    }


    @Test
    public void updateUser() {
        Long id = getUserId();

        User foundUser = userRepository.findOne(id);

        assertNotNull(foundUser);

        String oldUsername = foundUser.getUsername();
        String oldPassword = foundUser.getPassword();

        foundUser.setUsername("Samantha");
        foundUser.setPassword("newPassword");

        userRepository.save(foundUser);

        User updatedUser = userRepository.findOne(id);

        assertNotNull(updatedUser);
        assertThat(updatedUser.getUsername(), not(equalTo(oldUsername)));
        assertThat(updatedUser.getPassword(), not(equalTo(oldPassword)));

    }


    private Long getUserId(){
        return userRepository.findAll().get(0).getId();
    }
}
