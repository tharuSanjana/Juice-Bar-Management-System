/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
//import com.blendbuddy.db.DbConnection

import Db.DbConnection;
import Db.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class UserDao {

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
    
    public boolean saveUser(String userName, String password, String email) {
        try {
            Connection conn = DbConnection.getInstance();
            String sql = "INSERT INTO user (userName, password, email) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, email);

            int result = ps.executeUpdate(); // returns number of affected rows
            return result > 0; // true if inserted successfully

        } catch (Exception e) {
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
