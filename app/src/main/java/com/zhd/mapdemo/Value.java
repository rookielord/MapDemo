package com.zhd.mapdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 2015032501 on 2015/8/13.
 */
public class Value {
    public String GetCurrentTiem(){
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        Date curdate=new Date(System.currentTimeMillis());
        String time=format.format(curdate);
        return time;
    }
}
