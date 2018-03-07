package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProductListByCategoryId(Long categoryId, Integer start, Integer end);
    List<ProductDto> getProductListByCategoryId(Long categoryId);
    Long countProductListByCategoryId(Long categoryId);
    Long getProductListSize();
    ProductDto getProductById(Long categoryId, Long productId);
    ProductDto createNewProduct(Long categoryId, ProductDto productDto);
    ProductDto updateProduct(Long categoryId, Long productId, ProductDto productDto);
    void deleteProductById(Long categoryId, Long productId);
}
