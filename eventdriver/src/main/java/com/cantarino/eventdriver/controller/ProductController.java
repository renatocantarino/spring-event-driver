package com.cantarino.eventdriver.controller;


import com.cantarino.eventdriver.dto.CreateProductCommand;
import com.cantarino.eventdriver.entity.Product;
import com.cantarino.eventdriver.service.ProductCommandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductCommandService _productCommandService;

    @PostMapping
    public ResponseEntity<Product> Create(@Valid @RequestBody CreateProductCommand product) {
        return ResponseEntity.ok(_productCommandService.Create(product.toEntity()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> Update(@PathVariable long id,@RequestBody Product product) {
        return ResponseEntity.ok(_productCommandService.Update(id,product));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Update(@PathVariable long id) {
        _productCommandService.Delete(id);
        return ResponseEntity.noContent().build();
    }
}
