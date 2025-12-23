/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;
import Dto.IngredientsDto;
import Dto.ItemDto;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ItemModel extends BaseModel<ItemDto> {

    @Override
    public boolean save(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "INSERT INTO item VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getCategory());
        pstm.setDouble(4, dto.getPrice());
        
        boolean flag = pstm.executeUpdate() > 0;
        return flag;
    }

    @Override
    public boolean update(ItemDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "UPDATE item SET itemCategory = ? ,itemName = ? ,itemPrice = ?  WHERE itemId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCategory());
        pstm.setString(2, dto.getName());
        pstm.setDouble(3, dto.getPrice());
        pstm.setString(4, dto.getId());
        

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItemDto search(String id) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM item WHERE itemId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        ItemDto cusDto = null;

        if (resultSet.next()) {
            cusDto = new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
                    
            );
        }
        return cusDto;
    }

    @Override
    public List<ItemDto> getAll() throws SQLException {
         Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM item";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<ItemDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new ItemDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)
                            
                    )
            );
        }
        return dtoList;
    }
    
    public static String getGenerateItemId() throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "SELECT itemId  FROM item ORDER BY itemId  DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitCuctomerId(resultSet.getString(1));
        }
        return splitCuctomerId(null);
    }

    private static String splitCuctomerId(String currentEmployeeId) {
        if (currentEmployeeId != null) {
            String[] split = currentEmployeeId.split("It0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "It00" + id;
        } else {
            return "It001";
        }
    }

     public static List<String> getCmbItemId() throws SQLException {
        Connection connection = null;
        List<String> userIds = new ArrayList<>();
        String query = "SELECT itemId FROM item";

        try {
            connection = DbConnection.getInstance();
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                userIds.add(resultSet.getString("itemId"));
            }
        } finally {

            if (connection != null) {
                // connection.close();
            }
        }

        return userIds;
    }

}
