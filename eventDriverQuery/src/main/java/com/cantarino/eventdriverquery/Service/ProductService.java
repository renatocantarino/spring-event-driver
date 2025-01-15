package com.cantarino.eventdriverquery.Service;


import com.cantarino.eventdriverquery.Dto.ProductEvent;
import com.cantarino.eventdriverquery.Entity.Product;
import com.cantarino.eventdriverquery.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() {
        return repository.findAll();
    }



    @KafkaListener(topics = "product-event-topic" , groupId = "product-event-group")
    public void Process(ProductEvent event){
        Product product = event.getProduct();

        processInsert(event, "CreateProduct", product);
        processUpdate(event, "UpdateProduct", product);
        processDelete(event, "DeleteProduct", product);

    }

    private void processInsert(ProductEvent event, String eventName, Product product) {
        if (event.getEventType().equals(eventName)) {
            repository.save(product);
        }
    }

    private void processUpdate(ProductEvent event, String eventName, Product product) {
        if (event.getEventType().equals(eventName)) {
            Product existingProduct = repository.findById(product.getId()).orElseThrow();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            repository.save(existingProduct);
        }
    }

    private void processDelete(ProductEvent event, String eventName, Product product) {
        if (event.getEventType().equals(eventName)) {
            repository.deleteById(product.getId());
        }
    }

}
