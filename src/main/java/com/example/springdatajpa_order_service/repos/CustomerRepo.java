package com.example.springdatajpa_order_service.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpa_order_service.domain.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long>{
    Optional<Customer> findCustomerByCustomerNameIgnoreCase(String customerName);
}
