package com.popshop.live.online.assessment.flashsale.service;

import org.springframework.stereotype.Service;

import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;

@Service
public interface FlashSaleItemService {
	public FlashSaleItem createFlashSaleItem(FlashSaleItem flashSaleItem);

	public FlashSaleItem getFlashSaleItem(Long id);

	public FlashSaleItem updateFlashSaleItem(FlashSaleItem flashSaleItem, Long id);

	public void deleteFlashSaleItem(Long id);
}
