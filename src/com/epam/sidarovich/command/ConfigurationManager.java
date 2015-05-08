package com.epam.sidarovich.command;

import java.util.ResourceBundle;

/**
 * Created by ilona on 25.03.15.
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.config");
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
