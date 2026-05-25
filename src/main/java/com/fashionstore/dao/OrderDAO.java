package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.Order;

public interface OrderDAO {

    // Place order
    boolean placeOrder(Order order);

    // Get orders by user
    List<Order> getOrdersByUserId(int userId);

    // Get order by ID
    Order getOrderById(int orderId);

    // Update order status
    boolean updateOrderStatus(int orderId, String status);
}