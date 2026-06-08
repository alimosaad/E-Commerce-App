package com.alimosaad.payment.services;

import com.alimosaad.payment.dto.PaymentMapper;
import com.alimosaad.payment.notification.NotificationProducer;
import com.alimosaad.payment.notification.PaymentNotificationRequest;
import com.alimosaad.payment.repository.PaymentRepository;
import com.alimosaad.payment.requests.PaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
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
