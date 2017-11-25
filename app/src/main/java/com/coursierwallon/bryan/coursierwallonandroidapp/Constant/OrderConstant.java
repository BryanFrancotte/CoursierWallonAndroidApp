package com.coursierwallon.bryan.coursierwallonandroidapp.Constant;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by franc on 25-11-17.
 */

public class OrderConstant {
    public static final String state = "NOT_ACCEPTED";
    public static final int pickUp = 1;
    public static final int deposit = 10;

    public static final Date getDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
