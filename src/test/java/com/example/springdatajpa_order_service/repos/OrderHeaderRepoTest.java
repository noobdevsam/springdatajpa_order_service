package com.example.springdatajpa_order_service.repos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.springdatajpa_order_service.domain.OrderHeader;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepoTest {

    @Autowired
    OrderHeaderRepo repo;

    @Test
    void testSaveOrder() {
        var orderheader = new OrderHeader();
        orderheader.setCustomer("New Customer");

        var savedOrder = repo.save(orderheader);
        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        var fetchedOrder = repo.getReferenceById(savedOrder.getId());
        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        assertNotNull(fetchedOrder.getCreatedDate());
    }
}
