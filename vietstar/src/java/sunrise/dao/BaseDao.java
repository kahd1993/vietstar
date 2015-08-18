/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.dao;

import sunrise.constant.Constants;
import sunrise.database.DBPoolX;
import inet.util.DateTime;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Administrator
 */
public abstract class BaseDao implements Serializable {

    protected DBPoolX poolX = null;

    protected BaseDao() {
        try {
            poolX = DBPoolX.getInstance(Constants.DATABASE);
        } catch (Exception dbnf) {
            dbnf.printStackTrace();
        }

    }

    protected Connection getConnection() {
        return poolX.getConnection();
    }

    protected void releaseConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        poolX.releaseConnection(conn, stmt, rs);
    }

    protected void releaseConnection(Connection conn, PreparedStatement stmt) {
        poolX.releaseConnection(conn, stmt);
    }

    public DBPoolX getPoolX() {
        return poolX;
    }

    public void setPoolX(DBPoolX poolX) {
        this.poolX = poolX;
    }

    public static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public static int getDayOfWeek(String ngay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(ngay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("ICT"));
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) + 1;
    }

    public static String getCurrentTime() {
        String ctime = "";
        DateTime dt = new DateTime();
        if (dt.getHour() < 10) {
            ctime = "0" + dt.getHour() + ":" + dt.getMinute();
        } else {
            ctime = dt.getHour() + ":" + dt.getMinute();
        }
        return ctime;
    }

    public static void main(String[] a){
        System.out.println(getCurrentTime());
    }
}
