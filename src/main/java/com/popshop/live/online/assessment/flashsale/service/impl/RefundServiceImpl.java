package com.popshop.live.online.assessment.flashsale.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popshop.live.online.assessment.flashsale.dao.RefundRepository;
import com.popshop.live.online.assessment.flashsale.model.Refund;
import com.popshop.live.online.assessment.flashsale.service.RefundService;

@Component
public class RefundServiceImpl implements RefundService {

	@Autowired
	private RefundRepository refundRepository;

	@Override
	public Refund createRefund(Refund refund) {
		Refund existingRefund = getByOrderId(refund.getId());
		if (existingRefund == null) {
			return refundRepository.save(refund);
		} else {
			return null;
		}

	}

	@Override
	public Refund getRefund(Long id) {
		Optional<Refund> refund = refundRepository.findById(id);
		if (refund.isPresent()) {
			return refund.get();
		} else {
			return null;
		}
	}

	@Override
	public Refund getByOrderId(Long orderId) {
		return refundRepository.getByOrderId(orderId);
	}

}
