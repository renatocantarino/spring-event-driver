package com.cantarino.eventdriver.dto;

import com.cantarino.eventdriver.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductCommand {

    private String name;
    private String description;
    private double price;

    public Product toEntity(){
        Product product = new Product();
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        return product;
    }

}
