package sunrise.database;
import inet.util.Encrypter;
import inet.util.Md5;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.util.*;
import snaq.db.ConnectionPoolManager;
import sunrise.constant.Constants;

public class DBPoolX {

    public static ConnectionPoolManager poolManager;
    public String poolName;

    static {
        try {
            init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public DBPoolX() throws Exception {
        init();
    }

    public DBPoolX(String poolName) throws Exception {

        if (poolManager == null) {
            init();
        }

        this.poolName = poolName;
    }

    public static void init() throws Exception {
        try {
            File file = new File("dbpool.propertiesExt");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

            Properties properties = new Properties();
            properties.load(DBPoolX.class.getResourceAsStream("/sunrise/configuration/dbpool.properties"));
            Enumeration e = properties.propertyNames();

            while (e.hasMoreElements()) {
                String data = "";
                String value = "";
                String key = (String) e.nextElement();
                if (key.contains("password")) {
                    value = Encrypter.decrypt(properties.getProperty(key));
                } else {
                    value = properties.getProperty(key);
                }
                data = key + "=" + value;
                bw.write(data);
                bw.newLine();
            }

            bw.close();
            poolManager = ConnectionPoolManager.getInstance(file);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static DBPoolX getInstance(String poolName) throws Exception {
        return new DBPoolX(poolName);
    }

    // Remove and close all connections in pool
    public static void releaseAll() {
        poolManager.release();
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = poolManager.getConnection(poolName, 60000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt) {
        try {
            if (preStmt != null) {
                preStmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }

        releaseConnection(conn, preStmt);
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
        }

        releaseConnection(conn, preStmt, rs);
    }

    
    public static void main(String[] args) throws Exception{
//        System.out.println("Encrypter.encrypt(\"yte\")="+Encrypter.encrypt("thuoc"));
//        System.out.println(Encrypter.decrypt("5nsytePMtk0="));
        System.out.println(Md5.Hash("admin"));
//        System.out.println(Md5.Hash("123456"));
        DBPoolX poolX = DBPoolX.getInstance(Constants.DATABASE);
        if(poolX.getConnection() != null) System.out.println("success");
        else System.out.println("fail");
    }
}
