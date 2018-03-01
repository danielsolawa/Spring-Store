package com.danielsolawa.storeauth.controllers;


import com.danielsolawa.storeauth.dtos.ProductDto;
import com.danielsolawa.storeauth.dtos.ProductListDto;
import com.danielsolawa.storeauth.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

    public static final String BASE_URL = "/api/v1/categories";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{categoryId}/products")
    @ResponseStatus(HttpStatus.OK)
    public ProductListDto getProductList(@PathVariable  Long categoryId){
        return new ProductListDto(productService.getProductList(categoryId));
    }


    @GetMapping("/{categoryId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProductById(@PathVariable Long categoryId, @PathVariable Long productId){
        return productService.getProductById(categoryId, productId);
    }

    @PostMapping("/{categoryId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProduct(@PathVariable Long categoryId, @RequestBody ProductDto productDto){
        return productService.createNewProduct(categoryId, productDto);
    }

    @PutMapping("/{categoryId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@PathVariable  Long categoryId, @PathVariable Long productId,
                                    @RequestBody ProductDto productDto){
        return productService.updateProduct(categoryId, productId, productDto);
    }


    @DeleteMapping("/{categoryId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable  Long categoryId, @PathVariable Long productId){

        productService.deleteProductById(categoryId, productId);
    }
}
