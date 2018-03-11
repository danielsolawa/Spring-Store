package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.ProductDto;

import java.util.List;

public interface ProductSearchService {

    List<ProductDto> searchForProductByKeyword(String keyword);
    Long countSearchForProductByKeyword(String keyword);
    List<ProductDto> searchForProductByKeyword(String keyword, Integer page, Integer size);
}
