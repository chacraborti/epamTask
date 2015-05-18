package com.epam.sidarovich.tag;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by ilona on 16.05.15.
 */
public class DateFormat{
    public static String dateFormatted(GregorianCalendar calendar) {
        String res = null;
        if (calendar == null || calendar.toString().isEmpty()) {
            res = "Attribute or Parameter is null or empty";

        } else {
            SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
            String dateFormatted = fmt.format(calendar.getTime());
            res = dateFormatted;
        }
        return res;
    }
}
