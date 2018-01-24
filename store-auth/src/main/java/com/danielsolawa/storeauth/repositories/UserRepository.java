package com.danielsolawa.storeauth.repositories;


import com.danielsolawa.storeauth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
