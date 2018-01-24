package com.danielsolawa.storeauth.dtos;

import com.danielsolawa.storeauth.domain.Product;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;
    private String name;
    private List<Product> products;
}
