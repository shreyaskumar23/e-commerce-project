package com.bazinga.au.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazinga.au.model.CheckoutCart;
import com.bazinga.au.service.CartService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	CartService cartService;

	@PostMapping(value = "/updateorder", consumes = "application/json")
	public ResponseEntity<?> checkoutOrder(@RequestBody CheckoutCart checkoutCart) {
		try {
			List<CheckoutCart> obj = cartService.saveProductsForCheckOut(checkoutCart);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
