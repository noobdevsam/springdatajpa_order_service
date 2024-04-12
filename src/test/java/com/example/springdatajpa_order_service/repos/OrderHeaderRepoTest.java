package com.example.springdatajpa_order_service.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.springdatajpa_order_service.domain.OrderHeader;
import com.example.springdatajpa_order_service.domain.OrderLine;

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
        assertNotNull(fetchedOrder.getLastModifiedDate());
    }

    @Test
    void testSaveOrderWithLine() {
        var orderheader = new OrderHeader();
        orderheader.setCustomer("New Customer");

        var orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);

        orderheader.setOrderLines(Set.of(orderLine));
        orderLine.setOrderHeader(orderheader);

        var savedOrder = repo.save(orderheader);

        repo.flush(); // flushing cache to database

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(savedOrder.getOrderLines().size(), 1);

        var fetchedOrder = repo.getReferenceById(savedOrder.getId());
        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        // because of using cascade type 'persist', we don't need to invoke OrderLine repository to save a new OrderLine object related to OrderHeader object

    }
}
