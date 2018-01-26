package com.danielsolawa.storeauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order")
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        product.setOrder(this);
        this.products.add(product);
    }

}
