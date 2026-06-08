package com.alimosaad.ecommerce.services;

import com.alimosaad.ecommerce.dto.OrderLineMapper;
import com.alimosaad.ecommerce.repositories.OrderLineRepository;
import com.alimosaad.ecommerce.requests.OrderLineRequest;
import com.alimosaad.ecommerce.requests.OrderLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;
    public Integer saveOrderLines(OrderLineRequest orderLineRequest) {
        var order =orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        // todo findByOrderId logic
        return orderLineRepository.findAllByOrderId(orderId).stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
