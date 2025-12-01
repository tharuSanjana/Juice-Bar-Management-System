/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dto;

/**
 *
 * @author user
 */
public class CustomerDto {

    private String cusId;
    private String cusName;
    private String conNum;
    private int userId;
    private String email;

    public CustomerDto(String cusId, String cusName, String conNum, int userId, String email) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.conNum = conNum;
        this.userId = userId;
        this.email = email;
    }

    /**
     * @return the cusId
     */
    public String getCusId() {
        return cusId;
    }

    /**
     * @param cusId the cusId to set
     */
    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    /**
     * @return the cusName
     */
    public String getCusName() {
        return cusName;
    }

    /**
     * @param cusName the cusName to set
     */
    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     * @return the conNum
     */
    public String getConNum() {
        return conNum;
    }

    /**
     * @param conNum the conNum to set
     */
    public void setConNum(String conNum) {
        this.conNum = conNum;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
