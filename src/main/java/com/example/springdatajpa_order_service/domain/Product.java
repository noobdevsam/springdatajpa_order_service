package com.example.springdatajpa_order_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Product extends BaseEntity{
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
}
