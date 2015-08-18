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
            String sql = "SELECT ID, USN, EMAIL, PHONE, ADDRESS, STATUS "
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
}
