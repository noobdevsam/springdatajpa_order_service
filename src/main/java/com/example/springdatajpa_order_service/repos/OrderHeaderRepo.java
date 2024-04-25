package com.example.springdatajpa_order_service.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdatajpa_order_service.domain.Customer;
import com.example.springdatajpa_order_service.domain.OrderHeader;

public interface OrderHeaderRepo extends JpaRepository<OrderHeader, Long>{
    List<OrderHeader> findAllByCustomer(Customer customer);
}
