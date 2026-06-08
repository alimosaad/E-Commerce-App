package com.alimosaad.ecommerce.dto;

import com.alimosaad.ecommerce.entities.Order;
import com.alimosaad.ecommerce.entities.OrderLine;
import com.alimosaad.ecommerce.requests.OrderLineRequest;
import com.alimosaad.ecommerce.requests.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.orderId())
                                .build()
                )
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
