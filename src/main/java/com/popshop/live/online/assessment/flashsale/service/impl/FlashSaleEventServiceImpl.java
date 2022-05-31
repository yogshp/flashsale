package com.popshop.live.online.assessment.flashsale.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popshop.live.online.assessment.flashsale.dao.FlashSaleEventRepository;
import com.popshop.live.online.assessment.flashsale.model.FlashSaleEvent;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleEventService;

@Component
public class FlashSaleEventServiceImpl implements FlashSaleEventService {

	@Autowired
	private FlashSaleEventRepository flashSaleEventRepository;
	
	@Override
	public FlashSaleEvent createFlashSaleEvent(FlashSaleEvent flashSaleEvent) {
		return flashSaleEventRepository.save(flashSaleEvent);
	}

	@Override
	public FlashSaleEvent getFlashSaleEvent(Long id) {
		Optional<FlashSaleEvent> flashSaleEvent = flashSaleEventRepository.findById(id);
		if (flashSaleEvent.isPresent()) {
			return flashSaleEvent.get();
		} else {
			return null;
		}
	}
}
