/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tm;

import Dto.OrderDto;

/**
 *
 * @author user
 */
public class OrderTm extends OrderDto{
 private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderTm{" + "total=" + total + '}';
    }

    public OrderTm(String orderId, String date, String time, int qty, double netTotal, String customerId) {
        super(orderId, date, time, qty, netTotal, customerId);
    }

   

   
    
    
    
}
