package com.example.springdatajpa_order_service;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.springdatajpa_order_service.domain.Address;
import com.example.springdatajpa_order_service.domain.Customer;
import com.example.springdatajpa_order_service.domain.OrderHeader;
import com.example.springdatajpa_order_service.domain.OrderLine;
import com.example.springdatajpa_order_service.domain.Product;
import com.example.springdatajpa_order_service.domain.ProductStatus;
import com.example.springdatajpa_order_service.repos.CustomerRepo;
import com.example.springdatajpa_order_service.repos.OrderHeaderRepo;
import com.example.springdatajpa_order_service.repos.ProductRepo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {
    final String PRODUCT_01 = "Product 1";
    final String PRODUCT_02 = "Product 2";
    final String PRODUCT_03 = "Product 3";
    final String TEST_CUSTOMER = "TEST CUSTOMER";

    @Autowired
    OrderHeaderRepo orderHeaderRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ProductRepo productRepo;

    @Disabled
    @Rollback(value = false)
    @Test
    void testDataLoader() {
        List<Product> products = loadProducts();
        Customer customer = loadCustomers();

        int ordersToCreate = 100;

        for (int i = 0; i < ordersToCreate; i++) {
            System.out.println("Creating order #: " + i);
            saveOrder(customer, products);
        }
        orderHeaderRepo.flush();
    }

    @Test
    void testLazyDataLoad() {
        var orderheader = orderHeaderRepo.getReferenceById(13l);
        System.out.println("Order Id: " + orderheader.getId());
        System.out.println("Customer name: " + orderheader.getCustomer().getCustomerName());
    }

    @Test
    void test_hibernate_n_plus_problem() {
        Customer cs = customerRepo.findCustomerByCustomerNameIgnoreCase(TEST_CUSTOMER).get();

        IntSummaryStatistics totalOrdereed = orderHeaderRepo.findAllByCustomer(cs).stream()
        .flatMap(orderheader -> orderheader.getOrderLines().stream())
        .collect(Collectors.summarizingInt(ol -> ol.getQuantityOrdered()));

        System.out.println("Total ordered: " + totalOrdereed.getSum());
    }

    private OrderHeader saveOrder(Customer customer, List<Product> products) {
        Random random = new Random();

        OrderHeader oHeader = new OrderHeader();
        oHeader.setCustomer(customer);

        products.forEach(product -> {
            OrderLine oLine = new OrderLine();
            oLine.setProduct(product);
            oLine.setQuantityOrdered(random.nextInt(20));

            oHeader.addOrderLine(oLine);
        });

        return orderHeaderRepo.save(oHeader);
    }

    private Customer loadCustomers() {
        return getOrSaveCustomer(TEST_CUSTOMER);
    }

    private Customer getOrSaveCustomer(String customerName) {
        return customerRepo.findCustomerByCustomerNameIgnoreCase(customerName)
            .orElseGet(() -> {
                Customer c1 = new Customer();
                c1.setCustomerName(customerName);
                c1.setEmail("test@example.com");

                Address address = new Address();
                address.setAddress("123 Main street");
                address.setCity("New City");
                address.setState("Nw state");

                c1.setAddress(address);

                return customerRepo.save(c1);
            });
    }

    private List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();

        products.add(getOrSaveProduct(PRODUCT_01));
        products.add(getOrSaveProduct(PRODUCT_02));
        products.add(getOrSaveProduct(PRODUCT_03));

        return products;
    }

    private Product getOrSaveProduct(String description) {
        return productRepo.findByDescription(description)
            .orElseGet(() -> {
                Product p1 = new Product();
                p1.setDescription(description);
                p1.setProductStatus(ProductStatus.NEW);

                return productRepo.save(p1);
            });
    }
}
