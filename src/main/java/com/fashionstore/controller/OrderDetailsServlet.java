package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fashionstore.dao.*;
import com.fashionstore.dao.impl.*;
import com.fashionstore.model.*;

@WebServlet("/order-details")
public class OrderDetailsServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("id"));

        Order order = orderDAO.getOrderById(orderId);
        List<OrderItem> items = orderItemDAO.getOrderItems(orderId);

        request.setAttribute("order", order);
        request.setAttribute("items", items);

        request.getRequestDispatcher("/WEB-INF/views/order-details.jsp")
               .forward(request, response);
    }
}