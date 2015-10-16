package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilona on 08.05.15.
 */
public class DeleteTourCommand implements ActionCommand {

    private static final String TOUR_ID_PARAM = "tourId";
    private static final String TOURS_ATTR = "tours";

    /**
     * Delete tour, return tours page
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        TourService tourService = new TourService();
        List<Tour> tours;
        int idTour = Integer.valueOf(request.getParameter(TOUR_ID_PARAM));

        try {
            tourService.deleteTour(idTour);
            tours = tourService.viewAllTours();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        Collections.reverse(tours);
        request.setAttribute(TOURS_ATTR, tours);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.tours");
    }
}
