package com.cantarino.eventdriver.service;

import com.cantarino.eventdriver.dto.ProductEvent;
import com.cantarino.eventdriver.entity.Product;
import com.cantarino.eventdriver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCommandService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Product Create(Product product) {
        Product prod = productRepository.save(product);

        kafkaTemplate.send("product-event-topic", new ProductEvent("ProductCreated", prod));

        return prod;
    }

    public Product Update(Long id, Product product) {

        Product entity = productRepository.findById(id)
                                          .orElseThrow(()-> new RuntimeException("Product not found"));

        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setDescription(product.getDescription());
        Product edited = productRepository.save(entity);

        kafkaTemplate.send("product-event-topic", new ProductEvent("ProductCreated", edited));

        return edited;
    }

    public void Delete(long id) {
        productRepository.deleteById(id);
    }
}
