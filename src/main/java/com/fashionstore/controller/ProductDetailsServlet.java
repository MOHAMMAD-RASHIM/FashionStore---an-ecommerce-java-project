package com.fashionstore.controller;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

@WebServlet("/product")
public class ProductDetailsServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        int productId = Integer.parseInt(idParam);

        Product product = productDAO.getProductById(productId);

        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        request.setAttribute("product", product);

        request.getRequestDispatcher("/WEB-INF/views/product-details.jsp")
               .forward(request, response);
    }
}