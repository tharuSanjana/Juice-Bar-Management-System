/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 */
public abstract  class BaseModel<T> {
    public  abstract boolean save(T dto) throws SQLException;
    public abstract boolean update(T dto) throws SQLException;
    public abstract boolean delete(String id) throws SQLException;
    public  abstract T search(String id) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
}
