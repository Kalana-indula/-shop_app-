package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private Integer qty;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders=new HashSet<>();
}
