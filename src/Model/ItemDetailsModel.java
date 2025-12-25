/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;
import Dto.ItemDetailsDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class ItemDetailsModel extends BaseModel<ItemDetailsDto> {

    @Override
    public boolean save(ItemDetailsDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "INSERT INTO itemDetails VALUES(?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getItemId());
        pstm.setString(2, dto.getIngId());

        boolean flag = pstm.executeUpdate() > 0;
        return flag;
    }

    @Override
    public boolean update(ItemDetailsDto dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItemDetailsDto search(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemDetailsDto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT i.itemId,i.itemName,ing.ingredientId,ing.ingredientName  FROM itemDetails d JOIN item i ON d.itemId = i.itemId JOIN ingredient ing ON d.ingredientId = ing.ingredientId";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<ItemDetailsDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new ItemDetailsDto(
                            resultSet.getString("itemId"),
                            resultSet.getString("itemName"),
                            resultSet.getString("ingredientId"),
                            resultSet.getString("ingredientName")
                    )
            );
        }
        return dtoList;
    }

}
