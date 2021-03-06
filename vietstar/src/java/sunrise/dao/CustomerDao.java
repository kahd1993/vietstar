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
import sunrise.entity.Customers;

/**
 *
 * @author Do Quoc Hoa
 */
public class CustomerDao extends BaseDao{
    public List<Customers> getAllCustomer(){
        List<Customers> lstCus = null;
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        try{
            sql = "     Select id, code, name, address, phone, fax, email, row_numer() over(order by id desc) R"
                    + " from customer"
                    + " where 1 = 1";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Customers customer = null;
            while(rs.next()){
                if(lstCus==null) lstCus = new ArrayList<>();
                customer = new Customers();
                int i = 1;
                customer.setId(rs.getInt(i++));
                customer.setCode(rs.getString(i++));
                customer.setName(rs.getString(i++));
                customer.setAddress(rs.getString(i++));
                customer.setPhone(rs.getString(i++));
                customer.setFax(rs.getString(i++));
                customer.setEmail(rs.getString(i++));
                customer.setNumrow(rs.getInt(i++));
                lstCus.add(customer);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return lstCus;
    }
    
    public Customers getRowById(int id){
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Customers customer = null;
        String sql = "";
        try{
            sql = "     Select id, code, name, address, phone, fax, email"
                    + " from customer"
                    + " where id = ?";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                customer = new Customers();
                int i = 1;
                customer.setId(rs.getInt(i++));
                customer.setCode(rs.getString(i++));
                customer.setName(rs.getString(i++));
                customer.setAddress(rs.getString(i++));
                customer.setPhone(rs.getString(i++));
                customer.setFax(rs.getString(i++));
                customer.setEmail(rs.getString(i++));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return customer;
    }
    
    public boolean addCustomer(Customers customer){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "Insert into customer(code,name,address,phone,fax,email) values(?,?,?,?,?,?)";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, customer.getCode());
            pstm.setString(i++, customer.getName());
            pstm.setString(i++, customer.getAddress());
            pstm.setString(i++, customer.getPhone());
            pstm.setString(i++, customer.getFax());
            pstm.setString(i++, customer.getEmail());
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
    
    public boolean editCustomer(Customers customer){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "     Update customer set code = ?, name = ?, address = ?, phone = ?, fax = ?, email = ?"
                    + " where id = ?";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, customer.getCode());
            pstm.setString(i++, customer.getName());
            pstm.setString(i++, customer.getAddress());
            pstm.setString(i++, customer.getPhone());
            pstm.setString(i++, customer.getFax());
            pstm.setString(i++, customer.getEmail());
            pstm.setInt(i++, customer.getId());
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
    
    public boolean deleteCustomer(int id){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "Delete from customer where id = ?";
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
