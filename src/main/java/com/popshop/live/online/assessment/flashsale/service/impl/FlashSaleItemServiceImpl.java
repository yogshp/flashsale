package com.popshop.live.online.assessment.flashsale.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popshop.live.online.assessment.flashsale.dao.FlashSaleItemRepository;
import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleItemService;

@Component
public class FlashSaleItemServiceImpl implements FlashSaleItemService {

	@Autowired
	private FlashSaleItemRepository flashSaleItemRepository;
	
	@Override
	public FlashSaleItem createFlashSaleItem(FlashSaleItem flashSaleItem) {
		FlashSaleItem itemExists = flashSaleItemRepository.findByName(flashSaleItem.getName());
		if(itemExists != null) {
			return null;
		}
		else {
			return flashSaleItemRepository.save(flashSaleItem);
		}
	}

	@Override
	public FlashSaleItem getFlashSaleItem(Long id) {
		Optional<FlashSaleItem> flashSaleItem = flashSaleItemRepository.findById(id);
		if (flashSaleItem.isPresent()) {
			return flashSaleItem.get();
		} else {
			return null;
		}
	}

	@Override
	public FlashSaleItem updateFlashSaleItem(FlashSaleItem flashSaleItem, Long id) {
		Optional<FlashSaleItem> optionalFlashSaleItem = flashSaleItemRepository.findById(id);
		if (optionalFlashSaleItem.isPresent()) {
			FlashSaleItem itemToUpdate = optionalFlashSaleItem.get();
			//itemToUpdate.setName(flashSaleItem.getName());
			//itemToUpdate.setPrice(flashSaleItem.getPrice());
			itemToUpdate.setQuantity(flashSaleItem.getQuantity());
			return flashSaleItemRepository.save(itemToUpdate);
		} else {
			return null;
		}
	}

	@Override
	public void deleteFlashSaleItem(Long id) {
		Optional<FlashSaleItem> flashSaleItem = flashSaleItemRepository.findById(id);
		if (flashSaleItem.isPresent()) {
			flashSaleItemRepository.deleteById(id);
		} 
		
	}

}
