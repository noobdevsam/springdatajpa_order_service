package com.example.springdatajpa_order_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class OrderApproval extends BaseEntity{
    private String approvedBy;

    @OneToOne
    @JoinColumn(name = "order_header_id")
    private OrderHeader orderHeader;
}
