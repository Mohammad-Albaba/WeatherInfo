package com.example.weatherinfo;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtils {

    public static String format(long dateTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault());
        long currentTime = (long) dateTime * 1000L;
        return simpleDateFormat.format(currentTime);
    }
}
