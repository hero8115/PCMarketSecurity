package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {


    private String name;

    private String model;

    private String description;

    private Double price;

    private Integer category_id;
}
