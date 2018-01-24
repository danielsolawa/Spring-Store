package com.danielsolawa.storeauth.mappers;

import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.ProductDto;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class ProductMapperTest {

    ProductMapper productMapper;

    @Before
    public void setUp() throws Exception {
        productMapper = ProductMapper.INSTANCE;
    }

    @Test
    public void productDtoToProduct() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Bicycle");
        productDto.setPrice(150.00);

        Product product = productMapper.productDtoToProduct(productDto);

        assertThat(productDto.getName(), equalTo(product.getName()));
        assertThat(productDto.getPrice(), equalTo(product.getPrice()));

    }

    @Test
    public void productToProductDto() {
        Product product = new Product();
        product.setName("Bicycle");
        product.setPrice(150.00);

        ProductDto productDto = productMapper.productToProductDto(product);

        assertThat(product.getName(), equalTo(productDto.getName()));
        assertThat(product.getPrice(), equalTo(productDto.getPrice()));
    }
}