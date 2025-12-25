/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tm;

import Dto.SupplierDto;

/**
 *
 * @author user
 */
public class SupplierTm extends SupplierDto {

    @Override
    public String toString() {
        return "SupplierTm{" + '}';
    }
    
    public SupplierTm(String id, String name, String conNum, String address) {
        super(id, name, conNum, address);
    }
    
}
