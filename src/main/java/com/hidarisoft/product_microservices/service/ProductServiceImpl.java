package com.hidarisoft.product_microservices.service;

import com.hidarisoft.product_microservices.model.Product;
import com.hidarisoft.product_microservices.model.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public String createProduct(Product product) throws ExecutionException, InterruptedException {
        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId, product.getTitle(), product.getPrice(), product.getQuantity());

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent).get();

        logger.info("Partition: {}", result.getRecordMetadata().partition());
        logger.info("Topic: {}", result.getRecordMetadata().topic());
        logger.info("Offset: {}", result.getRecordMetadata().offset());

        logger.info("Returning product id: {}", productId);
        return productId;
    }

}
