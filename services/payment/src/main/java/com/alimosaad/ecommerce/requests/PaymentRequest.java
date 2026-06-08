package com.alimosaad.ecommerce.requests;

import com.alimosaad.ecommerce.customer.Customer;
import com.alimosaad.ecommerce.paymentMethod.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
