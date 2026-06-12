package com.alimosaad.ecommerce.services;

import com.alimosaad.ecommerce.customer.CustomerClient;
import com.alimosaad.ecommerce.dto.OrderMapper;
import com.alimosaad.ecommerce.exceptions.BusinessException;
import com.alimosaad.ecommerce.kafka.OrderConfirmation;
import com.alimosaad.ecommerce.kafka.OrderProducer;
import com.alimosaad.ecommerce.payment.PaymentClient;
import com.alimosaad.ecommerce.payment.PaymentRequest;
import com.alimosaad.ecommerce.product.ProductClient;
import com.alimosaad.ecommerce.product.PurchaseRequest;
import com.alimosaad.ecommerce.repositories.OrderRepository;
import com.alimosaad.ecommerce.requests.OrderLineRequest;
import com.alimosaad.ecommerce.requests.OrderRequest;
import com.alimosaad.ecommerce.requests.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    /// create customerClient
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public Integer createOrder(OrderRequest request) {
        /// 1. check we have our customer or not using --> Openfeign --> create customerClient
        var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(
                ()->new BusinessException("cannot create order:: No Customer exist")
        );
        /// 2. purchase the product --> product microservice (using RestTemplate) to apply different ways of communications
        var purchasedProduct=this.productClient.purchaseProduct(request.products());
        /// 3. persist the order(شراء)
        var order=this.orderRepository.save(orderMapper.toOrder(request));
        /// 4. persist the order lines
        for (PurchaseRequest purchaseRequest:request.products()){
            orderLineService.saveOrderLines(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        //  todo 5. start payment process
        var paymentRequest=new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer

        );
        paymentClient.requestOrderPayment(paymentRequest);
        /// 6. send the order confirmation --> notification microservice (kafka)
        log.info("Customer before kafka = {}", customer);
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProduct
                )
        );

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId).map(orderMapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException(String.format("No order Found with the provided ID: %d",orderId)));
    }
}
