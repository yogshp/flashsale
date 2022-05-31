package com.popshop.live.online.assessment.flashsale.service;

import org.springframework.stereotype.Service;

import com.popshop.live.online.assessment.flashsale.enums.OrderStatus;
import com.popshop.live.online.assessment.flashsale.model.Order;

@Service
public interface OrderService {
 public Order createOrder(Order order);
 public Order getOrder(Long id);
 public Order updateOrder(Order order, Long id);
 public Order findByFlashSaleEventIdAndUserIdAndStatus(Long flashSaleEventId,Long userId, OrderStatus status);
}
