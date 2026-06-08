package com.alimosaad.ecommerce.services;

import com.alimosaad.ecommerce.dto.PaymentMapper;
import com.alimosaad.ecommerce.notification.NotificationProducer;
import com.alimosaad.ecommerce.notification.PaymentNotificationRequest;
import com.alimosaad.ecommerce.repository.PaymentRepository;
import com.alimosaad.ecommerce.requests.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;
    public Integer CreatePayment(PaymentRequest paymentRequest) {
        var payment=paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        // todo payment and send notification using kafka
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.orderReference(),
                        paymentRequest.amount(),
                        paymentRequest.paymentMethod(),
                        paymentRequest.customer().firstName(),
                        paymentRequest.customer().lastName(),
                        paymentRequest.customer().email()
                )
        );
        return payment.getId();
    }
}
