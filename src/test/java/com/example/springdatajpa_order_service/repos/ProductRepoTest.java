package com.example.springdatajpa_order_service.repos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.springdatajpa_order_service.domain.Product;
import com.example.springdatajpa_order_service.domain.ProductStatus;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepoTest {

    @Autowired
    ProductRepo repo;

    @Test
    void testSaveProduct() {
        var product = new Product();
        product.setDescription("My product");
        product.setProductStatus(ProductStatus.NEW);

        var savedProduct = repo.save(product);
        var fetchedProduct = repo.getReferenceById(savedProduct.getId());

        assertNotNull(fetchedProduct);
        assertNotNull(fetchedProduct.getDescription());
        assertNotNull(fetchedProduct.getProductStatus());
        assertNotNull(fetchedProduct.getCreatedDate());
        assertNotNull(fetchedProduct.getLastModifiedDate());
    }

    @Test
    void testGetCategories() {
        var product = repo.findByDescription("PRODUCT1");
        assertNotNull(product);
        assertNotNull(product.getCategories());
    }
}
