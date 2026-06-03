package com.alimosaad.order.kafka;

import com.alimosaad.order.customer.CustomerResponse;
import com.alimosaad.order.payment.PaymentMethod;
import com.alimosaad.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
)
{
}
