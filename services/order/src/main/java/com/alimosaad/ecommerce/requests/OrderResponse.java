package com.alimosaad.ecommerce.requests;

import com.alimosaad.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId

) {
}
