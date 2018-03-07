package com.danielsolawa.storeauth.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductListDto {

    List<ProductDto> products;
    Long amount;

    public ProductListDto(List<ProductDto> products) {
        this.products = products;
    }

    public ProductListDto(List<ProductDto> products, Long amount) {
        this.products = products;
        this.amount = amount;
    }
}
