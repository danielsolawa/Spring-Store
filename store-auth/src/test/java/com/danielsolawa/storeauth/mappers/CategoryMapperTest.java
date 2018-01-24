package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.CategoryDto;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class CategoryMapperTest {

    CategoryMapper categoryMapper;

    @Before
    public void setUp() throws Exception {
        categoryMapper = CategoryMapper.INSTANCE;
    }

    @Test
    public void categoryDtoToCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Sport");
        categoryDto.setProduct(new Product());

        Category category = categoryMapper.categoryDtoToCategory(categoryDto);

        assertThat(categoryDto.getName(), equalTo(category.getName()));
        assertNotNull(category.getProduct());
    }

    @Test
    public void categoryToCategoryDto() {
        Category category = new Category();
        category.setName("Music");
        category.setProduct(new Product());

        CategoryDto categoryDto = categoryMapper.categoryToCategoryDto(category);


        assertThat(category.getName(), equalTo(categoryDto.getName()));
        assertNotNull(categoryDto.getProduct());
    }
}