/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;
import Dto.IngredientsDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class IngredientsModel extends BaseModel<IngredientsDto> {

    @Override
    public boolean save(IngredientsDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "INSERT INTO ingredient VALUES(?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setInt(3, dto.getQty());

        boolean flag = pstm.executeUpdate() > 0;
        return flag;
    }

    @Override
    public boolean update(IngredientsDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "UPDATE ingredient SET ingredientName  = ? ,qtyOnHand  = ?  WHERE ingredientId  = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setInt(2, dto.getQty());
        pstm.setString(3, dto.getId());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "DELETE FROM ingredient WHERE ingredientId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;
    }

    @Override
    public IngredientsDto search(String id) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM ingredient WHERE ingredientId  = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        IngredientsDto ingDto = null;

        if (resultSet.next()) {
            ingDto = new IngredientsDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return ingDto;
    }

    @Override
    public List<IngredientsDto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM ingredient";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<IngredientsDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new IngredientsDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3)
                    )
            );
        }
        return dtoList;
    }

    public static List<String> getCmbIngredientsId() throws SQLException {
        Connection connection = null;
        List<String> ingIds = new ArrayList<>();
        String query = "SELECT ingredientId  FROM ingredient";

        try {
            connection = DbConnection.getInstance();
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                ingIds.add(resultSet.getString("ingredientId"));
            }
        } finally {

            if (connection != null) {
                // connection.close();
            }
        }

        return ingIds;
    }

    public static String getGenerateIngredientId() throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "SELECT ingredientId  FROM ingredient ORDER BY ingredientId  DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitIngredientId(resultSet.getString(1));
        }
        return splitIngredientId(null);
    }

    private static String splitIngredientId(String currentIngredientId) {
        if (currentIngredientId != null) {
            String[] split = currentIngredientId.split("I0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "I00" + id;
        } else {
            return "I001";
        }
    }

}
