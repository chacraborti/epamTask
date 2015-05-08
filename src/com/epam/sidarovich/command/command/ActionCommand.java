package com.epam.sidarovich.command.command;

import com.epam.sidarovich.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilona on 25.03.15.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request) throws CommandException;
}

