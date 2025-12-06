/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Db.DbConnection;
import Dto.EmployeeDto;
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
public class EmployeeModel extends BaseModel<EmployeeDto> {

    @Override
    public boolean save(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "INSERT INTO employee VALUES(?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmpId());
        pstm.setString(2, dto.getEmpName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getConNum());
        pstm.setInt(5, dto.getUserId());
        boolean flag = pstm.executeUpdate() > 0;
        return flag;
    }

    @Override
    public boolean update(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance();

        String sql = "UPDATE employee SET employeeName  = ? ,employeeAddress  = ? ,employeeContact  = ? ,userId   = ? WHERE employeeId  = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmpName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getConNum());
        pstm.setInt(4, dto.getUserId());
        pstm.setString(5, dto.getEmpId());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
          Connection connection = DbConnection.getInstance();
        String sql = "DELETE FROM employee WHERE employeeId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;
    }

    @Override
    public EmployeeDto search(String id) throws SQLException {
         Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM employee WHERE employeeId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto empDto = null;

        if (resultSet.next()) {
            empDto = new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
        }
        return empDto;
    }

    @Override
    public List<EmployeeDto> getAll() throws SQLException {
        Connection connection = DbConnection.getInstance();
        String sql = "SELECT * FROM employee";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ArrayList<EmployeeDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new EmployeeDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    )
            );
        }
        return dtoList;
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

        String sql = "SELECT employeeId  FROM employee ORDER BY employeeId  DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return splitEmployeeId(resultSet.getString(1));
        }
        return splitEmployeeId(null);
    }

    private static String splitEmployeeId(String currentEmployeeId) {
        if (currentEmployeeId != null) {
            String[] split = currentEmployeeId.split("E0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "E00" + id;
        } else {
            return "E001";
        }
    }

    public static List<String> getCmbEmployeeId() throws SQLException {
        Connection connection = null;
        List<String> empIds = new ArrayList<>();
        String query = "SELECT employeeId FROM employee";

        try {
            connection = DbConnection.getInstance();
            PreparedStatement pstm = connection.prepareStatement(query);
            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                empIds .add(resultSet.getString("employeeId"));
            }
        } finally {

            if (connection != null) {
                // connection.close();
            }
        }

        return empIds ;
    }

   

}
