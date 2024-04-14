package com.example.springdatajpa_order_service.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpa_order_service.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
}
