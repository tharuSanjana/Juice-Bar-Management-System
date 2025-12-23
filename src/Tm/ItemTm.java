/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tm;

import Dto.ItemDto;

/**
 *
 * @author user
 */
public class ItemTm extends ItemDto{

    @Override
    public String toString() {
        return "ItemTm{" + '}';
    }
    
    public ItemTm(String id, String name, String category, double price) {
        super(id, name, category, price);
    }
    
}
