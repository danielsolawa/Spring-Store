package com.danielsolawa.storeauth.repositories;

import com.danielsolawa.storeauth.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{
}
