package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.TourLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;

public class ObserveToursCommand implements ActionCommand{
    /**
     * View tours,go to tours page
     * @param request
     * @return
     * @throws CommandException
     */
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
        Collections.reverse(tours);
        request.setAttribute("tours", tours);
        PathPageManager pathPageManager =new PathPageManager();
        return pathPageManager.getProperty("path.page.tours");
        }
    }