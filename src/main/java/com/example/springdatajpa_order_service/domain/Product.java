package com.example.springdatajpa_order_service.domain;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = "categories")
@Entity
public class Product extends BaseEntity{

    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private Integer quantityOnHand = 0;

    @ManyToMany
    @JoinTable(
        name = "product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;
}
