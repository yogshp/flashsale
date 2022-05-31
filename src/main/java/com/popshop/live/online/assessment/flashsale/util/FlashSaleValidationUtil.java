package com.popshop.live.online.assessment.flashsale.util;

import java.time.LocalDateTime;

import com.popshop.live.online.assessment.flashsale.model.FlashSaleEvent;
import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;
import com.popshop.live.online.assessment.flashsale.model.Order;
import com.popshop.live.online.assessment.flashsale.model.Refund;
import com.popshop.live.online.assessment.flashsale.model.User;

public class FlashSaleValidationUtil {

	public static boolean validateUserRequestPayload(User user) {
		if (user != null) {
			if (user.getName() != null && !user.getName().isEmpty() && user.getEmailId() != null
					&& !user.getEmailId().isEmpty() && user.getBalance() != null && user.getBalance() > 0.0) {
				return true;
			}
		}
		return false;
	}

	public static boolean validateFlashSaleItemRequestPayload(FlashSaleItem flashSaleItem) {
		if (flashSaleItem != null) {
			if (flashSaleItem.getName() != null && !flashSaleItem.getName().isEmpty()
					&& flashSaleItem.getPrice() != null && flashSaleItem.getPrice() > 0.0
					&& flashSaleItem.getQuantity() != null && flashSaleItem.getQuantity() > 0
					&& flashSaleItem.getBeginAt() != null && flashSaleItem.getBeginAt().isAfter(LocalDateTime.now())
					&& flashSaleItem.getEndAt() != null
					&& flashSaleItem.getEndAt().isAfter(flashSaleItem.getBeginAt())) {
				return true;
			}
		}
		return false;
	}

	public static boolean validateOrderRequestPayload(Order order) {
		if (order != null && order.getName() != null && !order.getName().isEmpty()
				&& order.getFlashSaleEventId() != null && order.getUserId() != null
				&& order.getFlashSaleItemId() != null && order.getAmount() != null && order.getAmount() > 0) {
			return true;
		}
		return false;
	}

	public static boolean validateFlashSaleEventRequestPayload(FlashSaleEvent flashSaleEvent) {
		if (flashSaleEvent != null && flashSaleEvent.getName() != null && !flashSaleEvent.getName().isEmpty()
				&& flashSaleEvent.getQuantity() != null && flashSaleEvent.getQuantity() > 0
				&& flashSaleEvent.getBeginAt() != null && flashSaleEvent.getBeginAt().isAfter(LocalDateTime.now())
				&& flashSaleEvent.getEndAt() != null
				&& flashSaleEvent.getEndAt().isAfter(flashSaleEvent.getBeginAt())) {
			return true;
		}
		return false;
	}

	public static boolean validateRefundRequestPayload(Refund refund) {
		if (refund != null && refund.getOrderId() != null && refund.getAmount() != null) {
			return true;
		}
		return false;
	}
	
	public static boolean validateOrder(Order order, User user, FlashSaleItem flashSaleItem, FlashSaleEvent flashSaleEvent) {
		if (FlashSaleValidationUtil.validateOrderRequestPayload(order)) {
			if (user != null && user.getBalance() >= order.getAmount()) {
				if (flashSaleItem.getQuantity() > 0 && flashSaleItem.getBeginAt().compareTo(LocalDateTime.now()) <= 0
						&& flashSaleItem.getEndAt().compareTo(LocalDateTime.now()) >= 0
						&& flashSaleItem.getBeginAt().compareTo(flashSaleEvent.getBeginAt()) == 0
						&& flashSaleItem.getEndAt().compareTo(flashSaleEvent.getEndAt()) == 0) {
					return true;
				}
			}
		}
		return false;
	}
}
