/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;
import Dto.CustomerDto;
import Dto.SupplierDto;
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
public class SupplierModel extends BaseModel<SupplierDto> {

    @Override
    public boolean save(SupplierDto dto) throws SQLException {
         Connection connection = DbConnection.getInstance();

        String sql = "INSERT INTO supplier VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getConNum());
        
        
        boolean flag = pstm.executeUpdate() > 0;
        return flag;
    }

    @Override
    public boolean update(SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "UPDATE supplier SET supplierName  = ? ,supplierAddress  = ? ,supplierContact  = ?  WHERE supplierId  = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getConNum());
        pstm.setString(4, dto.getId());
        

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SupplierDto search(String id) throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM supplier WHERE supplierId  = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        SupplierDto supDto = null;

        if (resultSet.next()) {
            supDto = new SupplierDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return supDto;
    }

    @Override
    public List<SupplierDto> getAll() throws SQLException {
         Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM supplier";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<SupplierDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new SupplierDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;
    }
    
    public static String getGenerateSupplierId() throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "SELECT supplierId   FROM supplier ORDER BY supplierId  DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitCuctomerId(resultSet.getString(1));
        }
        return splitCuctomerId(null);
    }

    private static String splitCuctomerId(String currentEmployeeId) {
        if (currentEmployeeId != null) {
            String[] split = currentEmployeeId.split("S0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "S00" + id;
        } else {
            return "S001";
        }
    }
    
    public static List<String> getCmbSupplierId() throws SQLException {
        Connection connection = null;
        List<String> userIds = new ArrayList<>();
        String query = "SELECT supplierId  FROM supplier";

        try {
            connection = DbConnection.getInstance();
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                userIds.add(resultSet.getString("supplierId"));
            }
        } finally {

            if (connection != null) {
                // connection.close();
            }
        }

        return userIds;
    }
    
}
