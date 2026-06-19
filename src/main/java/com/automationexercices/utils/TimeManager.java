package com.automationexercices.utils;

public class TimeManager {
    // screenshot -logs-reports timestamp
    public static String getTimestamp(){
        return new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
    }
    // unique timestamp for each data
    public static String getSimpleUniqueTimestamp(){
        // return a timestamp in numbers only
        return  Long.toString(System.currentTimeMillis());
    }
}
