package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ilona on 07.05.15.
 */
public class CreateTourPageCommand implements ActionCommand {
    /**
     * Go to create tour page
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Date current=new Date();
        String currentString = new SimpleDateFormat("yyyy-MM-dd").format(current);
        request.setAttribute("current", currentString);
        PathPageManager pathPageManager =new PathPageManager();
        return pathPageManager.getProperty("path.page.admin_create_tour");
    }
}
