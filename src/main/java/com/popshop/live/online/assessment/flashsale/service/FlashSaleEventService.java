package com.popshop.live.online.assessment.flashsale.service;

import org.springframework.stereotype.Service;

import com.popshop.live.online.assessment.flashsale.model.FlashSaleEvent;

@Service
public interface FlashSaleEventService {

	public FlashSaleEvent createFlashSaleEvent(FlashSaleEvent flashSaleEvent);

	public FlashSaleEvent getFlashSaleEvent(Long id);
}
