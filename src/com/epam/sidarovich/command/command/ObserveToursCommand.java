package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.TourLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
////не надо
public class ObserveToursCommand implements ActionCommand{
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
        TourLogic tourLogic = new TourLogic();
        HttpSession session = request.getSession();
        List<Tour> tours = null;
        try {
            tours = tourLogic.viewAllTours();
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        session.setAttribute("tours", tours);
        return ConfigurationManager.getProperty("path.page.tours");
        }
    }