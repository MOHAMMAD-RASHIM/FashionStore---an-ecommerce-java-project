package com.fashionstore.dao;

import java.util.List;
import com.fashionstore.model.User;

public interface UserDAO {

    // ================= AUTH =================

    // Register new user
    boolean registerUser(User user);

    // Login user
    User loginUser(String email, String password);

    // Check if email already exists
    boolean isEmailExists(String email);


    // ================= FETCH =================

    // Get user by ID
    User getUserById(int userId);

    // Get user by email
    User getUserByEmail(String email);

    // Get all users (admin / future use)
    List<User> getAllUsers();


    // ================= UPDATE =================

    // Update full user profile
    boolean updateUser(User user);

    // Update only password
    boolean updatePassword(int userId, String newPassword);


    // ================= DELETE =================

    // Delete user
    boolean deleteUser(int userId);


    // ================= EXTRA (OPTIONAL BUT USEFUL) =================

    // Count total users
    int getUserCount();
}