/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tm;

import Dto.CustomerDto;

/**
 *
 * @author user
 */
public class CustomerTm extends CustomerDto {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CustomerTm{");
        sb.append('}');
        return sb.toString();
    }

    public CustomerTm(String cusId, String cusName, String conNum, int userId, String email) {
        super(cusId, cusName, conNum, userId, email);
    }

}
