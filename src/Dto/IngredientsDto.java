/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author user
 */
public class IngredientsDto {
    private String id;
    private String name;
    private int qty;

    public IngredientsDto(String id, String name, int qty) {
        this.id = id;
        this.name = name;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
}
