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

import com.popshop.live.online.assessment.flashsale.enums.OrderStatus;
import com.popshop.live.online.assessment.flashsale.model.FlashSaleEvent;
import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;
import com.popshop.live.online.assessment.flashsale.model.Order;
import com.popshop.live.online.assessment.flashsale.model.User;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleEventService;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleItemService;
import com.popshop.live.online.assessment.flashsale.service.OrderService;
import com.popshop.live.online.assessment.flashsale.service.UserService;
import com.popshop.live.online.assessment.flashsale.util.FlashSaleValidationUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/flashsale")
@Tag(name ="Orders", description = "This API is used to manage the limited Flash Sale Order requests")
public class FlashSaleOrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private FlashSaleItemService flashSaleItemService;

	@Autowired
	private FlashSaleEventService flashSaleEventService;

	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		if (FlashSaleValidationUtil.validateOrderRequestPayload(order)) {
			Order existingOrder = orderService.findByFlashSaleEventIdAndUserIdAndStatus(order.getFlashSaleEventId(),
					order.getUserId(), OrderStatus.OPEN);
			if (existingOrder == null) {
				User user = userService.getUser(order.getUserId());
				FlashSaleItem flashSaleItem = flashSaleItemService.getFlashSaleItem(order.getFlashSaleItemId());
				FlashSaleEvent flashSaleEvent = flashSaleEventService.getFlashSaleEvent(order.getFlashSaleEventId());
				if (FlashSaleValidationUtil.validateOrder(order, user, flashSaleItem, flashSaleEvent)) {
					Order createdOrder = orderService.createOrder(order);
					if (createdOrder != null) {
						User userToUpdate = new User();
						userToUpdate.setBalance(user.getBalance() - order.getAmount());
						User updatedUser = userService.updateUser(userToUpdate, order.getUserId());
						if (updatedUser != null) {
							FlashSaleItem flashSaleItemToUpdate = new FlashSaleItem();
							flashSaleItemToUpdate.setQuantity(flashSaleItem.getQuantity() - 1);
							FlashSaleItem updatedFlashSaleItem = flashSaleItemService
									.updateFlashSaleItem(flashSaleItemToUpdate, order.getFlashSaleItemId());
							if (updatedFlashSaleItem != null) {
								return new ResponseEntity<Order>(createdOrder, HttpStatus.CREATED);
							}
						}
					}
				}
			}
		}
		return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
		if (id != null) {
			Order order = orderService.getOrder(id);
			if (order != null) {
				return new ResponseEntity<Order>(order, HttpStatus.OK);
			} else {
				return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		}
	}
}
