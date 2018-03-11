package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.mappers.ProductMapper;
import com.danielsolawa.storeauth.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

public class ProductSearchServiceImplTest {

    @Mock
    ProductRepository productRepository;

    ProductMapper productMapper;

    ProductSearchService productSearchService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productMapper = ProductMapper.INSTANCE;
        productSearchService = new ProductSearchServiceImpl(productRepository, productMapper);
    }

    @Test
    public void countSearchProductsByKeyword() {

        given(productRepository.countSearchForProducts(anyString(), anyString(), anyString())).willReturn(3L);

        Long productNumbers = productSearchService.countSearchForProductByKeyword("keyword");

        assertThat(productNumbers, equalTo(3L));

        then(productRepository).should().countSearchForProducts(anyString(), anyString(), anyString());
    }

    @Test
    public void searchForProductByKeyword() {
        List<Product> products = Arrays.asList(new Product(), new Product(), new Product());

        given(productRepository.searchForProducts(anyString(), anyString(), anyString())).willReturn(products);


        List<ProductDto> dtoList = productSearchService.searchForProductByKeyword("search");

        assertThat(dtoList, hasSize(3));

        then(productRepository).should().searchForProducts(anyString(), anyString(), anyString());

    }

    @Test
    public void searchForProductByKeywordPageable() {
        List<Product> products = Arrays.asList(new Product(), new Product(), new Product());

        given(productRepository.searchForProducts(
                anyString(), anyString(), anyString(), any(Pageable.class))).willReturn(products);

        List<ProductDto> dtoList = productSearchService.searchForProductByKeyword("search", 0, 5);

        assertThat(dtoList, hasSize(3));

        then(productRepository).should().searchForProducts(anyString(), anyString(), anyString(), any(Pageable.class));
    }
}