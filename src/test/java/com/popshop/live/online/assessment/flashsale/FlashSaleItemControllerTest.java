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

import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;

@SpringBootTest(classes = FlashSaleApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlashSaleItemControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testgetItem() {
		assertTrue(
				this.restTemplate.getForObject("http://localhost:8080/flashsale/items/1", FlashSaleItem.class) != null);
	}

	@Test
	public void testCreateItem() {
		FlashSaleItem flashSaleItem = new FlashSaleItem("Apple Iphone 13 Pro Max", 1000.00, 1,
				LocalDateTime.parse("2022-05-31T00:00:00"), LocalDateTime.parse("2022-05-31T23:59:59"));

		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/flashsale/items",
				flashSaleItem, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
}
