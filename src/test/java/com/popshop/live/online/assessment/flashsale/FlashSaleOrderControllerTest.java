package com.popshop.live.online.assessment.flashsale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.popshop.live.online.assessment.flashsale.model.Order;
import com.popshop.live.online.assessment.flashsale.model.User;

@SpringBootTest(classes = FlashSaleApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlashSaleOrderControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testgetOrder() {
		assertTrue(this.restTemplate.getForObject("http://localhost:8080/flashsale/orders/2", User.class) != null);
	}

	@Test
	public void testCreateOrder() {
		Order order = new Order("Flash Sale Order 5", 1l, 4l, 1l, 1, 280.00);
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity("http://localhost:8080/flashsale/orders", order, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
}
