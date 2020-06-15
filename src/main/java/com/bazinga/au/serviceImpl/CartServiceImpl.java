package com.bazinga.au.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazinga.au.model.AddToCart;
import com.bazinga.au.model.CheckoutCart;
import com.bazinga.au.repository.AddToCartRepository;
import com.bazinga.au.repository.OrdersRepository;
import com.bazinga.au.service.CartService;

import io.jsonwebtoken.lang.Collections;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	AddToCartRepository addToCartRepository;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Override
	public List<AddToCart> addCartbyUserIdAndProductId(int userId, long productId, int qty, double price) throws Exception {
		try{
			AddToCart obj = new AddToCart();
			obj.setUserId(userId);
			obj.setProductId(productId);
			obj.setQty(qty);
			obj.setPrice(price);
			addToCartRepository.save(obj);
			return this.getCartbyUserId(userId);
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<AddToCart> getCartbyUserId(int userId) {
		return addToCartRepository.getCartByUserID(userId);
	}

	@Override
	public List<AddToCart> removeCartbyProductId(int userId,long productId) {
		addToCartRepository.deleteCartByProductId(productId);
		return this.getCartbyUserId(userId);
	}

	@Override
	public void updateQtyByProductId(long productId, int qty, double price) {
		addToCartRepository.updateQtyByProductId(productId, price, qty);
	}

	@Override
	public boolean isItemPresent(AddToCart addToCart) {
		List<AddToCart> newCart = addToCartRepository.getCartByProductIdAndUserID(addToCart.getUserId(),addToCart.getProductId());
		if(Collections.isEmpty(newCart)) {
		return false;
		}else {
		return true;
		}
	}

	@Override
	public int getqty(int userId, long productId) {
		return addToCartRepository.getqty(userId, productId);
	}

	@Override
	public List<CheckoutCart> saveProductsForCheckOut(CheckoutCart checkoutCart) throws Exception {
		double sum =0;
		int i=2;
		List<AddToCart> cartItems = addToCartRepository.getCartByUserID(checkoutCart.getUserId());
		for(AddToCart cart : cartItems) {
			sum = sum + cart.getPrice()*cart.getQty();
		}
		if(sum==checkoutCart.getTotalprice()) {
			
			CheckoutCart cc = new CheckoutCart();
				cc.setOrderId(i);
				cc.setPaymentType(checkoutCart.getPaymentType());
				cc.setTotalprice(sum);
				cc.setUserId(checkoutCart.getUserId());
				ordersRepository.save(cc);
				return this.getCheckoutbyUserId(checkoutCart.getUserId());
			
		}else {
			throw new Exception("Total amount is mismatch");
		}
	}

	@Override
	public List<CheckoutCart> getCheckoutbyUserId(int userId) {
		return ordersRepository.getCheckoutByUserID(userId);
	}	

}
