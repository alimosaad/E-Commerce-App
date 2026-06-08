package com.alimosaad.ecommerce.payment;

import com.alimosaad.ecommerce.customer.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
