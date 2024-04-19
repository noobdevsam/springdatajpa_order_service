package com.example.springdatajpa_order_service.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpa_order_service.domain.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
    Optional<Product> findByDescription(String description);
}
