package com.alimosaad.order.services;

import com.alimosaad.order.customer.CustomerClient;
import com.alimosaad.order.dto.OrderMapper;
import com.alimosaad.order.exceptions.BusinessException;
import com.alimosaad.order.product.ProductClient;
import com.alimosaad.order.product.PurchaseRequest;
import com.alimosaad.order.repositories.OrderRepository;
import com.alimosaad.order.requests.OrderLineRequest;
import com.alimosaad.order.requests.OrderRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    /// create customerClient
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    public Integer createOrder(OrderRequest request) {
        /// 1. check we have our customer or not using --> Openfeign --> create customerClient
        var customer = customerClient.findCustomerById(request.customerId()).orElseThrow(
                ()->new BusinessException("cannot create order:: No Customer exist")
        );
        /// 2. purchase the product --> product microservice (using RestTemplate) to apply different ways of communications
        this.productClient.purchaseProduct(request.products());
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

        /// 6. send the order confirmation --> notification microservice (kafka)

        return null;
    }
}
