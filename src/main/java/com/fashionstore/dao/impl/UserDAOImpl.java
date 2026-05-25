package com.fashionstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fashionstore.dao.UserDAO;
import com.fashionstore.model.User;
import com.fashionstore.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    private Connection connection;

    public UserDAOImpl() {
        connection = DBConnection.getConnection();
    }

    // ================= REGISTER =================
    @Override
    public boolean registerUser(User user) {

        String query = "INSERT INTO users (full_name, email, phone, password, address) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAddress());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= LOGIN =================
    @Override
    public User loginUser(String email, String password) {

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= CHECK EMAIL =================
    @Override
    public boolean isEmailExists(String email) {
        // Also consider checking phone since it is UNIQUE in your DB
        String query = "SELECT user_id FROM users WHERE email = ? OR phone = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, email); // Assuming you might check phone elsewhere, but for registration safety, check both.
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= GET USER BY ID =================
    @Override
    public User getUserById(int userId) {

        String query = "SELECT * FROM users WHERE user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET USER BY EMAIL =================
    @Override
    public User getUserByEmail(String email) {

        String query = "SELECT * FROM users WHERE email = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ================= GET ALL USERS =================
    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // ================= UPDATE USER =================
    @Override
    public boolean updateUser(User user) {

        String query = "UPDATE users SET full_name = ?, phone = ?, address = ? WHERE user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getAddress());
            ps.setInt(4, user.getUserId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= UPDATE PASSWORD =================
    @Override
    public boolean updatePassword(int userId, String newPassword) {

        String query = "UPDATE users SET password = ? WHERE user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, newPassword);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= DELETE USER =================
    @Override
    public boolean deleteUser(int userId) {

        String query = "DELETE FROM users WHERE user_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= COUNT USERS =================
    @Override
    public int getUserCount() {

        String query = "SELECT COUNT(*) FROM users";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}