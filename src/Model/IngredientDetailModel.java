/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;

import Dto.IngredientDetailDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class IngredientDetailModel extends BaseModel<IngredientDetailDto>{

    @Override
    public boolean save(IngredientDetailDto dto) throws SQLException {
        java.sql.Connection connection = DbConnection.getInstance();

        String sql = "INSERT INTO ingredientsDetail VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSupId());
        pstm.setString(2, dto.getIngId());
        pstm.setString(3, dto.getDate());
        pstm.setDouble(4, dto.getQty());
       
        boolean flag = pstm.executeUpdate() > 0;
        return flag;
    }

    @Override
    public boolean update(IngredientDetailDto dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IngredientDetailDto search(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<IngredientDetailDto> getAll() throws SQLException {
       java.sql.Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM ingredientsDetail";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<IngredientDetailDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new IngredientDetailDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return dtoList;
    }
    
}
