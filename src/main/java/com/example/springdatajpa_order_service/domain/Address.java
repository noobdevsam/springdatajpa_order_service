package com.example.springdatajpa_order_service.domain;

import org.hibernate.validator.constraints.Length;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address {
    private String address;

    @Length(max = 30)
    private String city;
    private String state;
    private String zipCode;
}
