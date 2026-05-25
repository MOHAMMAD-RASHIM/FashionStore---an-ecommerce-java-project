package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.Category;

public interface CategoryDAO {

    // Get all active categories
    List<Category> getAllCategories();

    // Get category by ID
    Category getCategoryById(int categoryId);

    // Add new category
    boolean addCategory(Category category);

    // Update category
    boolean updateCategory(Category category);

    // Delete category
    boolean deleteCategory(int categoryId);
}