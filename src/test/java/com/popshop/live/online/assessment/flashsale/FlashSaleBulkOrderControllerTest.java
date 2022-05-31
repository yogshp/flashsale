package com.popshop.live.online.assessment.flashsale;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.popshop.live.online.assessment.flashsale.model.Order;

@SpringBootTest(classes = FlashSaleApplication.class,webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlashSaleBulkOrderControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testCreateOrder() {
		Order order = new Order("Flash Sale Order 5",1l,4l,1l,1,280.00);
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity("http://localhost:8080/flashsale/bulk/orders", order, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
}
