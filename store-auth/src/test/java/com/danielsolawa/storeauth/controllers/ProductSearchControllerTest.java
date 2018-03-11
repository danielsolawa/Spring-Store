package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.services.ProductSearchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductSearchControllerTest extends AbstractControllerTest{

    @InjectMocks
    ProductSearchController productSearchController;

    MockMvc mockMvc;

    @Mock
    ProductSearchService productSearchService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productSearchController).build();
    }


    @Test
    public void searchForProductsPageable() throws Exception {
        List<ProductDto> productDtos = Arrays.asList(new ProductDto(), new ProductDto(), new ProductDto());

        given(productSearchService.searchForProductByKeyword(anyString(), anyInt(), anyInt())).willReturn(productDtos);
        given(productSearchService.countSearchForProductByKeyword(anyString())).willReturn(3L);

        mockMvc.perform(get(ProductSearchController.BASE_URL + "/keyword?page=5&size=5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(3)))
                .andExpect(jsonPath("$.amount", equalTo(3)));

        then(productSearchService).should().searchForProductByKeyword(anyString(), anyInt(), anyInt());
        then(productSearchService).should().countSearchForProductByKeyword(anyString());


    }
}