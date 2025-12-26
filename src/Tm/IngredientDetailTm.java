/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tm;

import Dto.IngredientDetailDto;

/**
 *
 * @author user
 */
public class IngredientDetailTm extends IngredientDetailDto {

    @Override
    public String toString() {
        return "IngredientDetailTm{" + '}';
    }
    
    public IngredientDetailTm(String supId, String ingId, String date, double qty) {
        super(supId, ingId, date, qty);
    }
    
}
