package com.popshop.live.online.assessment.flashsale.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popshop.live.online.assessment.flashsale.enums.OrderStatus;
import com.popshop.live.online.assessment.flashsale.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	public Order findByFlashSaleEventIdAndUserIdAndStatus(Long flashSaleEventId, Long userId, OrderStatus status);
}
