package com.fashionstore.dao.impl;

import java.sql.*;
import java.util.*;
import com.fashionstore.dao.OrderDAO;
import com.fashionstore.model.Order;
import com.fashionstore.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean placeOrder(Order order) {
        String query = "INSERT INTO orders (user_id, total_amount, payment_method, order_status, delivery_address) VALUES (?, ?, ?, ?, ?)";
        // Open connection inside try-with-resources
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getUserId());
            ps.setDouble(2, order.getTotalAmount());
            ps.setString(3, order.getPaymentMethod());
            ps.setString(4, order.getOrderStatus());
            ps.setString(5, order.getDeliveryAddress());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        order.setOrderId(rs.getInt(1)); 
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> list = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id=? ORDER BY order_date DESC";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractOrder(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Order getOrderById(int orderId) {
        String query = "SELECT * FROM orders WHERE order_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractOrder(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) {
        String query = "UPDATE orders SET order_status=? WHERE order_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setOrderId(rs.getInt("order_id"));
        o.setUserId(rs.getInt("user_id"));
        o.setOrderDate(rs.getTimestamp("order_date"));
        o.setTotalAmount(rs.getDouble("total_amount"));
        o.setPaymentMethod(rs.getString("payment_method"));
        o.setOrderStatus(rs.getString("order_status"));
        o.setDeliveryAddress(rs.getString("delivery_address"));
        return o;
    }
   
}