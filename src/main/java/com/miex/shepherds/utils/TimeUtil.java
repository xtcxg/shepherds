package com.miex.shepherds.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    /**
     * 获取当前时间：yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String getTimeNow(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }

    public static Date getTime(String time){
        return null;
    }
}
