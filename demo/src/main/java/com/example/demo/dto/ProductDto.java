package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {
    private String name;
    private Integer qty;
    private Double price;
    private Long categoryId;
}
