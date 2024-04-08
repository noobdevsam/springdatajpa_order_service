package com.example.springdatajpa_order_service.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springdatajpa_order_service.domain.OrderHeader;

public interface OrderHeaderRepo extends JpaRepository<OrderHeader, Long>{
    
}
