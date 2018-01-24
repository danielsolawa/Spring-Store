package com.danielsolawa.storeauth.dtos;

import com.danielsolawa.storeauth.domain.Product;
import lombok.Data;

@Data
public class CategoryDto {

    private Long id;
    private String name;
    private Product product;
}
