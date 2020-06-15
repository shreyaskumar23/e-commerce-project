package com.bazinga.au.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazinga.au.model.AddToCart;
import com.bazinga.au.service.CartService;

@RestController
@RequestMapping("/carts")
public class AddToCartController {

	@Autowired
	CartService cartService;

	@PostMapping(value = "/items", consumes = "application/json")
	public ResponseEntity<?> addToCart(@RequestBody AddToCart addToCart) {

		try {
			if (cartService.isItemPresent(addToCart)) {
				int sum = cartService.getqty(addToCart.getUserId(), addToCart.getProductId()) + addToCart.getQty();
				cartService.updateQtyByProductId(addToCart.getProductId(), sum, addToCart.getPrice());
				List<AddToCart> obj = cartService.getCartbyUserId(addToCart.getUserId());
				return ResponseEntity.ok(obj);
			} else {
				List<AddToCart> obj = cartService.addCartbyUserIdAndProductId(addToCart.getUserId(),
						addToCart.getProductId(), addToCart.getQty(), addToCart.getPrice());
				return ResponseEntity.ok(obj);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Bad request: productId,userId,qty,price should be present in body");
		}
	}

	@DeleteMapping(value = "removeProduct/{userId}/{productId}")
	public ResponseEntity<?> removeCartWithProductId(@PathVariable("userId") int userId,
			@PathVariable("productId") long productId) {
		try {
			cartService.removeCartbyProductId(userId, productId);
			return new ResponseEntity<>("Product is deleted", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Bad request: /{userId}/{productId} should be present in url");
		}
	}

	@GetMapping("getCartsByUserId/{userId}")
	public ResponseEntity<?> getCartsByUserId(@PathVariable int userId) {
		try {
			List<AddToCart> obj = cartService.getCartbyUserId(userId);
			return ResponseEntity.ok(obj);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error : userId should be present in url");
		}
	}

	@PutMapping("updateQtyForCart")
	public ResponseEntity<?> updateQtyForCart(@RequestBody AddToCart addCartRequest) {
		try {
			cartService.updateQtyByProductId(addCartRequest.getProductId(), addCartRequest.getQty(),
					addCartRequest.getPrice());
			List<AddToCart> obj = cartService.getCartbyUserId(addCartRequest.getUserId());
			return ResponseEntity.ok(obj);

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Request is not proper");
		}
	}

}
