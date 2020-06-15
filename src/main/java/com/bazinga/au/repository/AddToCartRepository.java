package com.bazinga.au.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bazinga.au.model.AddToCart;

@Repository
public interface AddToCartRepository extends JpaRepository<AddToCart, Integer>{
	@Query("from AddToCart d where d.userId=:userId")
	List<AddToCart> getCartByUserID(@Param("userId") int userId);
	@Query("from AddToCart d where d.productId=:productId and d.userId=:userId")
	List<AddToCart> getCartByProductIdAndUserID(@Param("userId") int userId,@Param("productId") long productId);
	@Query("select qty from AddToCart d where d.productId=:productId and d.userId=:userId")
	int getqty(@Param("userId") int userId,@Param("productId") long productId);
	@Modifying
	@Transactional
	@Query("Delete from AddToCart d where d.productId=:productId")
	void deleteCartByProductId(@Param("productId") long productId);
	@Modifying
	@Transactional
	@Query("Delete from AddToCart d where d.id=:userId")
	void deleteAllCartId(@Param("userId") long userId);
	
	@Modifying
	@Transactional
	@Query("update AddToCart d set d.qty=:qty, d.price=:price where d.productId=:productId")
	void updateQtyByProductId(@Param("productId") Long productId,@Param("price") double price,@Param("qty") Integer qty);
}
