/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author user
 */
public class ItemDetailsDto {

    private String itemId;
    private String itemName;
    private String ingId;
    private String ingName;

    public ItemDetailsDto(String itemId, String itemName, String ingId, String ingName) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.ingId = ingId;
        this.ingName = ingName;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getIngId() {
        return ingId;
    }

    public String getIngName() {
        return ingName;
    }

    public ItemDetailsDto(String itemId, String ingId) {
        this.itemId = itemId;
        this.ingId = ingId;
    }
}
