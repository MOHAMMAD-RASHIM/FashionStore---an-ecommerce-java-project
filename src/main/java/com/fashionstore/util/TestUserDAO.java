package com.fashionstore.util;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.dao.impl.UserDAOImpl;
import com.fashionstore.model.User;

public class TestUserDAO {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAOImpl();

        // ================= REGISTER TEST =================
        User user = new User();
        user.setFullName("Test User");
        user.setEmail("test@example.com");
        user.setPhone("9876543210");
        user.setPassword("1234");
        user.setAddress("Bangalore");

        boolean isRegistered = userDAO.registerUser(user);

        if (isRegistered) {
            System.out.println("User Registered Successfully!");
        } else {
            System.out.println("Registration Failed!");
        }

        // ================= LOGIN TEST =================
        User loggedInUser = userDAO.loginUser("test@example.com", "1234");

        if (loggedInUser != null) {
            System.out.println("Login Successful!");
            System.out.println("Welcome: " + loggedInUser.getFullName());
        } else {
            System.out.println("Login Failed!");
        }

        // ================= EMAIL CHECK TEST =================
        boolean exists = userDAO.isEmailExists("test@example.com");

        System.out.println("Email Exists: " + exists);
    }
}