package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;


import com.epam.sidarovich.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilona on 26.04.15.
 */
public class SignUpPageUpCommand implements ActionCommand{


    /**
     * Go to registration page
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PathPageManager pathPageManager =new PathPageManager();
        return pathPageManager.getProperty("path.page.registration");
    }
    }
