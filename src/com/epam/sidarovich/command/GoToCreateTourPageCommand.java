package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilona on 07.05.15.
 */
public class GoToCreateTourPageCommand implements ActionCommand {
    /**
     * Go to create tour page
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ConfigurationManager configurationManager=new ConfigurationManager();
        return configurationManager.getProperty("path.page.admin_create_tour");
    }
}
