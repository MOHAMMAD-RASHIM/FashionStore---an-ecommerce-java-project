package com.fashionstore.dao.impl;

import java.sql.*;
import com.fashionstore.dao.CartDAO;
import com.fashionstore.model.Cart;
import com.fashionstore.util.DBConnection;

public class CartDAOImpl implements CartDAO {

    @Override
    public Cart createCart(int userId) {
        String query = "INSERT INTO cart (user_id) VALUES (?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt(1));
                cart.setUserId(userId);
                return cart;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cart getCartByUserId(int userId) {
        String query = "SELECT * FROM cart WHERE user_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                return cart;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cart getCartById(int cartId) {
        String query = "SELECT * FROM cart WHERE cart_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("cart_id"));
                cart.setUserId(rs.getInt("user_id"));
                return cart;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteCart(int cartId) {
        String query = "DELETE FROM cart WHERE cart_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, cartId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}