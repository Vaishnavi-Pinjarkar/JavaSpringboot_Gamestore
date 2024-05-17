package com.itvedant.gamestore.service;

import org.springframework.beans.factory.annotation.Autowired;


import com.itvedant.gamestore.entity.Cart;
import com.itvedant.gamestore.entity.Product;
import com.itvedant.gamestore.entity.User;
import com.itvedant.gamestore.repository.CartRepository;

public class CartService {
	@Autowired
	private CartRepository cartRepository;
	
//	public Cart createCart (User user,Product product) {
//		
//		Cart cart = new Cart();
//		
//		cart.setId(user);
//		cart.setProd_id(product);
//		cart.setQuantity(1);
//		cart.setTotal_price(product.getPrice());
//		
//		this.cartRepository.save(cart);
//		
//		return cart;
//	
//	}

}
