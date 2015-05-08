package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilona on 25.03.15.
 */
public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        return ConfigurationManager.getProperty("path.page.login");
    }
}