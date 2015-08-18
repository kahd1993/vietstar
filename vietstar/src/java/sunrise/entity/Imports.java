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
public class Imports {
    private int id;
    private String code;
    private int idStore;
    private int idSupplier;
    private String date;
    private BigDecimal totalAmount;
    private int numrow;

    public Imports() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
}
