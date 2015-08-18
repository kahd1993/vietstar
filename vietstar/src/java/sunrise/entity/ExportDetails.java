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
public class ExportDetails {
    private int id_export;
    private String ecode;
    private int id_product;
    private String pname;
    private BigDecimal pcost;
    private int quality;
    private BigDecimal price;
    private int row;

    public ExportDetails() {
    }

    public int getId_export() {
        return id_export;
    }

    public void setId_export(int id_export) {
        this.id_export = id_export;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public BigDecimal getPcost() {
        return pcost;
    }

    public void setPcost(BigDecimal pcost) {
        this.pcost = pcost;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
}
