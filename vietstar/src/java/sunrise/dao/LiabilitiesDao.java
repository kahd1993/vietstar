/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sunrise.entity.Liabilities;

/**
 *
 * @author Do Quoc Hoa
 */
public class LiabilitiesDao extends BaseDao{
    public List<Liabilities> getAllLiabilities(){
        List<Liabilities> lstLiabilities = null;
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        try{
            sql = "     select id,date,total_amount,total_liability,number_day,status, row_number() over(order by id desc) R"
                    + " from liabilities";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            Liabilities liab = null;
            while(rs.next()){
                if(lstLiabilities==null) lstLiabilities = new ArrayList<>();
                liab = new Liabilities();
                int i = 1;
                liab.setId(rs.getInt(i++));
                liab.setDate(rs.getString(i++));
                liab.setTotal_amount(rs.getBigDecimal(i++));
                liab.setTotal_liability(rs.getBigDecimal(i++));
                liab.setNumber_day(rs.getInt(i++));
                liab.setStatus(rs.getInt(i++));
                liab.setRow(rs.getInt(i++));
                lstLiabilities.add(liab);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return lstLiabilities;
    }
    
    public boolean updateLiability(BigDecimal moneys, int id){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = " Update liabilities set total_liability = total_liability - ? where id = ?";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            pstm.setBigDecimal(1, moneys);
            pstm.setInt(2, id);
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
}
