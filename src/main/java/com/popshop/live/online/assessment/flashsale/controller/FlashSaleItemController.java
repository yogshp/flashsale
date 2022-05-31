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

import com.popshop.live.online.assessment.flashsale.model.FlashSaleItem;
import com.popshop.live.online.assessment.flashsale.service.FlashSaleItemService;
import com.popshop.live.online.assessment.flashsale.util.FlashSaleValidationUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/flashsale")
@Tag(name ="Items", description = "This API is used to manage the Flash Sale Items")
public class FlashSaleItemController {
	@Autowired
	private FlashSaleItemService flashSaleItemService;

	@PostMapping("/items")
	public ResponseEntity<FlashSaleItem> createUser(@RequestBody FlashSaleItem flashSaleItem) {
		if (FlashSaleValidationUtil.validateFlashSaleItemRequestPayload(flashSaleItem)) {
			FlashSaleItem createdItem = flashSaleItemService.createFlashSaleItem(flashSaleItem);
			if (createdItem != null) {
				return new ResponseEntity<FlashSaleItem>(createdItem, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<FlashSaleItem>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<FlashSaleItem> getUser(@PathVariable("id") Long id) {
		if (id != null) {
			FlashSaleItem flashSaleItem = flashSaleItemService.getFlashSaleItem(id);
			if (flashSaleItem != null) {
				return new ResponseEntity<FlashSaleItem>(flashSaleItem, HttpStatus.OK);
			} else {
				return new ResponseEntity<FlashSaleItem>(flashSaleItem, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<FlashSaleItem>(HttpStatus.BAD_REQUEST);
		}
	}
}

