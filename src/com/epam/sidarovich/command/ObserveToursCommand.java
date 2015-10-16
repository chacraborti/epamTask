package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public class ObserveToursCommand implements ActionCommand {

    private static final String TOURS_ATTR = "tours";

    /**
     * View tours,go to tours page
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        TourService tourService = new TourService();

        List<Tour> tours;
        try {
            tours = tourService.viewAllTours();
//            for (Tour tour: allTours){
//                if(!tourService.isPaid(tour.getId())){
//                tours.add(tour);
//                }
//            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        Collections.reverse(tours);
        request.setAttribute(TOURS_ATTR, tours);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.tours");
    }
}