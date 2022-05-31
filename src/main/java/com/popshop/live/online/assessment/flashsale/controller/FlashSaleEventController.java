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

import com.popshop.live.online.assessment.flashsale.model.FlashSaleEvent;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleEventService;
import com.popshop.live.online.assessment.flashsale.util.FlashSaleValidationUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/flashsale")
@Tag(name ="Events", description = "This API is used to manage the Flash Sale Events")
public class FlashSaleEventController {
	@Autowired
	private FlashSaleEventService flashSaleEventService;
	
	@PostMapping("/events")
	public ResponseEntity<FlashSaleEvent> createFlashSaleEvent(@RequestBody FlashSaleEvent flashSaleEvent) {
		if (FlashSaleValidationUtil.validateFlashSaleEventRequestPayload(flashSaleEvent)) {
			FlashSaleEvent createdFlashSaleEvent= flashSaleEventService.createFlashSaleEvent(flashSaleEvent);
			if (createdFlashSaleEvent != null) {
				return new ResponseEntity<FlashSaleEvent>(createdFlashSaleEvent, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<FlashSaleEvent>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/events/{id}")
	public ResponseEntity<FlashSaleEvent> getFlashSaleEvent(@PathVariable("id") Long id) {
		if (id != null) {
			FlashSaleEvent flashSaleEvent = flashSaleEventService.getFlashSaleEvent(id);
			if (flashSaleEvent != null) {
				return new ResponseEntity<FlashSaleEvent>(flashSaleEvent, HttpStatus.OK);
			} else {
				return new ResponseEntity<FlashSaleEvent>(flashSaleEvent, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<FlashSaleEvent>(HttpStatus.BAD_REQUEST);
		}
	}
}
