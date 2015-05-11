package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilona on 25.03.15.
 */
public class EmptyCommand implements ActionCommand {
    /**
     * Go to login page
     * @param request
     * @return
     */
    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty("path.page.login");
    }
}