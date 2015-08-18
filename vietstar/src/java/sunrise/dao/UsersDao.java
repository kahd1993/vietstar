/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.dao;

import inet.util.Md5;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sunrise.constant.Constants;
import static sunrise.constant.Constants.ACTIVE;
import static sunrise.constant.Constants.DEACTIVE;
import static sunrise.constant.Constants.IMG_ACTIVE;
import static sunrise.constant.Constants.IMG_DEACTIVE;
import sunrise.entity.Users;

/**
 *
 * @author zoncn
 */
public class UsersDao extends BaseDao{
    public Users login(String usn, String pwd){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users login = null;
        try{
            String sql = "SELECT ID, USN, NAME, EMAIL, PHONE, ADDRESS, STATUS "
                    + "   FROM [USER] WHERE USN = ? AND PWD = ?";
            conn = getConnection();
            String pwdMd5 = Md5.Hash(pwd);
            ps = conn.prepareStatement(sql);
            ps.setString(1, usn);
            ps.setString(2, pwdMd5);
            rs = ps.executeQuery();
            if(rs.next()){
                int i = 1;
                login = new Users();
                login.setId(rs.getInt(i++));
                login.setUsn(rs.getString(i++));
                login.setName(rs.getString(i++));
                login.setEmail(rs.getString(i++));
                login.setPhone(rs.getString(i++));
                login.setAddress(rs.getString(i++));
                login.setStatus(rs.getInt(i++));
            }
            return login;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps, rs);
        }
        return null;
    }
    
    public boolean insert(Users is){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "INSERT INTO [USER](USN, PWD, NAME, EMAIL, PHONE, ADDRESS, STATUS) "
                    + "   VALUES(?, ?, ?, ?, ?)";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, is.getUsn());
            ps.setString(i++, Md5.Hash(is.getPwd()));
            ps.setString(i++, is.getName());
            ps.setString(i++, is.getEmail());
            ps.setString(i++, is.getPhone());
            ps.setString(i++, is.getAddress());
            ps.setInt(i++, DEACTIVE);
            
            return ps.executeUpdate() == 1;
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps);
        }
        return false;
    }
    
    public boolean update(Users ud){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE [USER] SET NAME = ?, EMAIL = ?, PHONE = ? ADDRESS = ?"
                    + "   WHERE ID = ?";
            
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, ud.getName());
            ps.setString(i++, ud.getEmail());
            ps.setString(i++, ud.getPhone());
            ps.setString(i++, ud.getAddress());
            ps.setInt(i++, ud.getId());
            
            return ps.executeUpdate() == 1;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean lockUser(int id){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE [USER] SET STATUS = ?"
                    + "   WHERE ID = ?";
            
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, DEACTIVE);
            ps.setInt(i++, id);
            
            return ps.executeUpdate() == 1;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public boolean unlockUser(int id){
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            String sql = "UPDATE [USER] SET STATUS = ?"
                    + "   WHERE ID = ?";
            
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, ACTIVE);
            ps.setInt(i++, id);
            
            return ps.executeUpdate() == 1;
            
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps);
        }
        
        return false;
    }
    
    public List<Users> getListAllUsers(int id,String s){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Users> result = null;
        try{
            String sql = "SELECT * FROM(SELECT ID, USN, NAME, EMAIL, PHONE, ADDRESS, STATUS,"
                    + "   ROW_NUMBER() OVER(ORDER BY ID DESC) R"
                    + "   FROM [USER] WHERE 1 = 1 AND ID != ?";
            if (s != null && !"".equals(s)) {
                sql += " AND (UPPER(USN) LIKE '%"+s+"%' OR UPPER(NAME) LIKE '%"+s+"%'"
                    + "  OR UPPER(EMAIL) LIKE '%"+s+"%' OR UPPER(PHONE) LIKE '%"+s+"%'"
                    + "  OR UPPER(ADDRESS) LIKE '%"+s+"%')";
            }
            sql += " )a  WHERE R >= ? AND R <= ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int count = 1;
            ps.setInt(count++, id);
            rs = ps.executeQuery();
            Users us = null;
            while(rs.next()){
                int i = 1;
                if(result == null) result = new ArrayList<>();
                us = new Users();
                us.setId(rs.getInt(i++));
                us.setUsn(rs.getString(i++));
                us.setName(rs.getString(i++));
                us.setEmail(rs.getString(i++));
                us.setPhone(rs.getString(i++));
                us.setAddress(rs.getString(i++));
                us.setStatus(rs.getInt(i++));
                if(us.getStatus() == DEACTIVE){
                    us.setImg(IMG_DEACTIVE);
                    us.setAction(false);
                }else if(us.getStatus() == ACTIVE){
                    us.setImg(IMG_ACTIVE);
                    us.setAction(true);
                }
                us.setRow(rs.getInt(i++));
                result.add(us);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps, rs);
        }
        return result;
    }
    
    public Users getRowById(int id){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "     SELECT ID, USN, NAME, EMAIL, PHONE, ADDRESS "
                    + "        FROM [USER] WHERE ID=?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Users us = null;
            int i = 1;
            while (rs.next()) {

                us = new Users();
                us.setId(rs.getInt(i++));
                us.setUsn(rs.getString(i++));
                us.setName(rs.getString(i++));
                us.setEmail(rs.getString(i++));
                us.setPhone(rs.getString(i++));
                us.setAddress(rs.getString(i++));
            }
            return us;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(conn, ps, rs);
        }
        return null;
    }
}
