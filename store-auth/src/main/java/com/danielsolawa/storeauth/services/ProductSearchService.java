package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.ProductDto;

import java.util.List;

public interface ProductSearchService {

    List<ProductDto> searchForProductByKeyword(String keyword);
}
