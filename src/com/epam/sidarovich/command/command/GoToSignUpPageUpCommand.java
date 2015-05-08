package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;


import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilona on 26.04.15.
 */
public class GoToSignUpPageUpCommand implements ActionCommand{


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return ConfigurationManager.getProperty("path.page.registration");
    }
    }
