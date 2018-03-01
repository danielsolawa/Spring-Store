package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.mappers.ProductMapper;
import com.danielsolawa.storeauth.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
    public void searchForProductByKeyword() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        given(productRepository.searchForProducts(anyString(), anyString())).willReturn(products);

        List<ProductDto> dtoList = productSearchService.searchForProductByKeyword("search");

        assertThat(dtoList, hasSize(3));

        then(productRepository).should().searchForProducts(anyString(), anyString());

    }
}