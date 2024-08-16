package com.example.springdatajpa_order_service.services;

import com.example.springdatajpa_order_service.domain.Product;

public interface ProductService {
    
    Product saveProduct(Product product);

    Product updateQOH(Long id, Integer quantityOnHand);
    
}
