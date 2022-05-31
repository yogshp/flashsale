package com.popshop.live.online.assessment.flashsale.rabbitmq.queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popshop.live.online.assessment.flashsale.model.Order;

@Service
public class FlashSaleBulkOrderSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	@Autowired
	private Queue queue;
	private static Logger logger = LogManager.getLogger(FlashSaleBulkOrderSender.class.toString());

	public void send(Order order) {
		rabbitTemplate.convertAndSend(queue.getName(), order);
		logger.info("Sending Message to the Queue : " + order.toString());
	}
}