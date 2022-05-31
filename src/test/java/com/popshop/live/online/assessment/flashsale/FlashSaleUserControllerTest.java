package com.popshop.live.online.assessment.flashsale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.popshop.live.online.assessment.flashsale.model.User;

@SpringBootTest(classes = FlashSaleApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlashSaleUserControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testgetUser() {
		assertTrue(this.restTemplate.getForObject("http://localhost:8080/flashsale/users/1", User.class) != null);
	}

	@Test
	public void testCreateUser() {
		User user = new User("Integration Test", "integration.test@gmail.com", 4000.00);
		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("http://localhost:8080/flashsale/users",
				user, String.class);
		assertEquals(201, responseEntity.getStatusCodeValue());
	}
}
