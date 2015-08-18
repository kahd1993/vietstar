/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    
}
