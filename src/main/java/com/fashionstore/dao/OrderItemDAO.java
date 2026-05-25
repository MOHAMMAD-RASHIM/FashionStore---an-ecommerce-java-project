package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.OrderItem;

public interface OrderItemDAO {

    // Add order item
    boolean addOrderItem(OrderItem item);

    // Get items by order ID
    List<OrderItem> getOrderItems(int orderId);
}