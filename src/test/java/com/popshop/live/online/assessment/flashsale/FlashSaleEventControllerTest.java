package com.popshop.live.online.assessment.flashsale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.popshop.live.online.assessment.flashsale.model.FlashSaleEvent;

@SpringBootTest(classes = FlashSaleApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlashSaleEventControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testgetEvent() {
		assertTrue(this.restTemplate.getForObject("http://localhost:8080/flashsale/events/1",
				FlashSaleEvent.class) != null);
	}

	@Test
	public void testCreateEvent() {

		FlashSaleEvent flashSaleEvent = new FlashSaleEvent("Flash Sale Event3", 1,
				LocalDateTime.parse("2022-05-31T00:00:00"), LocalDateTime.parse("2022-05-31T23:59:59"));
		ResponseEntity<String> responseEntity = this.restTemplate
				.postForEntity("http://localhost:8080/flashsale/events", flashSaleEvent, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
}
