package com.hidarisoft.product_microservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
