/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;
import Dto.CustomerDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class CustomerModel {

    public static boolean saveCustomer(CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCusId());
        pstm.setString(2, dto.getCusName());
        pstm.setString(3, dto.getConNum());
        pstm.setInt(4, dto.getUserId());
        pstm.setString(5, dto.getEmail());
        boolean flag = pstm.executeUpdate() > 0;
        return flag;
    }

    public static List<String> getCmbUserId() throws SQLException {
        Connection connection = null;
        List<String> userIds = new ArrayList<>();
        String query = "SELECT userId FROM user";

        try {
            connection = DbConnection.getInstance();
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                userIds.add(resultSet.getString("userId"));
            }
        } finally {

            if (connection != null) {
                // connection.close();
            }
        }

        return userIds;
    }

    public static String getGenerateEmployeeId() throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "SELECT customerId  FROM customer ORDER BY customerId  DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitCuctomerId(resultSet.getString(1));
        }
        return splitCuctomerId(null);
    }

    private static String splitCuctomerId(String currentEmployeeId) {
        if (currentEmployeeId != null) {
            String[] split = currentEmployeeId.split("C0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "C00" + id;
        } else {
            return "C001";
        }
    }

    public ArrayList<CustomerDto> getAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM customer";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<CustomerDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new CustomerDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }

    public static boolean updateCustomer(CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "UPDATE customer SET customerName = ? ,customerContact = ? ,userId = ? ,email  = ? WHERE customerId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCusName());
        pstm.setString(2, dto.getConNum());
        pstm.setInt(3, dto.getUserId());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getCusId());

        return pstm.executeUpdate() > 0;

    }

    public static CustomerDto searchCustomerId(String cusId) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM customer WHERE customerId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cusId);
        ResultSet resultSet = pstm.executeQuery();

        CustomerDto cusDto = null;

        if (resultSet.next()) {
            cusDto = new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );
        }
        return cusDto;
    }

    public static List<String> getCmbCustomerId() throws SQLException {
        Connection connection = null;
        List<String> userIds = new ArrayList<>();
        String query = "SELECT customerId FROM customer";

        try {
            connection = DbConnection.getInstance();
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                userIds.add(resultSet.getString("customerId"));
            }
        } finally {

            if (connection != null) {
                // connection.close();
            }
        }

        return userIds;
    }

    public static boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "DELETE FROM customer WHERE customerId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;
    }

}
