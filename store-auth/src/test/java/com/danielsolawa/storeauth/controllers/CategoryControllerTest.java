package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.dtos.CategoryDto;
import com.danielsolawa.storeauth.dtos.CategoryListDto;
import com.danielsolawa.storeauth.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest extends AbstractControllerTest{

    MockMvc mockMvc;

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryService categoryService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void getCategoryList() throws Exception {
        List<CategoryDto> categories =  Arrays.asList(new CategoryDto(), new CategoryDto());

        given(categoryService.getCategoryList()).willReturn(categories);

        mockMvc.perform(get(CategoryController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));


        then(categoryService).should().getCategoryList();
    }

    @Test
    public void getCategoryById() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        given(categoryService.getCategoryById(anyLong())).willReturn(categoryDto);

        mockMvc.perform(get(CategoryController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(categoryService).should().getCategoryById(anyLong());


    }

    @Test
    public void createCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        given(categoryService.createNewCategory(any(CategoryDto.class))).willReturn(categoryDto);

        mockMvc.perform(post(CategoryController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(categoryDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(categoryService).should().createNewCategory(any(CategoryDto.class));

    }

    @Test
    public void updateCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);

        given(categoryService.updateCategory(anyLong(), any(CategoryDto.class))).willReturn(categoryDto);

        mockMvc.perform(put(CategoryController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(categoryDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(categoryService).should().updateCategory(anyLong(), any(CategoryDto.class));


    }

    @Test
    public void deleteCategory() throws Exception {

        mockMvc.perform(delete(CategoryController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(categoryService).should().deleteCategoryById(anyLong());
    }
}