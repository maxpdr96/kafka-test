package com.hidarisoft.product_microservices.controller;

import com.hidarisoft.product_microservices.model.ErrorMessage;
import com.hidarisoft.product_microservices.model.Product;
import com.hidarisoft.product_microservices.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        try {
            String productId = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully: " + productId);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), ex.getMessage(), "/products"));
        }
    }
}
