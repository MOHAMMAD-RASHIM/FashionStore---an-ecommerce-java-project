package com.fashionstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.dao.impl.ProductDAOImpl;
import com.fashionstore.model.Product;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> allProducts = new ArrayList<>();

        try {
            // 🔹 Fetch from DB
            allProducts = productDAO.getAllProducts();

            System.out.println("DEBUG: Total products fetched = " + 
                (allProducts != null ? allProducts.size() : "NULL"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔹 Safety: avoid null
        if (allProducts == null) {
            allProducts = new ArrayList<>();
        }

        // 🔹 Limit to 4
        List<Product> featuredProducts = allProducts.size() > 4
                ? allProducts.subList(0, 4)
                : allProducts;

        System.out.println("DEBUG: Featured products = " + featuredProducts.size());

        request.setAttribute("products", featuredProducts);

        request.getRequestDispatcher("/WEB-INF/views/home.jsp")
               .forward(request, response);
    }
}