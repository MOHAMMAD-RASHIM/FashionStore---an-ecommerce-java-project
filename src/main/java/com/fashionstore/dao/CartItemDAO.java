package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.CartItem;

public interface CartItemDAO {
    boolean addToCart(CartItem item);
    boolean updateCartItem(CartItem item);
    boolean removeCartItem(int cartItemId);
    List<CartItem> getCartItems(int cartId);
    boolean clearCart(int cartId);
    CartItem getCartItemById(int id);
    CartItem getCartItemByProductAndSize(int cartId, int productId, String size);
    boolean addCartItem(CartItem item);
    
    // Updated to include size check
    CartItem getCartItem(int cartId, int productId, String size);
}