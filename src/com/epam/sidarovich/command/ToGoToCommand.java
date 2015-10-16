package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.TourService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ilona on 15.10.15.
 */
public class ToGoToCommand implements ActionCommand {
    private static final String PAGE_PARAM = "page";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TOUR_ID_PARAM = "tourId";
    private static final String CURRENT_ATTR = "current";
    private static final String TOUR_ATTR = "tour";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        PathPageManager pathPageManager = new PathPageManager();
        String page = request.getParameter(PAGE_PARAM);
        try {
            switch (page) {
                case "create_tour": {
                    Date current = new Date();
                    String currentString = new SimpleDateFormat(DATE_FORMAT).format(current);
                    request.setAttribute(CURRENT_ATTR, currentString);
                    return pathPageManager.getProperty("path.page.admin_create_tour");
                }
                case "update_tour": {
                    int idTour = Integer.valueOf(request.getParameter(TOUR_ID_PARAM));
                    request.setAttribute(TOUR_ID_PARAM, idTour);
                    TourService tourService = new TourService();
                    Tour tour;
                    tour = tourService.findTourById(idTour);

                    Date current = new Date();
                    String currentString = new SimpleDateFormat(DATE_FORMAT).format(current);
                    request.setAttribute(CURRENT_ATTR, currentString);
                    request.setAttribute(TOUR_ATTR, tour);
                    return pathPageManager.getProperty("path.page.update_tour");
                }
                case "sign_up": {
                    return pathPageManager.getProperty("path.page.registration");
                }
                default:
                    return pathPageManager.getProperty("path.page.index");

            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}



