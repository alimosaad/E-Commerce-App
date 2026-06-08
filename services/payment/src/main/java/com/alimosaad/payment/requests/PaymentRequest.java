package com.alimosaad.payment.requests;

import com.alimosaad.payment.customer.Customer;
import com.alimosaad.payment.paymentMethod.PaymentMethod;

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
