package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.TourLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilona on 08.05.15.
 */
public class DeleteTourCommand implements ActionCommand {
    /**
     * Delete tour, return tours page
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        TourLogic tourLogic = new TourLogic();

        int idTour=Integer.valueOf(request.getParameter("tourId"));

        try {
            tourLogic.deleteTour(idTour);
        } catch (LogicException e) {
            throw new CommandException();
        }
        List<Tour> tours;
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
