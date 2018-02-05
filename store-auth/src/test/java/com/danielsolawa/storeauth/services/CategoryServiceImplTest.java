package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.CategoryDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.CategoryMapper;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

public class CategoryServiceImplTest {

    CategoryMapper categoryMapper;

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryMapper = CategoryMapper.INSTANCE;

        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
    }

    @Test
    public void getCategoryList() {
        List<Category> categories = Arrays.asList(new Category(), new Category());

        given(categoryRepository.findAll()).willReturn(categories);

        List<CategoryDto> dtoList = categoryService.getCategoryList();

        assertThat(categories, hasSize(2));

        then(categoryRepository).should().findAll();

    }

    @Test
    public void getCategoryByIdHappyPath() {
        Category category = new Category();
        category.setId(1L);
        category.setProducts(Arrays.asList(new Product(), new Product()));

        given(categoryRepository.findOne(anyLong())).willReturn(category);

        CategoryDto categoryDto = categoryService.getCategoryById(1L);

        assertThat(categoryDto.getProducts(), hasSize(2));
        assertThat(categoryDto.getId(), equalTo(1L));

        then(categoryRepository).should().findOne(anyLong());
    }


    @Test(expected = ResourceNotFoundException.class)
    public void getCategoryByIdFailure() {

        given(categoryRepository.findOne(anyLong())).willThrow(ResourceNotFoundException.class);


        CategoryDto categoryDto = categoryService.getCategoryById(1L);

        then(categoryRepository).should().findOne(anyLong());
    }

    @Test
    public void createNewCategory() {
        Category category = new Category();
        category.setId(1L);

        given(categoryRepository.save(any(Category.class))).willReturn(category);

        CategoryDto categoryDto = categoryService.createNewCategory(new CategoryDto());

        assertThat(categoryDto.getId(), equalTo(1L));

        then(categoryRepository).should().save(any(Category.class));
    }

    @Test
    public void updateCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setProducts(Arrays.asList(new Product()));

        given(categoryRepository.findOne(anyLong())).willReturn(category);
        given(categoryRepository.save(any(Category.class))).willReturn(category);

        CategoryDto categoryDto = categoryService.updateCategory(1L, new CategoryDto());

        assertThat(categoryDto.getId(), equalTo(1L));
        assertThat(categoryDto.getProducts(), hasSize(1));

        then(categoryRepository).should().save(any(Category.class));

    }

    @Test
    public void deleteCategoryById() {

        categoryService.deleteCategoryById(1L);

        then(categoryRepository).should().delete(anyLong());
    }
}