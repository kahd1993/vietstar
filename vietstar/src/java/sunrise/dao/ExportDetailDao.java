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
import java.util.ArrayList;
import java.util.List;
import sunrise.entity.ExportDetails;

/**
 *
 * @author zoncn
 */
public class ExportDetailDao extends BaseDao{
    public boolean insert(int epId, int idPd, int quality, BigDecimal price) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO EXPORT_DETAIL(ID_EXPORT, ID_PRODUCT, QUALITY, PRICE) "
                    + "   VALUES(?, ?, ?, ?)";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setInt(i++, epId);
            ps.setInt(i++, idPd);
            ps.setInt(i++, quality);
            ps.setBigDecimal(i++, price);

            return ps.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(conn, ps);
        }
        return false;
    }
    
    public List<ExportDetails> getAllDetailByID(int epId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ExportDetails> result = null;
        try{
            String sql = "SELECT * FROM(SELECT A.CODE, C.ID_PRODUCT, B.NAME, B.SELL_PRICE, C.QUALITY, C.PRICE"
                    + "   FROM EXPORT A JOIN EXPORT_DETAIL C ON A.ID = C.ID_EXPORT "
                    + "   JOIN PRODUCT B ON C.ID_PRODUCT = B.ID WHERE C.ID_EXPORT = ?)";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, epId);
            rs = ps.executeQuery();
            ExportDetails epd = null;
            while(rs.next()){
                int i = 1;
                if(result == null) result = new ArrayList<>();
                epd = new ExportDetails();
                epd.setEcode(rs.getString(i++));
                epd.setId_product(rs.getInt(i++));
                epd.setPname(rs.getString(i++));
                epd.setPcost(rs.getBigDecimal(i++));
                epd.setQuality(rs.getInt(i++));
                epd.setPrice(rs.getBigDecimal(i++));
                result.add(epd);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(conn, ps, rs);
        }
        return result;
    }
}
