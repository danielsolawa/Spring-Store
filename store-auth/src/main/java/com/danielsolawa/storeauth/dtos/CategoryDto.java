package com.danielsolawa.storeauth.dtos;

import com.danielsolawa.storeauth.domain.Product;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
public class CategoryDto {

    private Long id;

    @NotEmpty(message = "This field can't be empty.")
    private String name;
    private List<Product> products;
}
