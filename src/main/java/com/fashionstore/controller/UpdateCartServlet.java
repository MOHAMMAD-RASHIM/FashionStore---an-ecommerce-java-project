package com.fashionstore.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.dao.impl.CartItemDAOImpl;
import com.fashionstore.model.CartItem;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CartItemDAO cartItemDAO = new CartItemDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        	String idParam = request.getParameter("id");

        	if (idParam == null) {
        	    response.sendRedirect(request.getContextPath() + "/cart");
        	    return;
        	}

        	int cartItemId = Integer.parseInt(idParam);
            String action = request.getParameter("action");

            CartItem item = cartItemDAO.getCartItemById(cartItemId);

            if (item != null) {

                if ("inc".equals(action)) {
                    item.setQuantity(item.getQuantity() + 1);
                    cartItemDAO.updateCartItem(item);

                } else if ("dec".equals(action)) {

                    int qty = item.getQuantity() - 1;

                    if (qty <= 0) {
                        cartItemDAO.removeCartItem(cartItemId);
                    } else {
                        item.setQuantity(qty);
                        cartItemDAO.updateCartItem(item);
                    }

                } else if ("remove".equals(action)) {
                    cartItemDAO.removeCartItem(cartItemId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect back to cart page
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}