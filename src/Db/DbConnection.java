package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author user
 */
public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/BlendBuddy";
    private static final String USER = "root";
    private static final String PASSWORD = "Ijse@1234";
    private static Connection connection;

    // Private constructor to prevent instantiation
    private DbConnection() {
    }

    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load MySQL JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully!");

            } catch (ClassNotFoundException e) {
                System.out.println(" MySQL Driver not found!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
