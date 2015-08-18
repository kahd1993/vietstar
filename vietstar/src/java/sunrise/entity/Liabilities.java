/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.entity;

import java.math.BigDecimal;

/**
 *
 * @author zoncn
 */
public class Liabilities {
    private int id;
    private String date;
    private BigDecimal total_amount;
    private BigDecimal total_liability;
    private int number_day;
    private int status;
    private int row;

    public Liabilities() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }

    public BigDecimal getTotal_liability() {
        return total_liability;
    }

    public void setTotal_liability(BigDecimal total_liability) {
        this.total_liability = total_liability;
    }

    public int getNumber_day() {
        return number_day;
    }

    public void setNumber_day(int number_day) {
        this.number_day = number_day;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
}
