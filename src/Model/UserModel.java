/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
//import com.blendbuddy.db.DbConnection

import Db.DbConnection;
import Dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class UserModel {

    public void getAllUsers() {
        try {
            Connection conn = DbConnection.getInstance();
            String sql = "SELECT * FROM user";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("userId") + " - " + rs.getString("userName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean saveUser(UserDto dto) throws SQLException {
        try {
            Connection conn = DbConnection.getInstance();
            String sql = "INSERT INTO user (userName, password, email) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, dto.getUserName());
            ps.setString(2, dto.getPassword());
            ps.setString(3, dto.getEmail());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return false; // insertion failed
            }

            // Get the auto-generated userId
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    dto.setUserId(userId);
                    System.out.println("Inserted userId = " + userId);

                    // Optional: store in DTO if needed
                    dto.setUserId(userId);
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkLogin(String userName, String password) {
        try {
            Connection conn = DbConnection.getInstance();
            String sql = "SELECT * FROM user WHERE userName = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);

            return ps.executeQuery().next(); // true if user exists
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
