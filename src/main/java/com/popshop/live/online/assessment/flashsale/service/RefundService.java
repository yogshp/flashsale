package com.popshop.live.online.assessment.flashsale.service;

import org.springframework.stereotype.Service;

import com.popshop.live.online.assessment.flashsale.model.Refund;

@Service
public interface RefundService {

	public Refund createRefund(Refund refund);

	public Refund getRefund(Long id);
	
	public Refund getByOrderId(Long orderId);
}
