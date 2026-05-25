package com.fashionstore.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.CategoryDAO;
import com.fashionstore.model.Category;
import com.fashionstore.util.DBConnection;

public class CategoryDAOImpl implements CategoryDAO {

    private Connection connection = DBConnection.getConnection();

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM categories WHERE is_active = true";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setDescription(rs.getString("description"));
                c.setActive(rs.getBoolean("is_active"));

                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Category getCategoryById(int id) {
        String query = "SELECT * FROM categories WHERE category_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Category c = new Category();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setDescription(rs.getString("description"));
                c.setActive(rs.getBoolean("is_active"));
                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addCategory(Category category) {
        String query = "INSERT INTO categories (category_name, description, is_active) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            ps.setBoolean(3, category.isActive());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCategory(Category category) {
        String query = "UPDATE categories SET category_name=?, description=?, is_active=? WHERE category_id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getDescription());
            ps.setBoolean(3, category.isActive());
            ps.setInt(4, category.getCategoryId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCategory(int id) {
        String query = "DELETE FROM categories WHERE category_id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}