package com.fashionstore.dao;

import com.fashionstore.model.Cart;

public interface CartDAO {
    Cart createCart(int userId);
    Cart getCartByUserId(int userId);
    Cart getCartById(int cartId);
    boolean deleteCart(int cartId);
}