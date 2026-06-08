package com.alimosaad.ecommerce.dto;

import com.alimosaad.ecommerce.entities.Payment;
import com.alimosaad.ecommerce.requests.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest) {
        // todo toPayment method
        return Payment
                .builder()
                .id(paymentRequest.id())
                .orderId(paymentRequest.orderId())
                .amount(paymentRequest.amount())
                .paymentMethod(paymentRequest.paymentMethod())
                .build();
    }
}
