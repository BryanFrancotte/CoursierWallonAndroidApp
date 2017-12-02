package com.coursierwallon.bryan.coursierwallonandroidapp.Constant;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by franc on 25-11-17.
 */

public class OrderConstant {
    public static final String STATE = "NOT_ACCEPTED";
    public static final int pickUp = 1;
    public static final int deposit = 10;
    public static final int TYPE_S = 0;//TODO: faire une enum pour ce truc
    public static final int TYPE_M = 1;
    public static final int TYPE_L = 2;
    public static final int TYPE_XL = 3;
    public static final int DELIVERY_NORMAL = 0;
    public static final int DELIVERY_SEMI_EXPRESS = 1;
    public static final int DELIVERY_EXPRESS = 2;
    public static final Date getDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
