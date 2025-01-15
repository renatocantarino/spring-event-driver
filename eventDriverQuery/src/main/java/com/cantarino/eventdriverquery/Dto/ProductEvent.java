package com.cantarino.eventdriverquery.Dto;

import com.cantarino.eventdriverquery.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {

    private String eventType;
    private Product product;
}