package com.example.springdatajpa_order_service.repos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.lang.NonNull;

import com.example.springdatajpa_order_service.domain.Product;
import jakarta.persistence.LockModeType;

public interface ProductRepo extends JpaRepository<Product, Long>{

    Optional<Product> findByDescription(String description);

    @NonNull
    @Override
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<Product> findById(@NonNull Long id);

}
