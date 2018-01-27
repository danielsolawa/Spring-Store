package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.mappers.ProductMapper;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

   private final CategoryRepository categoryRepository;
   private final ProductMapper productMapper;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductDto> getProductList(Long categoryId) {
        Category category = getCategoryById(categoryId);

        return  category.getProducts()
                .stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long categoryId, Long productId) {
        Category category = getCategoryById(categoryId);

        return category.getProducts()
                .stream()
                .map(productMapper::productToProductDto)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    @Override
    public ProductDto createNewProduct(Long categoryId, ProductDto productDto) {

        return saveProductDto(categoryId, productDto);
    }

    private ProductDto saveProductDto(Long categoryId, ProductDto productDto) {
        Category category = getCategoryById(categoryId);
        category.addProduct(productMapper.productDtoToProduct(productDto));

        Category savedCategory = categoryRepository.save(category);
        Product product = savedCategory.getProducts()
                .stream()
                .max(Comparator.comparing(Product::getId))
                .orElseThrow(NoSuchElementException::new);


        return productMapper.productToProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long categoryId, Long productId, ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProductById(Long categoryId, Long productId) {

    }


    private Category getCategoryById(Long id){
        Category category = categoryRepository.findOne(id);

        if(category == null){
            //todo
            throw new RuntimeException();
        }

        return category;
    }
}
