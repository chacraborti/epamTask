package com.epam.sidarovich.manager;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static final String BUNDLE_NAME = "resources.message";
    public MessageManager() { }

    /**
     * Get information from resources.message file
     * @param key
     * @return
     */

    public  String getProperty(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale );
        return bundle.getString(key);
}
}
