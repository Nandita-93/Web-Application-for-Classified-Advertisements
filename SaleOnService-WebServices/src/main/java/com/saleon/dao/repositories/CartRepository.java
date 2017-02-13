package com.saleon.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.saleon.model.Cart;


public interface CartRepository extends CrudRepository<Cart, Long> {
	
	@Query("from Cart where postUserId = :postUserId and status = 'IN_CART'")
    public List<Cart> getCartItems(@Param("postUserId") Long  postUserId);
	
	@Query("from Cart where itemId = :itemId")
    public Cart getCartItemDetails(@Param("itemId") Long  itemId);

}