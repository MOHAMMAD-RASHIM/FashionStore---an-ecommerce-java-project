package com.fashionstore.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("search");
        String category = request.getParameter("category");

        List<Product> products;

        // 🔍 SEARCH
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productDAO.searchProducts(keyword);
        }
        // 🏷️ CATEGORY FILTER
        else if (category != null && !category.isEmpty()) {
            int categoryId = Integer.parseInt(category);
            products = productDAO.getProductsByCategory(categoryId);
        }
        // 📦 ALL PRODUCTS
        else {
            products = productDAO.getAllProducts();
        }

        request.setAttribute("products", products);

        request.getRequestDispatcher("/WEB-INF/views/products.jsp")
               .forward(request, response);
    }
}