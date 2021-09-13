package com.example.communicationapp.util;

import java.util.Calendar;

public class TimeUtil {


    public static long getCurrentTimeStamp(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }
}
