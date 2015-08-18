/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunrise.constant;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Constants {

    public static boolean THREAD_RUNNING = true;
    public static final String DATABASE = "orcl";
    public static final String UNDER_SCORE = "_";
    public static final long FIVE_SECONDS = 5 * 1000;
    public static final long FIVE_MINUS = 5 * 60 * 1000;
    public static final long TEN_MINUS = 10 * 60 * 1000;
    public static final long ONE_HOUR = 6 * TEN_MINUS;
    public static final long ONE_DAY = 24 * ONE_HOUR;
    public static final long ONE_MONTH = 30 * ONE_DAY;

    //Status user
    public static final int DEACTIVE = 0;
    public static final int ACTIVE = 1;
    
    //Image active,deactive
    public static final String IMG_ACTIVE = "../resources/images/active.png";
    public static final String IMG_DEACTIVE = "../resources/images/deactive.png";
}
