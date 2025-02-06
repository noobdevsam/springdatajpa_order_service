package com.example.springdatajpa_order_service.domain;

import java.util.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
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

    @Version
    private Integer version;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billToAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private Set<OrderLine> orderLines;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    private OrderApproval orderApproval;

    public void setOrderApproval(OrderApproval orderApproval) {
        this.orderApproval = orderApproval;
        orderApproval.setOrderHeader(this);
    }

    public void addOrderLine(OrderLine orderLine) {
        if (orderLines == null) {
            orderLines = new HashSet<>();
        }

        orderLines.add(orderLine);
        orderLine.setOrderHeader(this);
    }

    // excluding 'orderlines' property from hashcode method
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
        result = prime * result + ((billToAddress == null) ? 0 : billToAddress.hashCode());
        result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderHeader other = (OrderHeader) obj;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (shippingAddress == null) {
            if (other.shippingAddress != null)
                return false;
        } else if (!shippingAddress.equals(other.shippingAddress))
            return false;
        if (billToAddress == null) {
            if (other.billToAddress != null)
                return false;
        } else if (!billToAddress.equals(other.billToAddress))
            return false;
        if (orderStatus != other.orderStatus)
            return false;
        if (orderLines == null) {
            if (other.orderLines != null)
                return false;
        } else if (!orderLines.equals(other.orderLines))
            return false;
        return true;
    }

    
}
