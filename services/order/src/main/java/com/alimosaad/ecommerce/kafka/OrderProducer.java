package com.alimosaad.ecommerce.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    private final KafkaTemplate<String,OrderConfirmation> kafkaTemplate;
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
        log.info("sending order confirmation");
        // build a message -> payload -> content of a message -> ex:- orderRef and amount
        // setHeader -> specify the topic == "sending message to "order-topic" .build-> to build the message"
        // use this way if you need to add some headers like service-name or customerId or any another headers
        Message<OrderConfirmation> message= MessageBuilder.withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC,"order-topic").build();
        kafkaTemplate.send(message);
        // this is another way equivalent.
        //kafkaTemplate.send("order-topic",orderConfirmation);

    }
}
