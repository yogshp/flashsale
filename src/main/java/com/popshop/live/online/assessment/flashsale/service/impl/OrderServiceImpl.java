package com.popshop.live.online.assessment.flashsale.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popshop.live.online.assessment.flashsale.dao.OrderRepository;
import com.popshop.live.online.assessment.flashsale.enums.OrderStatus;
import com.popshop.live.online.assessment.flashsale.model.Order;
import com.popshop.live.online.assessment.flashsale.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order, Long id) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if (optionalOrder.isPresent()) {
			Order orderToUpdate = optionalOrder.get();
			orderToUpdate.setStatus(OrderStatus.CANCELED);
			return orderRepository.save(orderToUpdate);
		} else {
			return null;
		}
	}

	@Override
	public Order getOrder(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isPresent()) {
			return order.get();
		} else {
			return null;
		}
	}

	@Override
	public Order findByFlashSaleEventIdAndUserIdAndStatus(Long flashSaleEventId,Long userId,OrderStatus status) {
		return orderRepository.findByFlashSaleEventIdAndUserIdAndStatus(flashSaleEventId, userId,status);
	}

}
