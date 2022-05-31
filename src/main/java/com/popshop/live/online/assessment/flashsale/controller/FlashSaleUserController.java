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

import com.popshop.live.online.assessment.flashsale.model.User;
import com.popshop.live.online.assessment.flashsale.service.UserService;
import com.popshop.live.online.assessment.flashsale.util.FlashSaleValidationUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/flashsale")
@Tag(name ="Users", description = "This API is used to manage the Flash Sale Users")
public class FlashSaleUserController {
	@Autowired
	private UserService userService;

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (FlashSaleValidationUtil.validateUserRequestPayload(user)) {
			User createdUser = userService.createUser(user);
			if (createdUser != null) {
				return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		if (id != null) {
			User user = userService.getUser(id);
			if (user != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			} else {
				return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
}
