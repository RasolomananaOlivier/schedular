package com.project.l3.schedular.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public static Date fromSimpleDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date fromDateWithHour(String date, String hour) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            return sdf.parse(date + " " + hour);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
