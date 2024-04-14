package com.example.springdatajpa_order_service.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class OrderApproval extends BaseEntity{
    private String approvedBy;
}
