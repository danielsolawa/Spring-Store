package com.danielsolawa.storeauth.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "category")
    @JsonIgnore
    private List<Product> products = new ArrayList<>();


    public Category addProduct(Product product){
        product.setCategory(this);
        this.products.add(product);

        return this;
    }
}
