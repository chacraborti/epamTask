package com.epam.sidarovich.manager;

import java.util.ResourceBundle;

/**
 * Created by ilona on 25.03.15.
 */
public class ConfigurationManager {
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.config");
    public ConfigurationManager() { }

    /**
     * Get information from configuration file
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
