package com.bazinga.au.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bazinga.au.model.AddToCart;
import com.bazinga.au.model.CheckoutCart;

@Service
public interface CartService {
	List<AddToCart> addCartbyUserIdAndProductId(int id, long productId, int qty, double price) throws Exception;
	void updateQtyByProductId(long productId, int qty, double price);
	List<AddToCart> getCartbyUserId(int userId);
	List<AddToCart> removeCartbyProductId(int userId, long productId);
	boolean isItemPresent(AddToCart addToCart);
	int getqty(int userId,long productId);
	List<CheckoutCart> getCheckoutbyUserId(int userId);
	List<CheckoutCart> saveProductsForCheckOut(CheckoutCart checkoutCart) throws Exception;
	
}
