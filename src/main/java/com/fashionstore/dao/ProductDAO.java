package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.Product;

public interface ProductDAO {

    // Get all products
    List<Product> getAllProducts();

    // Get product by ID
    Product getProductById(int productId);

    // Get products by category
    List<Product> getProductsByCategory(int categoryId);

    // Search products
    List<Product> searchProducts(String keyword);

    // Filter by price range
    List<Product> getProductsByPriceRange(double minPrice, double maxPrice);

    // Add product
    boolean addProduct(Product product);

    // Update product
    boolean updateProduct(Product product);

    // Delete product
    boolean deleteProduct(int productId);
}