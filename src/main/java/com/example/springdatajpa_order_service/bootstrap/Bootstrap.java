package com.example.springdatajpa_order_service.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.springdatajpa_order_service.services.BootstrapOrderHeaderService;

@Component
public class Bootstrap implements CommandLineRunner{

    @Autowired
    BootstrapOrderHeaderService service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------------------");

        // this is an external method call using proxy mode
        service.readOrderData();

        /*
         * Transactional Proxy Mode
         *      Here, run() is a external method but readOrderData() is
         *      a internal method. We need a proxy to call internal method
         *      inside the external method.
         */
    }
    
}
