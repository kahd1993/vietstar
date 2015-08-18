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
public class ImportDetails {
    private int idImport;
    private int idProduct;
    private int quality;
    private BigDecimal price;
    private int numrow;

    public ImportDetails() {
    }

    public int getIdImport() {
        return idImport;
    }

    public void setIdImport(int idImport) {
        this.idImport = idImport;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public int getNumrow() {
        return numrow;
    }

    public void setNumrow(int numrow) {
        this.numrow = numrow;
    }
    
}
