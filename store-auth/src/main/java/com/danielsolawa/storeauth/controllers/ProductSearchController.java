package com.danielsolawa.storeauth.controllers;

import com.danielsolawa.storeauth.dtos.ProductListDto;
import com.danielsolawa.storeauth.services.ProductSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProductSearchController.BASE_URL)
public class ProductSearchController {

    public static final String BASE_URL = "/api/v1/product-search";

    private final ProductSearchService productSearchService;

    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }


    @GetMapping("/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public ProductListDto searchForProducts(@PathVariable("keyword") String keyword){

        return new ProductListDto(productSearchService.searchForProductByKeyword(keyword));
    }
}
