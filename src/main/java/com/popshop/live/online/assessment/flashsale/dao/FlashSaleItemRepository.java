package com.popshop.live.online.assessment.flashsale.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;

@Repository
public interface FlashSaleItemRepository extends JpaRepository<FlashSaleItem, Long>{
	public FlashSaleItem findByName(String name);
}
