package com.example.springdatajpa_order_service.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@AttributeOverrides({
    @AttributeOverride(
        name = "shippingAddress.address", column = @Column(name = "shipping_address")
    ),
    @AttributeOverride(
        name = "shippingAddress.city", column = @Column(name = "shipping_city")
    ),
    @AttributeOverride(
        name = "shippingAddress.state", column = @Column(name = "shipping_state")
    ),
    @AttributeOverride(
        name = "shippingAddress.zipCode", column = @Column(name = "shipping_zip_code")
    ),
    @AttributeOverride(
        name = "billToAddress.address", column = @Column(name = "bill_to_address")
    ),
    @AttributeOverride(
        name = "billToAddress.city", column = @Column(name = "bill_to_city")
    ),
    @AttributeOverride(
        name = "billToAddress.state", column = @Column(name = "bill_to_state")
    ),
    @AttributeOverride(
        name = "billToAddress.zipCode", column = @Column(name = "bill_to_zip_code")
    )
})
public class OrderHeader extends BaseEntity {
    private String customer;

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billToAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
