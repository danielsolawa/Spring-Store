package com.danielsolawa.storeauth.repositories;


import com.danielsolawa.storeauth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
