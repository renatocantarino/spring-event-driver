package com.cantarino.eventdriver.repository;

import com.cantarino.eventdriver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
