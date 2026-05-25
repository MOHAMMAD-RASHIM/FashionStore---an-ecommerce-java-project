package com.fashionstore.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fashionstore.dao.*;
import com.fashionstore.dao.impl.*;
import com.fashionstore.model.*;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAOImpl();
    private CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // 1. Check Login Status
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            // 2. Get Parameters from Form
            int productId = Integer.parseInt(request.getParameter("id"));
            String size = request.getParameter("size"); // Get the size from the dropdown

            // 3. Get/Create Cart for this User
            Cart cart = cartDAO.getCartByUserId(userId);
            if (cart == null) {
                cart = cartDAO.createCart(userId);
            }

            // 4. Check if this product with this SPECIFIC size is already in the cart
            CartItem existingItem = cartItemDAO.getCartItem(cart.getCartId(), productId, size);

            if (existingItem != null) {
                // If it exists, just increase quantity
                existingItem.setQuantity(existingItem.getQuantity() + 1);
                cartItemDAO.updateCartItem(existingItem);
            } else {
                // If it's a new item (or new size), add as new row
                Product product = productDAO.getProductById(productId);
                
                CartItem newItem = new CartItem();
                newItem.setCartId(cart.getCartId());
                newItem.setProductId(productId);
                newItem.setSizeLabel(size);
                newItem.setQuantity(1);
                newItem.setUnitPrice(product.getPrice());

                cartItemDAO.addToCart(newItem);
            }

            // 5. Success -> Redirect to Cart Page
            response.sendRedirect(request.getContextPath() + "/cart");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}