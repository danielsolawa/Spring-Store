package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.mappers.ProductMapper;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

public class ProductServiceImplTest {

    ProductService productService;

    ProductMapper productMapper;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productMapper = ProductMapper.INSTANCE;
        productService = new ProductServiceImpl(categoryRepository, productMapper);

    }

    @Test
    public void getProductList() {
        Category category = new Category();
        category.setId(1L);

        List<Product> products = Arrays.asList(new Product(), new Product());
        category.setProducts(products);


        given(categoryRepository.findOne(anyLong())).willReturn(category);

        List<ProductDto> dtoList = productService.getProductList(1l);

        assertThat(dtoList, hasSize(2));

        then(categoryRepository).should().findOne(anyLong());
    }

    @Test
    public void getProductByIdHappyPath() {
        Category category = new Category();
        category.setId(1L);

        Product product = new Product();
        product.setId(1L);

        category.addProduct(product);

        given(categoryRepository.findOne(anyLong())).willReturn(category);

        ProductDto productDto = productService.getProductById(1L, 1L);

        assertThat(productDto.getId(), equalTo(1L));

        then(categoryRepository).should().findOne(anyLong());

    }

    @Test(expected = NoSuchElementException.class)
    public void getProductByIdHappyPathFailure() {

        given(categoryRepository.findOne(anyLong())).willThrow(NoSuchElementException.class);

        ProductDto productDto = productService.getProductById(1L, 1L);

        then(categoryRepository).should().findOne(anyLong());

    }

    @Test
    public void createNewProduct() {
        Category category = new Category();
        category.setId(1L);

        Product product = new Product();
        product.setId(1L);

        Category categoryWithProduct = new Category();
        categoryWithProduct.setId(1L);
        categoryWithProduct.addProduct(product);


        given(categoryRepository.findOne(anyLong())).willReturn(category);
        given(categoryRepository.save(any(Category.class))).willReturn(categoryWithProduct);

        ProductDto productDto = productService.createNewProduct( 1L, new ProductDto());

        assertThat(productDto.getId(), equalTo(1L));

        then(categoryRepository).should().findOne(anyLong());
        then(categoryRepository).should().save(any(Category.class));



    }

    @Test
    public void updateProductHappyPath() {

        Product product = new Product();
        product.setId(1L);

        Category categoryWithProduct = new Category();
        categoryWithProduct.setId(1L);
        categoryWithProduct.addProduct(product);

        given(categoryRepository.findOne(anyLong())).willReturn(categoryWithProduct);
        given(categoryRepository.save(any(Category.class))).willReturn(categoryWithProduct);

        ProductDto productDto = productService.updateProduct(1L, 1L, new ProductDto());

        assertThat(productDto.getId(), equalTo(1L));


        then(categoryRepository).should().findOne(anyLong());
        then(categoryRepository).should().save(any(Category.class));

    }
    

    @Test
    public void deleteProductById() {
        Product product = new Product();
        product.setId(1L);

        Category categoryWithProduct = new Category();
        categoryWithProduct.setId(1L);
        categoryWithProduct.addProduct(product);

        given(categoryRepository.findOne(anyLong())).willReturn(categoryWithProduct);

        productService.deleteProductById(1L, 1L);

        then(categoryRepository).should().findOne(anyLong());
        then(categoryRepository).should().save(any(Category.class));

    }
}