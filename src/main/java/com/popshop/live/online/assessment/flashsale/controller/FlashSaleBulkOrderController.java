package com.popshop.live.online.assessment.flashsale.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.popshop.live.online.assessment.flashsale.enums.OrderStatus;
import com.popshop.live.online.assessment.flashsale.model.FlashSaleEvent;
import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;
import com.popshop.live.online.assessment.flashsale.model.Order;
import com.popshop.live.online.assessment.flashsale.model.User;
import com.popshop.live.online.assessment.flashsale.rabbitmq.queue.FlashSaleBulkOrderReceiver;
import com.popshop.live.online.assessment.flashsale.rabbitmq.queue.FlashSaleBulkOrderSender;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleEventService;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleItemService;
import com.popshop.live.online.assessment.flashsale.service.OrderService;
import com.popshop.live.online.assessment.flashsale.service.UserService;
import com.popshop.live.online.assessment.flashsale.util.FlashSaleValidationUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/flashsale")
@Tag(name ="Bulk Orders", description = "This API is used to manage the bulk Flash Sale Order requests")
public class FlashSaleBulkOrderController {
	@Autowired
	FlashSaleBulkOrderSender rabbitMQSender;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private FlashSaleItemService flashSaleItemService;

	@Autowired
	private FlashSaleEventService flashSaleEventService;
	
	private static Logger logger = LogManager.getLogger(FlashSaleBulkOrderReceiver.class.toString());

	@PostMapping(value = "/bulk/orders")
	public ResponseEntity<String> producer(@RequestBody Order order) {
		if (FlashSaleValidationUtil.validateOrderRequestPayload(order)) {
			Order existingOrder = orderService.findByFlashSaleEventIdAndUserIdAndStatus(order.getFlashSaleEventId(),
					order.getUserId(), OrderStatus.OPEN);
			if (existingOrder == null) {
				User user = userService.getUser(order.getUserId());
				FlashSaleItem flashSaleItem = flashSaleItemService.getFlashSaleItem(order.getFlashSaleItemId());
				FlashSaleEvent flashSaleEvent = flashSaleEventService.getFlashSaleEvent(order.getFlashSaleEventId());
				if (FlashSaleValidationUtil.validateOrder(order, user, flashSaleItem, flashSaleEvent)) {
					rabbitMQSender.send(order);
					logger.info("Order Request was received: "+order);
					return new ResponseEntity<String>(
							"Your Order Request was received and we will inform you once it is processed",
							HttpStatus.CREATED);
				}
			}
		}
		logger.info("Order Request was rejected: "+order);
		return new ResponseEntity<String>("Your Order Request was rejected", HttpStatus.BAD_REQUEST);
	}
}