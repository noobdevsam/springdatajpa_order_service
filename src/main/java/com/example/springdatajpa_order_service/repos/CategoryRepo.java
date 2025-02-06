package com.example.springdatajpa_order_service.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpa_order_service.domain.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
