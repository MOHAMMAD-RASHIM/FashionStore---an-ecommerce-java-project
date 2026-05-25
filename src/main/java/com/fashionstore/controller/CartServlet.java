package com.fashionstore.controller;

import java.io.IOException;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fashionstore.dao.*;
import com.fashionstore.dao.impl.*;
import com.fashionstore.model.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAOImpl();
    private CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Cart cart = cartDAO.getCartByUserId(userId);
        List<Map<String, Object>> cartData = new ArrayList<>();
        double total = 0;

        if (cart != null) {
            List<CartItem> items = cartItemDAO.getCartItems(cart.getCartId());

            for (CartItem item : items) {
                Product product = productDAO.getProductById(item.getProductId());
                if (product == null) continue;

                Map<String, Object> row = new HashMap<>();
                row.put("cartItemId", item.getCartItemId());
                row.put("name", product.getProductName());
                row.put("image", product.getImageUrl());
                row.put("price", item.getUnitPrice());
                row.put("qty", item.getQuantity());
                row.put("size", item.getSizeLabel()); // Added this line

                double subtotal = item.getUnitPrice() * item.getQuantity();
                row.put("subtotal", subtotal);
                total += subtotal;

                cartData.add(row);
            }
        }

        request.setAttribute("cartData", cartData);
        request.setAttribute("total", total);

        // Use forward to keep attributes alive for the JSP
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }

    
}