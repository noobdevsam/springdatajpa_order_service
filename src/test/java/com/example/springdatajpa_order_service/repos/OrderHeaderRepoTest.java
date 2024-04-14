package com.example.springdatajpa_order_service.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import com.example.springdatajpa_order_service.domain.Customer;
import com.example.springdatajpa_order_service.domain.OrderApproval;
import com.example.springdatajpa_order_service.domain.OrderHeader;
import com.example.springdatajpa_order_service.domain.OrderLine;
import com.example.springdatajpa_order_service.domain.Product;
import com.example.springdatajpa_order_service.domain.ProductStatus;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepoTest {

    @Autowired
    OrderHeaderRepo repo;

    @Autowired
    ProductRepo prepo;

    @Autowired
    CustomerRepo curepo;

    Product product;

    @BeforeEach
    void setUp() {
        var newProduct = new Product();
        newProduct.setProductStatus(ProductStatus.NEW);
        newProduct.setDescription("test product");
        product = prepo.saveAndFlush(newProduct);
    }

    @Test
    void testSaveOrder() {
        var orderheader = new OrderHeader();
        // orderheader.setCustomer("New Customer");

        var customer = new Customer();
        customer.setCustomerName("N customer");
        var savedCustomer = curepo.save(customer);

        orderheader.setCustomer(savedCustomer);

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
        // orderheader.setCustomer("New Customer");

        var customer = new Customer();
        customer.setCustomerName("N customer");
        var savedCustomer = curepo.save(customer);

        orderheader.setCustomer(savedCustomer);

        var orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

        // orderheader.setOrderLines(Set.of(orderLine));
        // orderLine.setOrderHeader(orderheader);
        orderheader.addOrderLine(orderLine);

        var approval = new OrderApproval();
        approval.setApprovedBy("Me");
        orderheader.setOrderApproval(approval);

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

    @Test
    void testDeleteCascade() {
        var orderHeader = new OrderHeader();
        var customer = new Customer();
        var orderLine = new OrderLine();

        customer.setCustomerName("New new customer");
        orderHeader.setCustomer(curepo.save(customer));
        orderLine.setQuantityOrdered(3);
        orderLine.setProduct(product);
        orderHeader.addOrderLine(orderLine);

        var savedOrder = repo.saveAndFlush(orderHeader);

        System.out.println("Order saved and flushed");

        repo.deleteById(savedOrder.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            var fetchedOrder = repo.getReferenceById(savedOrder.getId());
        });

    }
}
