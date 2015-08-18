/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.entity;

import java.math.BigDecimal;

/**
 *
 * @author Do Quoc Hoa
 */
public class Payments {
    private int id;
    private int idLiabilities;
    private String date;
    private BigDecimal money;
    private int numrow;

    public Payments() {
    }

    public int getNumrow() {
        return numrow;
    }

    public void setNumrow(int numrow) {
        this.numrow = numrow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLiabilities() {
        return idLiabilities;
    }

    public void setIdLiabilities(int idLiabilities) {
        this.idLiabilities = idLiabilities;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    
}