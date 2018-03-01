package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.services.ProductSearchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
    public void searchForProducts() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(new ProductDto());
        productDtos.add(new ProductDto());
        productDtos.add(new ProductDto());

        given(productSearchService.searchForProductByKeyword(anyString())).willReturn(productDtos);

        mockMvc.perform(get(ProductSearchController.BASE_URL + "/keyword")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(3)));

        then(productSearchService).should().searchForProductByKeyword(anyString());


    }
}