package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.TourLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilona on 07.05.15.
 */
public class CreateTourCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return ConfigurationManager.getProperty("path.page.admin_create_tour");
    }
}
