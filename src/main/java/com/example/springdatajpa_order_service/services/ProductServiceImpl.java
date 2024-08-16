package com.example.springdatajpa_order_service.services;

import org.springframework.stereotype.Service;

import com.example.springdatajpa_order_service.domain.Product;
import com.example.springdatajpa_order_service.repos.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepo.saveAndFlush(product);
    }

    @Override
    public Product updateQOH(Long id, Integer quantityOnHand) {
        var product = productRepo.findById(id).orElseThrow();
        product.setQuantityOnHand(quantityOnHand);
        return productRepo.saveAndFlush(product);
    }
    
}
