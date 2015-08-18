/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.entity;

/**
 *
 * @author zoncn
 */
public class LiabilityDetails {
    private int id_liabilities;
    private int id_export;
    private int row;

    public LiabilityDetails() {
    }

    public int getId_liabilities() {
        return id_liabilities;
    }

    public void setId_liabilities(int id_liabilities) {
        this.id_liabilities = id_liabilities;
    }

    public int getId_export() {
        return id_export;
    }

    public void setId_export(int id_export) {
        this.id_export = id_export;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
}
