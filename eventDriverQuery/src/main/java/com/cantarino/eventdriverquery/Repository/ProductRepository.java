package com.cantarino.eventdriverquery.Repository;

import com.cantarino.eventdriverquery.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}