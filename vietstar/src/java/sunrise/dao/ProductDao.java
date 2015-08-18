/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sunrise.entity.Products;

/**
 *
 * @author zoncn
 */
public class ProductDao extends BaseDao{
    public boolean insert(Products pd){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO PRODUCT(CODE, NAME, UNIT, QUALITY, ID_STORE, PURCHASE_PRICE, SELL_PRICE) "
                    + "   VALUES(?, ?, ?, ?, ?, ?, ?)";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, pd.getCode());
            ps.setString(i++, pd.getName());
            ps.setString(i++, pd.getUnit());
            ps.setInt(i++, pd.getQuality());
            ps.setInt(i++, pd.getId_store());
            ps.setBigDecimal(i++, pd.getPurchase_price());
            ps.setBigDecimal(i++, pd.getSell_price());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps);
        }
        return false;
    }
    
    public boolean update(Products pd){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE PRODUCT SET CODE = ?, NAME = ?, UNIT = ?, QUALITY = ?, "
                    + "   ID_STORE = ?, PURCHASE_PRICE = ?, SELL_PRICE = ? "
                    + "   WHERE ID = ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, pd.getCode());
            ps.setString(i++, pd.getName());
            ps.setString(i++, pd.getUnit());
            ps.setInt(i++, pd.getQuality());
            ps.setInt(i++, pd.getId_store());
            ps.setBigDecimal(i++, pd.getPurchase_price());
            ps.setBigDecimal(i++, pd.getSell_price());
            ps.setInt(i++, pd.getId());
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps);
        }
        return false;
    }
    
    public boolean remove(int id){
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = null;
        try {
            sql = "DELETE FROM PRODUCT WHERE ID = ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(conn, ps);
        }
        return false;
    }
    
    public List<Products> getListAllProducts(String s){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Products> result = null;
        try{
            String sql = "SELECT * FROM(SELECT A.ID, A.CODE, A.NAME, A.UNIT, A.QUALITY, B.NAME AS NSTORE, A.PURCHASE_PRICE, A.SELL_PRICE"
                    + "   ROW_NUMBER() OVER(ORDER BY A.ID DESC) R"
                    + "   FROM PRODUCT A JOIN STORE ON A.ID_STORE = B.ID WHERE 1 = 1 ";
            if (s != null && !"".equals(s)) {
                sql += " AND (UPPER(A.CODE) LIKE '%"+s+"%' OR UPPER(A.NAME) LIKE '%"+s+"%'"
                    + "  OR UPPER(A.UNIT) LIKE '%"+s+"%' OR UPPER(B.NAME) LIKE '%"+s+"%'"
                    + "  OR UPPER(A.PURCHASE_PRICE) LIKE '%"+s+"%' OR UPPER(A.SELL_PRICE) LIKE '%"+s+"%')";
            }
            sql += " )a  WHERE R >= ? AND R <= ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Products pd = null;
            while(rs.next()){
                int i = 1;
                if(result == null) result = new ArrayList<>();
                pd = new Products();
                pd.setId(rs.getInt(i++));
                pd.setCode(rs.getString(i++));
                pd.setName(rs.getString(i++));
                pd.setUnit(rs.getString(i++));
                pd.setQuality(rs.getInt(i++));
                pd.setStore_name(rs.getString(i++));
                pd.setPurchase_price(rs.getBigDecimal(i++));
                pd.setSell_price(rs.getBigDecimal(i++));
                pd.setRow(rs.getInt(i++));
                result.add(pd);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps, rs);
        }
        return result;
    }
    
    public Products getRowById(int id){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "     SELECT ID, CODE, NAME, UNIT, QUALITY, ID_STORE, PURCHASE_PRICE, SELL_PRICE "
                    + "        FROM PRODUCT WHERE ID=?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Products pd = null;
            int i = 1;
            while (rs.next()) {

                pd = new Products();
                pd.setId(rs.getInt(i++));
                pd.setCode(rs.getString(i++));
                pd.setName(rs.getString(i++));
                pd.setUnit(rs.getString(i++));
                pd.setQuality(rs.getInt(i++));
                pd.setId_store(rs.getInt(i++));
                pd.setPurchase_price(rs.getBigDecimal(i++));
                pd.setSell_price(rs.getBigDecimal(i++));
            }
            return pd;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(conn, ps, rs);
        }
        return null;
    }
}
