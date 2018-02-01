package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.CategoryDto;
import com.danielsolawa.storeauth.dtos.CategoryListDto;
import com.danielsolawa.storeauth.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {


    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDto getCategoryList(){
        return new CategoryListDto(categoryService.getCategoryList());
    }

    @GetMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto getCategoryById(@PathVariable Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody  CategoryDto categoryDto){
        return categoryService.createNewCategory(categoryDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto updateCategory(@PathVariable Long categoryId,@RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(categoryId, categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategoryById(categoryId);
    }

}
