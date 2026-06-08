package com.alimosaad.ecommerce.repositories;

import com.alimosaad.ecommerce.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
