package com.fashionstore.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.fashionstore.dao.*;
import com.fashionstore.dao.impl.*;
import com.fashionstore.model.*;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            User user = new User();
            user.setFullName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPhone(request.getParameter("phone"));
            user.setPassword(request.getParameter("password"));
            user.setAddress(request.getParameter("address"));

            // Check if email OR phone already exists to avoid SQL constraint violations
            if (userDAO.isEmailExists(user.getEmail())) {
                response.sendRedirect(request.getContextPath() + "/register?error=true&msg=exists");
                return;
            }

            boolean success = userDAO.registerUser(user);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/login?success=reg");
            } else {
                response.sendRedirect(request.getContextPath() + "/register?error=true");
            }
        } 
        else if ("login".equals(action)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userDAO.loginUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userName", user.getFullName());
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                response.sendRedirect(request.getContextPath() + "/login?error=true");
            }
        }
    }
}