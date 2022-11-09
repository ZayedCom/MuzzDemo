package com.muzz.muzzdemo.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStamp {

    //Generate timestamp in unix format
    public static long generateTimeStamp() {
        return System.currentTimeMillis();
    }

    //Convert timestamp in unix format to {day} {timestamp} format
    public static String getTimeStamp(long time) {
        if (time < 1000000000000L) {
            time *= 1000;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE HH:mm");

        Date resultDate = new Date(time);

        return simpleDateFormat.format(resultDate);
    }
}
