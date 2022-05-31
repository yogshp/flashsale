package com.popshop.live.online.assessment.flashsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;
import com.popshop.live.online.assessment.flashsale.model.Order;
import com.popshop.live.online.assessment.flashsale.model.Refund;
import com.popshop.live.online.assessment.flashsale.model.User;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleItemService;
import com.popshop.live.online.assessment.flashsale.service.OrderService;
import com.popshop.live.online.assessment.flashsale.service.RefundService;
import com.popshop.live.online.assessment.flashsale.service.UserService;
import com.popshop.live.online.assessment.flashsale.util.FlashSaleValidationUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/flashsale")
@Tag(name ="Refunds", description = "This API is used to manage the Flash Sale Refund requests")
public class FlashSaleRefundController {
	@Autowired
	private RefundService refundService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private FlashSaleItemService flashSaleItemService;

	@PostMapping("/refunds")
	public ResponseEntity<Refund> createRefund(@RequestBody Refund refund) {
		if (FlashSaleValidationUtil.validateRefundRequestPayload(refund)) {
			Order order = orderService.getOrder(refund.getOrderId());
			if (order != null) {
				Order updatedOrder = orderService.updateOrder(order, refund.getOrderId());
				if (updatedOrder != null) {
					User user = userService.getUser(order.getUserId());
					if (user != null) {
						User userToUpdate = new User();
						userToUpdate.setBalance(user.getBalance() + refund.getAmount());
						User updatedUser = userService.updateUser(userToUpdate, user.getId());
						if (updatedUser != null) {
							FlashSaleItem flashSaleItem = flashSaleItemService
									.getFlashSaleItem(order.getFlashSaleItemId());
							FlashSaleItem flashSaleItemToUpdate = new FlashSaleItem();
							flashSaleItemToUpdate.setQuantity(flashSaleItem.getQuantity() + order.getQuantity());
							FlashSaleItem updatedFlashSaleItem = flashSaleItemService
									.updateFlashSaleItem(flashSaleItemToUpdate, order.getFlashSaleItemId());
							if (updatedFlashSaleItem != null) {
								Refund createdRefund = refundService.createRefund(refund);
								if (createdRefund != null) {
									return new ResponseEntity<Refund>(createdRefund, HttpStatus.CREATED);
								}
							}
						}
					}
				}
			}
		}
		return new ResponseEntity<Refund>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/refunds/{id}")
	public ResponseEntity<Refund> getRefund(@PathVariable("id") Long id) {
		if (id != null) {
			Refund refund = refundService.getRefund(id);
			if (refund != null) {
				return new ResponseEntity<Refund>(refund, HttpStatus.OK);
			} else {
				return new ResponseEntity<Refund>(refund, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Refund>(HttpStatus.BAD_REQUEST);
		}
	}
}
