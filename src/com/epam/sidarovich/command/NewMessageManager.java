package com.epam.sidarovich.command;

import javax.servlet.ServletRequest;
import java.util.*;

/**
 * Created by ilona on 16.05.15.
 */
public class NewMessageManager {

    public static final String REQUEST_PARAMETER = "lang";
    private Locale defaultLocale = Locale.forLanguageTag("ru");
    private ResourceBundle bundle = null;
    private static final String BUNDLE_NAME = "message";

//    private HashMap<String, Locale> locales = new HashMap<String, Locale>();
//    {
//        locales.put("English", Locale.forLanguageTag("en"));
//        locales.put("Русский", Locale.forLanguageTag("ru"));
//    }

    /**
     * Get locale from code
     * @return
     */
//    public Locale resolveLocale(ServletRequest request){
//        String code = request.getParameter(REQUEST_PARAMETER);
//            Iterator<Map.Entry<String, Locale>> iterator = locales.entrySet().iterator();
//
//            while(iterator.hasNext()){
//                Locale locale = iterator.next().getValue();
//                if (locale.toLanguageTag().equalsIgnoreCase(code)){
//                    return locale;
//                }
//        }
//        return defaultLocale;
//    }

    public String getMessage(String key, Locale locale) {
        try{

                bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        } catch (MissingResourceException e){
            return "???? " + key + " ????";
        }
        return bundle.getString(key);
    }
}
