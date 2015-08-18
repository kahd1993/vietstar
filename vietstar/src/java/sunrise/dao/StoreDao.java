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
import sunrise.entity.Stores;

/**
 *
 * @author Do Quoc Hoa
 */
public class StoreDao extends BaseDao{
    public List<Stores> getAllCustomer(){
        List<Stores> lstStores = null;
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        try{
            sql = "     Select id, code, name, address, row_numer() over(order by id desc) R"
                    + " from store"
                    + " where 1 = 1";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Stores store = null;
            while(rs.next()){
                if(lstStores==null) lstStores = new ArrayList<>();
                store = new Stores();
                int i = 1;
                store.setId(rs.getInt(i++));
                store.setCode(rs.getString(i++));
                store.setName(rs.getString(i++));
                store.setAddress(rs.getString(i++));
                store.setNumrow(rs.getInt(i++));
                lstStores.add(store);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return lstStores;
    }
    
    public Stores getRowById(int id){
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Stores store = null;
        String sql = "";
        try{
            sql = "     Select id, code, name, address"
                    + " from store"
                    + " where id = ?";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                store = new Stores();
                int i = 1;
                store.setId(rs.getInt(i++));
                store.setCode(rs.getString(i++));
                store.setName(rs.getString(i++));
                store.setAddress(rs.getString(i++));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return store;
    }
    
    public boolean addStore(Stores store){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "Insert into store(code,name,address) values(?,?,?)";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, store.getCode());
            pstm.setString(i++, store.getName());
            pstm.setString(i++, store.getAddress());
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
    
    public boolean editStore(Stores store){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "     Update store set code = ?, name = ?, address = ?"
                    + " where id = ?";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, store.getCode());
            pstm.setString(i++, store.getName());
            pstm.setString(i++, store.getAddress());
            pstm.setInt(i++, store.getId());
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
    
    public boolean deleteStore(int id){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "Delete from store where id = ?";
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
