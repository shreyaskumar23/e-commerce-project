package com.bazinga.au.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bazinga.au.model.CheckoutCart;

public interface OrdersRepository extends JpaRepository<CheckoutCart, Integer>{
	@Query("from CheckoutCart d where d.userId=:userId")
	List<CheckoutCart> getCheckoutByUserID(@Param("userId") int userId);
}
