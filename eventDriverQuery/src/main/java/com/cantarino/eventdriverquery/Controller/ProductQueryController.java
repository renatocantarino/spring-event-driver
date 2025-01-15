package com.cantarino.eventdriverquery.Controller;

import com.cantarino.eventdriverquery.Entity.Product;
import com.cantarino.eventdriverquery.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/query/products")
@RestController
public class ProductQueryController {

    @Autowired
    private ProductService queryService;

    @GetMapping
    public ResponseEntity<List<Product>> fetchAllProducts(){
        return ResponseEntity.ok(queryService.getProducts());
    }
}