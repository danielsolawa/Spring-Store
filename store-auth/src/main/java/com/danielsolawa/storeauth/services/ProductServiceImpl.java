package com.danielsolawa.storeauth.services;

import com.danielsolawa.storeauth.domain.Category;
import com.danielsolawa.storeauth.domain.Product;
import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.exceptions.ResourceNotFoundException;
import com.danielsolawa.storeauth.mappers.ProductMapper;
import com.danielsolawa.storeauth.repositories.CategoryRepository;
import com.danielsolawa.storeauth.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
   private final ProductRepository productRepository;
   private final ProductMapper productMapper;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository,
                              ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductDto> getProductListByCategoryId(Long categoryId, Integer start, Integer end) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
        Pageable pageable = new PageRequest(start, end, sort);

        return  productRepository.findByCategoryId(categoryId, pageable)
                .stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductListByCategoryId(Long categoryId) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));

        return productRepository.findByCategoryId(categoryId, sort)
                .stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long countProductListByCategoryId(Long categoryId) {
        return productRepository.countByCategoryId(categoryId);
    }

    @Override
    public Long getProductListSize() {
        return productRepository.count();
    }


    @Override
    public ProductDto getProductById(Long categoryId, Long productId) {
        Category category = getCategoryById(categoryId);

        return category.getProducts()
                .stream()
                .filter(product -> product.getId().equals(productId))
                .map(productMapper::productToProductDto)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    @Override
    public ProductDto createNewProduct(Long categoryId, ProductDto productDto) {
        Category category = getCategoryById(categoryId);
        category.addProduct(productMapper.productDtoToProduct(productDto));

        return saveProductDto(category);
    }


    @Transactional
    @Override
    public ProductDto updateProduct(Long categoryId, Long productId, ProductDto productDto) {

        productDto.setId(productId);
        Category category = prepareForUpdate(productId, productDto, getCategoryById(categoryId));

        return saveProductDto(category);
    }



    @Transactional
    @Override
    public void deleteProductById(Long categoryId, Long productId) {
        Category category = getCategoryById(categoryId);

        Category updatedCategory = removeProduct(productId, category);

        categoryRepository.save(updatedCategory);



    }



    private Category removeProduct(Long productId, Category category) {
        Product foundProduct = category.getProducts()
                .stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst().orElseThrow(NoSuchElementException::new);

        log.info(foundProduct.toString());

        foundProduct.setCategory(null);

        category.getProducts().remove(foundProduct);


        return category;
    }


    private Category prepareForUpdate(Long productId, ProductDto productDto, Category category) {

        log.info(category.getProducts().toString());


        Product foundProduct= category.getProducts()
                .stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst().orElseThrow(NoSuchElementException::new);

        category.getProducts().remove(foundProduct);

        if(productDto.getName() != null){
            foundProduct.setName(productDto.getName());
        }

        if(productDto.getPrice() != null){
            foundProduct.setPrice(productDto.getPrice());
        }

        if(productDto.getDescription() != null){
            foundProduct.setDescription(productDto.getDescription());
        }

        category.addProduct(foundProduct);

        return category;
    }


    private ProductDto saveProductDto(Category category) {

        Category savedCategory = categoryRepository.save(category);
        Product product = savedCategory.getProducts()
                .stream()
                .max(Comparator.comparing(Product::getId))
                .orElseThrow(NoSuchElementException::new);


        return productMapper.productToProductDto(product);
    }


    private Category getCategoryById(Long id){
        Category category = categoryRepository.findOne(id);

        if(category == null){
            throw new ResourceNotFoundException();
        }

        return category;
    }
}
