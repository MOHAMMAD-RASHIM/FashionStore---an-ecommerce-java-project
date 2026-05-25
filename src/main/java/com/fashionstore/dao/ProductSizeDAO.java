package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.ProductSize;

public interface ProductSizeDAO {

    // Get sizes by product ID
    List<ProductSize> getSizesByProductId(int productId);

    // Get specific size
    ProductSize getProductSize(int productId, String sizeLabel);

    // Add size
    boolean addProductSize(ProductSize productSize);

    // Update stock
    boolean updateStock(int productSizeId, int quantity);

    // Delete size
    boolean deleteProductSize(int productSizeId);
}