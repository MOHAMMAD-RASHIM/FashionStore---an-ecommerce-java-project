package com.fashionstore.dao.impl;

import java.sql.*;
import java.util.*;

import com.fashionstore.dao.ProductSizeDAO;
import com.fashionstore.model.ProductSize;
import com.fashionstore.util.DBConnection;

public class ProductSizeDAOImpl implements ProductSizeDAO {

    private Connection connection = DBConnection.getConnection();

    @Override
    public List<ProductSize> getSizesByProductId(int productId) {
        List<ProductSize> list = new ArrayList<>();
        String query = "SELECT * FROM product_sizes WHERE product_id=? AND is_available=true";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductSize psObj = new ProductSize();
                psObj.setProductSizeId(rs.getInt("product_size_id"));
                psObj.setProductId(rs.getInt("product_id"));
                psObj.setSizeLabel(rs.getString("size_label"));
                psObj.setStockQuantity(rs.getInt("stock_quantity"));
                psObj.setSkuCode(rs.getString("sku_code"));
                psObj.setAvailable(rs.getBoolean("is_available"));

                list.add(psObj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public ProductSize getProductSize(int productId, String sizeLabel) {
        String query = "SELECT * FROM product_sizes WHERE product_id=? AND size_label=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setString(2, sizeLabel);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ProductSize psObj = new ProductSize();
                psObj.setProductSizeId(rs.getInt("product_size_id"));
                psObj.setProductId(rs.getInt("product_id"));
                psObj.setSizeLabel(rs.getString("size_label"));
                psObj.setStockQuantity(rs.getInt("stock_quantity"));
                psObj.setSkuCode(rs.getString("sku_code"));
                psObj.setAvailable(rs.getBoolean("is_available"));
                return psObj;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addProductSize(ProductSize psObj) {
        String query = "INSERT INTO product_sizes (product_id, size_label, stock_quantity, sku_code, is_available) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, psObj.getProductId());
            ps.setString(2, psObj.getSizeLabel());
            ps.setInt(3, psObj.getStockQuantity());
            ps.setString(4, psObj.getSkuCode());
            ps.setBoolean(5, psObj.isAvailable());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateStock(int productSizeId, int quantity) {
        String query = "UPDATE product_sizes SET stock_quantity=? WHERE product_size_id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, productSizeId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteProductSize(int id) {
        String query = "DELETE FROM product_sizes WHERE product_size_id=?";

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