package com.epam.sidarovich.command.command;


import com.epam.sidarovich.command.ConfigurationManager;
import com.epam.sidarovich.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().invalidate();
        return ConfigurationManager.getProperty("path.page.index");
    }
}