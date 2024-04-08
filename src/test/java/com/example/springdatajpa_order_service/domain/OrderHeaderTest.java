package com.example.springdatajpa_order_service.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class OrderHeaderTest {
    @Test
    void testEquals() {
        OrderHeader oh1 = new OrderHeader();
        oh1.setId(1L);

        OrderHeader oh2 = new OrderHeader();
        oh2.setId(1L);

        assert oh1.equals(oh2);
    }

    @Test
    void testNotEquals() {
        OrderHeader oh1 = new OrderHeader();
        oh1.setId(1L);

        OrderHeader oh2 = new OrderHeader();
        oh2.setId(3L);

        assertFalse(oh1.equals(oh2));
    }
}
