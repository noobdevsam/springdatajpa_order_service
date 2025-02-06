package com.example.springdatajpa_order_service.repos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import com.example.springdatajpa_order_service.domain.Product;
import com.example.springdatajpa_order_service.domain.ProductStatus;
import com.example.springdatajpa_order_service.services.ProductService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = {ProductService.class})
public class ProductRepoTest {

    @Autowired
    ProductRepo repo;

    @Autowired
    ProductService productService;

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
        var product = repo.findByDescription("PRODUCT1").get();
        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void testAddAndUpdateProduct() {
        var product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        var savedProduct = productService.saveProduct(product);
        var savedProduct2 = productService.updateQOH(savedProduct.getId(), 25);

        System.out.println(savedProduct2.getQuantityOnHand());
    }
}
