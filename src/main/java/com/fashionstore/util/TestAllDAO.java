package com.fashionstore.util;

import java.util.List;

import com.fashionstore.dao.*;
import com.fashionstore.dao.impl.*;
import com.fashionstore.model.*;

public class TestAllDAO {

    public static void main(String[] args) {

        // ================= USER DAO =================
        System.out.println("===== USER DAO TEST =====");
        UserDAO userDAO = new UserDAOImpl();

        User user = userDAO.getUserById(6);
        if (user != null) {
            System.out.println("User: " + user.getFullName());
        }

        System.out.println("Email Exists: " + userDAO.isEmailExists("yash@example.com"));

        // ================= CATEGORY DAO =================
        System.out.println("\n===== CATEGORY DAO TEST =====");
        CategoryDAO categoryDAO = new CategoryDAOImpl();

        List<Category> categories = categoryDAO.getAllCategories();
        for (Category c : categories) {
            System.out.println(c.getCategoryId() + " - " + c.getCategoryName());
        }

        // ================= PRODUCT DAO =================
        System.out.println("\n===== PRODUCT DAO TEST =====");
        ProductDAO productDAO = new ProductDAOImpl();

        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            System.out.println(p.getProductName() + " - ₹" + p.getPrice());
        }

        // ================= PRODUCT SIZE DAO =================
        System.out.println("\n===== PRODUCT SIZE DAO TEST =====");
        ProductSizeDAO sizeDAO = new ProductSizeDAOImpl();

        List<ProductSize> sizes = sizeDAO.getSizesByProductId(1);
        for (ProductSize ps : sizes) {
            System.out.println("Size: " + ps.getSizeLabel() + " Stock: " + ps.getStockQuantity());
        }

        // ================= CART DAO =================
        System.out.println("\n===== CART DAO TEST =====");
        CartDAO cartDAO = new CartDAOImpl();

        Cart cart = cartDAO.getCartByUserId(6);
        if (cart != null) {
            System.out.println("Cart ID: " + cart.getCartId());
        }

        // ================= CART ITEM DAO =================
        System.out.println("\n===== CART ITEM DAO TEST =====");
        CartItemDAO cartItemDAO = new CartItemDAOImpl();

        List<CartItem> cartItems = cartItemDAO.getCartItems(3);
        for (CartItem item : cartItems) {
            System.out.println("Product ID: " + item.getProductId() +
                               " Qty: " + item.getQuantity());
        }

        // ================= ORDER DAO =================
        System.out.println("\n===== ORDER DAO TEST =====");
        OrderDAO orderDAO = new OrderDAOImpl();

        List<Order> orders = orderDAO.getOrdersByUserId(6);
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId() +
                               " Total: ₹" + o.getTotalAmount());
        }

        // ================= ORDER ITEM DAO =================
        System.out.println("\n===== ORDER ITEM DAO TEST =====");
        OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

        List<OrderItem> orderItems = orderItemDAO.getOrderItems(1);
        for (OrderItem oi : orderItems) {
            System.out.println(oi.getProductName() +
                               " Qty: " + oi.getQuantity());
        }

        System.out.println("\n===== ALL DAO TEST COMPLETED =====");
    }
}