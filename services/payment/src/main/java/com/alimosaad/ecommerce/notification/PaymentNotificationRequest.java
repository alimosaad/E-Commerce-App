package com.alimosaad.ecommerce.notification;

import com.alimosaad.ecommerce.paymentMethod.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
