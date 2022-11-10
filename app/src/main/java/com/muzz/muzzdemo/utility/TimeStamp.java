package com.muzz.muzzdemo.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeStamp {
    private static final int SECOND_MILLIS = 1000;
    private static final int TWENTY_SECOND_MILLIS = 20 * SECOND_MILLIS;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;

    //Generate timestamp in unix format
    public static long generateTimeStamp() {
        return System.currentTimeMillis();
    }

    //Convert timestamp in unix format to {day} {timestamp} format
    public static String getTimeStamp(long time) {
        if (time < 1000000000000L) {
            time *= 1000;
        }

        SimpleDateFormat weekDayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        Date resultDate = new Date(time);

        return "<b>" + weekDayFormat.format(resultDate) + "</b> " + hourFormat.format(resultDate);
    }

    //Convert timestamp in unix format to seconds
    public static boolean getMessageTimeStamp(long time) {
        if (time < 1000000000000L) {
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return false;
        }

        final long timeDifference = now - time;

        if (timeDifference < MINUTE_MILLIS) {
            return false;
        } else if (timeDifference < 2 * MINUTE_MILLIS) {
            return false;
        } else if (timeDifference < 50 * MINUTE_MILLIS) {
            return false;
        } else if (timeDifference < 90 * MINUTE_MILLIS) {
            return true;
        } else if (timeDifference < 24 * HOUR_MILLIS) {
            return true;
        } else if (timeDifference < 48 * HOUR_MILLIS) {
            return true;
        } else {
            return true;
        }
    }
}
