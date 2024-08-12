package com.example.springdatajpa_order_service.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdatajpa_order_service.repos.OrderHeaderRepo;

@Component
public class Bootstrap implements CommandLineRunner{

    @Autowired
    OrderHeaderRepo oHeaderRepo;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------------------");
        System.out.println("Demonstrating lazy initialization error due to out of hibernate context");

        var orderHeader = oHeaderRepo.findById(1L).get();
        orderHeader.getOrderLines().forEach(ol -> {
            System.out.println(ol.getProduct().getDescription());

            // this call happens out of transactional context for OrderHeaderRepo
            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println(cat.getDescription());
            });
        });
    }

    /*
     * If we comment out the @Transactional annotation, we will get
     *      org.hibernate.LazyInitializationException: failed to lazily initialize a collection of
     *           role: com.example.springdatajpa_order_service.domain.Product.categories: could not initialize proxy - no Session
     */
    
}
