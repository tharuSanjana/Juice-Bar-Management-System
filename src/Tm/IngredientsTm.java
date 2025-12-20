/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tm;

import Dto.IngredientsDto;

/**
 *
 * @author user
 */
public class IngredientsTm extends IngredientsDto {

    @Override
    public String toString() {
        return "IngredientsTm{" + '}';
    }
    
    public IngredientsTm(String id, String name, int qty) {
        super(id, name, qty);
    }
    
}
