package com.danielsolawa.storeauth.repositories;

import com.danielsolawa.storeauth.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("From Product p WHERE (p.name LIKE %:first OR p.name LIKE :second% OR p.name LIKE %:third%" +
            " OR p.category.name LIKE %:first OR p.category.name LIKE :second% OR p.category.name LIKE %:third%)")
    List<Product> searchForProducts(@Param("first") String first, @Param("second") String second,
                                    @Param("third") String third);
}
