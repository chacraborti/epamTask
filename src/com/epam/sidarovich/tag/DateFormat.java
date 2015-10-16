package com.epam.sidarovich.tag;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by ilona on 16.05.15.
 */
public class DateFormat {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static String dateFormatted(GregorianCalendar calendar) {
        String res = null;
        if (calendar == null || calendar.toString().isEmpty()) {
            res = "Attribute or Parameter is null or empty";

        } else {
            SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
            String dateFormatted = fmt.format(calendar.getTime());
            res = dateFormatted;
        }
        return res;
    }
}
