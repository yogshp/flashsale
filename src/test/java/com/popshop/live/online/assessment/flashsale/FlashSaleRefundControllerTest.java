package com.popshop.live.online.assessment.flashsale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.popshop.live.online.assessment.flashsale.model.Refund;

@SpringBootTest(classes = FlashSaleApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlashSaleRefundControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testgetUser() {
		assertTrue(this.restTemplate.getForObject("http://localhost:8080/flashsale/refunds/1", Refund.class) != null);
	}

	@Test
	public void testCreateUser() {
		Refund refund = new Refund(2l, 280.00);
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity("http://localhost:8080/flashsale/refunds", refund, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
}
