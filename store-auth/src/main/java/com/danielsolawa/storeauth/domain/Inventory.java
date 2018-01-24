package com.danielsolawa.storeauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "inventory")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "inventory")
    private List<Product> products = new ArrayList<>();
}
