package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fashionstore.dao.*;
import com.fashionstore.dao.impl.*;
import com.fashionstore.model.*;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    private CartDAO cartDAO = new CartDAOImpl();
    private CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        String address = request.getParameter("address");
        String payment = request.getParameter("payment");

        Cart cart = cartDAO.getCartByUserId(userId);
        List<CartItem> items = cartItemDAO.getCartItems(cart.getCartId());

        double total = 0;
        for (CartItem item : items) {
            total += item.getQuantity() * item.getUnitPrice();
        }

        // 🔥 CREATE ORDER
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setPaymentMethod(payment);
        order.setOrderStatus("PLACED");
        order.setDeliveryAddress(address);

        orderDAO.placeOrder(order);

        // 🔥 ADD ORDER ITEMS
        for (CartItem item : items) {

            Product product = productDAO.getProductById(item.getProductId());

            OrderItem oi = new OrderItem();
            oi.setOrderId(order.getOrderId());
            oi.setProductId(item.getProductId());
            oi.setProductName(product.getProductName());
            oi.setSizeLabel(item.getSizeLabel());
            oi.setQuantity(item.getQuantity());
            oi.setUnitPrice(item.getUnitPrice());
            oi.setSubtotal(item.getQuantity() * item.getUnitPrice());

            orderItemDAO.addOrderItem(oi);
        }

        // 🔥 CLEAR CART
        cartItemDAO.clearCart(cart.getCartId());

        response.sendRedirect("orders");
    }
}