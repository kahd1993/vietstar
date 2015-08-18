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
import java.util.List;
import sunrise.entity.Imports;

/**
 *
 * @author Do Quoc Hoa
 */
public class ImportDao extends BaseDao{
    public List<Imports> getAllImport(){
        List<Imports> lstImport = null;
        Connection cnn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "";
        try{
            sql = "     select A.id, A.code, A.id_store, A.id_supplier, A.date, A.total_amount, B.name as stName, C.name as supName, row_number() over(order by id desc) R"
                    + " from import A join store B on A.id_store = B.id join Supplier C on A.id_supplier = C.id";
            cnn = getConnection();
            pstm = cnn.prepareStatement(sql);
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            releaseConnection(cnn, pstm, rs);
        }
        return lstImport;
    }
    
}
