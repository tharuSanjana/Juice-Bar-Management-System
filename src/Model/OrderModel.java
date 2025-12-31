/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;
import Dto.OrderDto;
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
public class OrderModel extends BaseModel<OrderDto> {

    @Override
    public boolean save(OrderDto dto) throws SQLException {
          Connection connection = DbConnection.getInstance();

    String sql = "INSERT INTO orders (orderId, orderDate , time, qty, netTotal , customerId) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement pstm = connection.prepareStatement(sql);

    pstm.setString(1, dto.getOrderId());
    pstm.setString(2, dto.getDate());
    pstm.setString(3, dto.getTime());
    pstm.setInt(4, dto.getQty());
    pstm.setDouble(5, dto.getNetTotal());
    pstm.setString(6, dto.getCustomerId());

    boolean flag = pstm.executeUpdate() > 0;
    return flag;
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OrderDto search(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OrderDto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM orders";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<OrderDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new OrderDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getDouble(5),
                            resultSet.getString(6)
                    )
            );
        }
        return dtoList;
    }

    public static String getGenerateOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "SELECT orderId  FROM orders ORDER BY orderId  DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }

    public static List<String> getCmbCustomerId() throws SQLException {
        Connection connection = null;
        List<String> cuatomerIds = new ArrayList<>();
        String query = "SELECT customerId  FROM customer";

        try {
            connection = DbConnection.getInstance();
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                cuatomerIds.add(resultSet.getString("customerId"));
            }
        } finally {

            if (connection != null) {
                // connection.close();
            }
        }

        return cuatomerIds;
    }
    
    public static String  setItemName(String itemId){
   String itemName = null;

    try {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT itemName FROM item WHERE itemId = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, itemId);

        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            itemName = rst.getString("itemName");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return itemName;
    }
    
    public static double getItemPrice(String itemId) {

    double price = 0;

    try {
        Connection con = DbConnection.getInstance();
        String sql = "SELECT itemPrice  FROM item WHERE itemId = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, itemId);

        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            price = rst.getDouble("itemPrice");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return price;
}

}
