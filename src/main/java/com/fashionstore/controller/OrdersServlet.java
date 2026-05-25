package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fashionstore.dao.*;
import com.fashionstore.dao.impl.*;
import com.fashionstore.model.*;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    private OrderDAO orderDAO = new OrderDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        List<Order> orders = orderDAO.getOrdersByUserId(userId);

        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/WEB-INF/views/orders.jsp")
               .forward(request, response);
    }
}