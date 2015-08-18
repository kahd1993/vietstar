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
import sunrise.entity.Exports;

/**
 *
 * @author zoncn
 */
public class ExportDao extends BaseDao {

    public boolean insert(Exports ep, BigDecimal total) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO EXPORT(CODE, ID_STORE, ID_CUSTOMER, DATE, TOTAL_AMOUNT) "
                    + "   VALUES(?, ?, ?, GETDATE(), ?)";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, ep.getCode());
            ps.setInt(i++, ep.getId_store());
            ps.setInt(i++, ep.getId_customer());
            ps.setBigDecimal(i++, total);

            return ps.executeUpdate() == 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(conn, ps);
        }
        return false;
    }

    public int getIdExport() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT IDENT_CURRENT('EXPORT')";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(conn, ps, rs);
        }
        return 0;
    }

    public List<Exports> getAllExport(String s) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Exports> result = null;
        try {
            String sql = "SELECT * FROM(SELECT A.ID, A.CODE, B.NAME AS NSTORE, C.NAME AS CUSNAME, A.DATE, A.TOTAL_AMOUNT,"
                    + "   ROW_NUMBER() OVER(ORDER BY A.ID DESC) R"
                    + "   FROM EXPORT A LEFT JOIN STORE B ON A.ID_STORE = B.ID AND EXPORT A LEFT JOIN CUSTOMER C ON A.ID_CUSTOMER = C.ID WHERE 1 = 1 ";
            if (s != null && !"".equals(s)) {
                sql += " AND (UPPER(A.CODE) LIKE '%" + s + "%' OR UPPER(NSTORE) LIKE '%" + s + "%'"
                        + "  OR UPPER(CUSNAME) LIKE '%" + s + "%' OR UPPER(A.DATE) LIKE '%" + s + "%'"
                        + "  OR UPPER(A.TOTAL_AMOUNT) LIKE '%" + s + "%')";
            }
            sql += " )a  WHERE R >= ? AND R <= ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            Exports ep = null;
            while (rs.next()) {
                int i = 1;
                if (result == null) {
                    result = new ArrayList<>();
                }
                ep = new Exports();
                ep.setId(rs.getInt(i++));
                ep.setCode(rs.getString(i++));
                ep.setStore_name(rs.getString(i++));
                ep.setCus_name(rs.getString(i++));
                ep.setDate(rs.getString(i++));
                ep.setTotal_amount(rs.getBigDecimal(i++));
                result.add(ep);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(conn, ps, rs);
        }
        return null;
    }
    
    public Exports getRowById(int id){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "     SELECT ID, CODE, TOTAL_AMOUNT"
                    + "        FROM EXPORT WHERE ID = ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Exports ep = null;
            int i = 1;
            while (rs.next()) {
                ep = new Exports();
                ep.setId(rs.getInt(i++));
                ep.setCode(rs.getString(i++));
                ep.setTotal_amount(rs.getBigDecimal(i++));
            }
            return ep;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            releaseConnection(conn, ps, rs);
        }
        return null;
    }
}
