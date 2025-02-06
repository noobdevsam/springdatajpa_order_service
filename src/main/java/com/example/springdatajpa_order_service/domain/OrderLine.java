package com.example.springdatajpa_order_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class OrderLine extends BaseEntity{

    @Version
    private Integer version;

    private Integer quantityOrdered;

    @ManyToOne
    private OrderHeader orderHeader;

    @ManyToOne
    private Product product;

    // excluding 'orderHeader' property from hashcode method
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result = prime * result + ((quantityOrdered == null) ? 0 : quantityOrdered.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
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
        OrderLine other = (OrderLine) obj;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        if (quantityOrdered == null) {
            if (other.quantityOrdered != null)
                return false;
        } else if (!quantityOrdered.equals(other.quantityOrdered))
            return false;
        if (orderHeader == null) {
            if (other.orderHeader != null)
                return false;
        } else if (!orderHeader.equals(other.orderHeader))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }

}
