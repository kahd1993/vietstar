/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sunrise.entity.Suppliers;

/**
 *
 * @author Do Quoc Hoa
 */
public class SupplierDao extends BaseDao{
    public List<Suppliers> getAllSupplier(){
        List<Suppliers> lstSuppliers = null;
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        try{
            sql = "     Select id, code, name, address, phone, fax, email, row_numer() over(order by id desc) R"
                    + " from supplier"
                    + " where 1 = 1";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Suppliers supplier = null;
            while(rs.next()){
                if(lstSuppliers==null) lstSuppliers = new ArrayList<>();
                supplier = new Suppliers();
                int i = 1;
                supplier.setId(rs.getInt(i++));
                supplier.setCode(rs.getString(i++));
                supplier.setName(rs.getString(i++));
                supplier.setAddress(rs.getString(i++));
                supplier.setPhone(rs.getString(i++));
                supplier.setFax(rs.getString(i++));
                supplier.setEmail(rs.getString(i++));
                supplier.setNumrow(rs.getInt(i++));
                lstSuppliers.add(supplier);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return lstSuppliers;
    }
    
    public Suppliers getRowById(int id){
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Suppliers supplier = null;
        String sql = "";
        try{
            sql = "     Select id, code, name, address, phone, fax, email"
                    + " from supplier"
                    + " where id = ?";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                supplier = new Suppliers();
                int i = 1;
                supplier.setId(rs.getInt(i++));
                supplier.setCode(rs.getString(i++));
                supplier.setName(rs.getString(i++));
                supplier.setAddress(rs.getString(i++));
                supplier.setPhone(rs.getString(i++));
                supplier.setFax(rs.getString(i++));
                supplier.setEmail(rs.getString(i++));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return supplier;
    }
    
    public boolean addSupplier(Suppliers supplier){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "Insert into supplier(code,name,address,phone,fax,email) values(?,?,?,?,?,?)";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, supplier.getCode());
            pstm.setString(i++, supplier.getName());
            pstm.setString(i++, supplier.getAddress());
            pstm.setString(i++, supplier.getPhone());
            pstm.setString(i++, supplier.getFax());
            pstm.setString(i++, supplier.getEmail());
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
    
    public boolean editSupplier(Suppliers supplier){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "     Update supplier set code = ?, name = ?, address = ?, phone = ?, fax = ?, email = ?"
                    + " where id = ?";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, supplier.getCode());
            pstm.setString(i++, supplier.getName());
            pstm.setString(i++, supplier.getAddress());
            pstm.setString(i++, supplier.getPhone());
            pstm.setString(i++, supplier.getFax());
            pstm.setString(i++, supplier.getEmail());
            pstm.setInt(i++, supplier.getId());
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
    
    public boolean deleteSupplier(int id){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "Delete from supplier where id = ?";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
}
