package com.hidarisoft.product_microservices.service;

import com.hidarisoft.product_microservices.model.Product;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(Product product) throws ExecutionException, InterruptedException;
}
