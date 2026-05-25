package com.fashionstore.dao.impl;

import java.sql.*;
import java.util.*;
import com.fashionstore.dao.CartItemDAO;
import com.fashionstore.model.CartItem;
import com.fashionstore.util.DBConnection;

public class CartItemDAOImpl implements CartItemDAO {
    
    @Override
    public boolean addToCart(CartItem item) {
        String query = "INSERT INTO cart_items (cart_id, product_id, size_label, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, item.getCartId());
            ps.setInt(2, item.getProductId());
            ps.setString(3, item.getSizeLabel());
            ps.setInt(4, item.getQuantity());
            ps.setDouble(5, item.getUnitPrice());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCartItem(CartItem item) {
        String query = "UPDATE cart_items SET quantity=? WHERE cart_item_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, item.getQuantity());
            ps.setInt(2, item.getCartItemId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeCartItem(int id) {
        String query = "DELETE FROM cart_items WHERE cart_item_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CartItem> getCartItems(int cartId) {
        List<CartItem> list = new ArrayList<>();
        String query = "SELECT * FROM cart_items WHERE cart_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItem item = new CartItem();
                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setCartId(rs.getInt("cart_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSizeLabel(rs.getString("size_label"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean clearCart(int cartId) {
        String query = "DELETE FROM cart_items WHERE cart_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, cartId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CartItem getCartItem(int cartId, int productId, String size) {
        CartItem item = null;
        // Logic: Check for the exact product AND size
        String query = "SELECT * FROM cart_items WHERE cart_id=? AND product_id=? AND size_label=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setString(3, size);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = new CartItem();
                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setCartId(rs.getInt("cart_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSizeLabel(rs.getString("size_label"));
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public CartItem getCartItemById(int id) {
        CartItem item = null;
        String query = "SELECT * FROM cart_items WHERE cart_item_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = new CartItem();
                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setCartId(rs.getInt("cart_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setSizeLabel(rs.getString("size_label")); // Added size
                item.setQuantity(rs.getInt("quantity"));
                item.setUnitPrice(rs.getDouble("unit_price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
    @Override
    public CartItem getCartItemByProductAndSize(int cartId, int productId, String size) {
        CartItem item = null;
        String query = "SELECT * FROM cart_items WHERE cart_id = ? AND product_id = ? AND size_label = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            ps.setString(3, size);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = new CartItem();
                item.setCartItemId(rs.getInt("cart_item_id"));
                item.setQuantity(rs.getInt("quantity"));
                // set other fields...
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return item;
    }

    @Override
    public boolean addCartItem(CartItem item) {
        String query = "INSERT INTO cart_items (cart_id, product_id, size_label, quantity, unit_price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, item.getCartId());
            ps.setInt(2, item.getProductId());
            ps.setString(3, item.getSizeLabel());
            ps.setInt(4, item.getQuantity());
            ps.setDouble(5, item.getUnitPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}