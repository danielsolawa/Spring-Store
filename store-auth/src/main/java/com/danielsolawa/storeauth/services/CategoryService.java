package com.danielsolawa.storeauth.services;


import com.danielsolawa.storeauth.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategoryList();
    CategoryDto getCategoryById(Long categoryId);
    CategoryDto createNewCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);
    void deleteCategoryById(Long categoryId);


}
