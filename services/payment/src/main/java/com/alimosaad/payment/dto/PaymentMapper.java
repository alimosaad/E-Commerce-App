package com.alimosaad.payment.dto;

import com.alimosaad.payment.customer.Customer;
import com.alimosaad.payment.entities.Payment;
import com.alimosaad.payment.requests.PaymentRequest;
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
