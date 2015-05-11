package com.epam.sidarovich.manager;

import java.util.ResourceBundle;

public class MessageManager {
    private  ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.message");
    public MessageManager() { }

    /**
     * Get information from resources.message file
     * @param key
     * @return
     */
    public  String getProperty(String key) {
        return resourceBundle.getString(key);
}
}
