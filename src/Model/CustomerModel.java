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
    pstm.setString(5,dto.getEmail());
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
        if(resultSet.next()) {
            return splitCuctomerId(resultSet.getString(1));
        }
        return splitCuctomerId(null);
    }
     
     private static String splitCuctomerId(String currentEmployeeId) {
        if(currentEmployeeId!= null) {
            String[] split = currentEmployeeId.split("E0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "C00" + id;
        } else {
            return "C001";
        }
    }

}
