package com.alimosaad.order.services;

import com.alimosaad.order.dto.OrderLineMapper;
import com.alimosaad.order.repositories.OrderLineRepository;
import com.alimosaad.order.requests.OrderLineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    public Integer saveOrderLines(OrderLineRequest orderLineRequest) {
        var order =orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }
}
