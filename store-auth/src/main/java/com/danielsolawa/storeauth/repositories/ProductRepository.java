package com.danielsolawa.storeauth.repositories;

import com.danielsolawa.storeauth.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("From Product WHERE name LIKE :keyword%")
    List<Product> searchForProducts(@Param("keyword") String keyword);
}
