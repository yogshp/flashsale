package com.popshop.live.online.assessment.flashsale.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popshop.live.online.assessment.flashsale.model.Refund;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Long> {
	public Refund getByOrderId(Long orderId);
}
