package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.mappers.ProductMapper;
import com.danielsolawa.storeauth.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductSearchServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> searchForProductByKeyword(String keyword) {
        return productRepository.searchForProducts(keyword, keyword, keyword)
                .stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long countSearchForProductByKeyword(String keyword) {
        return productRepository.countSearchForProducts(keyword, keyword, keyword);
    }

    @Override
    public List<ProductDto> searchForProductByKeyword(String keyword, Integer page, Integer size) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(page, size, sort);

        return productRepository.searchForProducts(keyword, keyword, keyword, pageable)
                .stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }
}
