package com.example.springdatajpa_order_service.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String address;
    private String city;
    private String state;
    private String zipCode;
}
