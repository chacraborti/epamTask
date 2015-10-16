package com.epam.sidarovich.manager;


import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by ilona on 18.05.15.
 */
public class SessionLocaleManager {
    public Locale receiveLocale(HttpSession session) {
        String stringLocale = (String) session.getAttribute("Locale");
        Locale locale = Locale.getDefault();
        if (stringLocale != null) {
            locale = Locale.forLanguageTag(stringLocale);
        }
        return locale;
    }
}
