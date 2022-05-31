package com.popshop.live.online.assessment.flashsale.rabbitmq.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.popshop.live.online.assessment.flashsale.model.FlashSaleEvent;
import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;
import com.popshop.live.online.assessment.flashsale.model.Order;
import com.popshop.live.online.assessment.flashsale.model.User;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleEventService;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleItemService;
import com.popshop.live.online.assessment.flashsale.service.OrderService;
import com.popshop.live.online.assessment.flashsale.service.UserService;
import com.popshop.live.online.assessment.flashsale.util.FlashSaleValidationUtil;

@Component
@RabbitListener(queues = "rabbitmq.queue", id = "listener")
public class FlashSaleBulkOrderReceiver {
	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private FlashSaleItemService flashSaleItemService;

	@Autowired
	private FlashSaleEventService flashSaleEventService;
	private static Logger logger = LogManager.getLogger(FlashSaleBulkOrderReceiver.class.toString());

	@RabbitHandler
	public void receiver(Order order) {
		logger.info("create order process started");
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
							logger.info("Order: "+order.getId()+" was successfully created");
						}
					}
				}
			}
			logger.info("create order process ended");
	  }
}
