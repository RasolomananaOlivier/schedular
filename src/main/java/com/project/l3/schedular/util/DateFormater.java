package com.project.l3.schedular.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormater {
    public static String format(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);
        return dateFormatter.format(date);
    }

    public static String format(Date date, String format) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format, Locale.FRENCH);
        return dateFormatter.format(date);
    }

    public static String formatToHour(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm", Locale.FRENCH);
        return dateFormatter.format(date);
    }
}
