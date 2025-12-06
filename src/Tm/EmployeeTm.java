/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tm;

import Dto.EmployeeDto;

/**
 *
 * @author user
 */
public class EmployeeTm extends EmployeeDto{
    
    public EmployeeTm(String empId, String empName, String conNum, String address, int userId) {
        super(empId, empName, conNum, address, userId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmployeeTm{");
        sb.append('}');
        return sb.toString();
    }
    
}
