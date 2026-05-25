package com.fashionstore.dao.impl;

import java.sql.*;
import java.util.*;

import com.fashionstore.dao.ProductDAO;
import com.fashionstore.model.Product;
import com.fashionstore.util.DBConnection;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> getAllProducts() {

        List<Product> list = new ArrayList<>();

        // 🔥 FIXED QUERY
        String query = "SELECT * FROM products WHERE is_active = 1";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = extractProduct(rs);
                list.add(p);
            }

            System.out.println("DAO DEBUG: Products fetched = " + list.size());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Product getProductById(int id) {

        String query = "SELECT * FROM products WHERE product_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractProduct(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {

        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id=? AND is_active = 1";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> searchProducts(String keyword) {

        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE product_name LIKE ? AND is_active = 1";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Product> getProductsByPriceRange(double min, double max) {

        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE price BETWEEN ? AND ? AND is_active = 1";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(extractProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean addProduct(Product p) {

        String query = "INSERT INTO products (category_id, product_name, description, price, image_url, is_active) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, p.getCategoryId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getDescription());
            ps.setDouble(4, p.getPrice());
            ps.setString(5, p.getImageUrl());
            ps.setBoolean(6, p.isActive());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateProduct(Product p) {

        String query = "UPDATE products SET category_id=?, product_name=?, description=?, price=?, image_url=?, is_active=? WHERE product_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, p.getCategoryId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getDescription());
            ps.setDouble(4, p.getPrice());
            ps.setString(5, p.getImageUrl());
            ps.setBoolean(6, p.isActive());
            ps.setInt(7, p.getProductId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProduct(int id) {

        String query = "DELETE FROM products WHERE product_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private Product extractProduct(ResultSet rs) throws SQLException {

        Product p = new Product();

        p.setProductId(rs.getInt("product_id"));
        p.setCategoryId(rs.getInt("category_id"));
        p.setProductName(rs.getString("product_name"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getDouble("price"));
        p.setImageUrl(rs.getString("image_url"));
        p.setActive(rs.getBoolean("is_active"));

        return p;
    }
}