package com.danielsolawa.storeauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_table")
@ToString(exclude = {"user"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;

    @ManyToOne
    @JsonIgnore
    private User user;


    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_table_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();


    public void addProduct(Product product){
        this.products.add(product);
    }




}
