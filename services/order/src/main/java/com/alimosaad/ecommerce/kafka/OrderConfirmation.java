package com.alimosaad.ecommerce.kafka;

import com.alimosaad.ecommerce.customer.CustomerResponse;
import com.alimosaad.ecommerce.payment.PaymentMethod;
import com.alimosaad.ecommerce.product.PurchaseResponse;

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
