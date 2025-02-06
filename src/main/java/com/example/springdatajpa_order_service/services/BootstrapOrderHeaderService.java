package com.example.springdatajpa_order_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdatajpa_order_service.repos.OrderHeaderRepo;

@Service
public class BootstrapOrderHeaderService {
    
    
    @Autowired
    OrderHeaderRepo oHeaderRepo;

    @Transactional
    public void readOrderData() {
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
     * Here, @Transactional makes every hibernate calls included in
     * the one hibernate session / transactional context
     */
    
}
