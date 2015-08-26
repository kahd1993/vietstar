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
import sunrise.entity.LiabilityDetails;

/**
 *
 * @author Do Quoc Hoa
 */
public class LiabilitiesDetailDao extends BaseDao{
    public List<LiabilityDetails> getAllLiabilityDetail(){
        List<LiabilityDetails> lstLiabDetail = null;
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        try{
            sql = "     select id_liabilities, id_export"
                    + " from liability_detail";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            LiabilityDetails liabDetail = null;
            while(rs.next()){
                liabDetail = new LiabilityDetails();
                if(lstLiabDetail==null) lstLiabDetail = new ArrayList<>();
                int i = 1;
                liabDetail.setId_liabilities(rs.getInt(i++));
                liabDetail.setId_export(rs.getInt(i++));
                lstLiabDetail.add(liabDetail);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return lstLiabDetail;
    }
    
    public boolean addLiabilityDeatail(int idExport, int idLiab){
        Connection cnn = null;
        PreparedStatement pstm = null;
        String sql = "";
        try{
            sql = "Insert into liability_detail(id_liabilities, id_export) values(?,?)";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, idLiab);
            pstm.setInt(2, idExport);
            return pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm);
        }
        return false;
    }
}
