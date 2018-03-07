package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.services.ProductService;
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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends AbstractControllerTest{

    MockMvc mockMvc;

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void getProductListNoParams() throws Exception {
        List<ProductDto> products = Arrays.asList(new ProductDto(), new ProductDto(), new ProductDto());

        given(productService.countProductListByCategoryId(anyLong())).willReturn(3L);
        given(productService.getProductListByCategoryId(anyLong())).willReturn(products);

        mockMvc.perform(get(ProductController.BASE_URL + "/1/products")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(3)))
                .andExpect(jsonPath("$.amount", equalTo(3)));

        then(productService).should().getProductListByCategoryId(anyLong());

    }


    @Test
    public void getProductListWithParams() throws Exception {
        List<ProductDto> products = Arrays.asList(new ProductDto(), new ProductDto(), new ProductDto());

        given(productService.countProductListByCategoryId(anyLong())).willReturn(3L);
        given(productService.getProductListByCategoryId(anyLong(), anyInt(), anyInt())).willReturn(products);

        mockMvc.perform(get(ProductController.BASE_URL + "/1/products?start=0&end=3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(3)));

        then(productService).should().getProductListByCategoryId(anyLong(), anyInt(), anyInt());

    }


    @Test
    public void getProductById() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);

        given(productService.getProductById(anyLong(), anyLong())).willReturn(productDto);

        mockMvc.perform(get(ProductController.BASE_URL + "/1/products/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(productService).should().getProductById(anyLong(), anyLong());
    }

    @Test
    public void createNewProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);

        given(productService.createNewProduct(anyLong(), any(ProductDto.class))).willReturn(productDto);

        mockMvc.perform(post(ProductController.BASE_URL + "/1/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(productService).should().createNewProduct(anyLong(), any(ProductDto.class));

    }

    @Test
    public void updateProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);

        given(productService.updateProduct(anyLong(), anyLong(), any(ProductDto.class))).willReturn(productDto);

        mockMvc.perform(put(ProductController.BASE_URL + "/1/products/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));

        then(productService).should().updateProduct(anyLong(), anyLong(), any(ProductDto.class));


    }

    @Test
    public void deleteProductById() throws Exception {
        mockMvc.perform(delete(ProductController.BASE_URL + "/1/products/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(productService).should().deleteProductById(anyLong(), anyLong());

    }
}