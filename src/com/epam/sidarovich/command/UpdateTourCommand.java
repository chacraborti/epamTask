package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.MessageManager;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.manager.SessionLocaleManager;
import com.epam.sidarovich.service.TourService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ilona on 15.10.15.
 */
public class UpdateTourCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UpdateTourCommand.class);

    private static final int MIN_COST = 0;
    private static final int DATE_DECREMENT = -1;
    private static final String COUNTRY_PARAM = "country";
    private static final String DATE_PARAM = "date";
    private static final String TOUR_TYPE_PARAM = "tourType";
    private static final String IS_HOT_PARAM = "isHot";
    private static final String COST_PARAM = "cost";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String USER_ATTR = "user";
    private static final String IS_ADMIN_ATTR = "isAdmin";
    private static final String TOURS_ATTR = "tours";
    private static final String TOUR_ID = "tourId";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        int idTour = Integer.valueOf(request.getParameter(TOUR_ID));
        String country = request.getParameter(COUNTRY_PARAM);
        String date = request.getParameter(DATE_PARAM);
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        Date dateTime = null;
        Date preCurrent = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        Calendar c = Calendar.getInstance();
        c.setTime(preCurrent);
        c.add(Calendar.DATE, DATE_DECREMENT);
        preCurrent = c.getTime();

        TourService tourService = new TourService();
        MessageManager messageManager = new MessageManager();
        Tour existingTour;
        List<Tour> tours;
        HttpSession session = request.getSession();
        SessionLocaleManager sessionLocaleManager = new SessionLocaleManager();
        Locale locale = sessionLocaleManager.receiveLocale(session);
        PathPageManager pathPageManager = new PathPageManager();

        try {
            dateTime = df.parse(date);
        } catch (ParseException e) {
            throw new CommandException(e);
        }

        cal.setTime(dateTime);
        TourType tourType = TourType.valueOf(request.getParameter(TOUR_TYPE_PARAM));
        boolean isHot = false;
        if (request.getParameter(IS_HOT_PARAM) != null) {
            isHot = true;
        }
        int cost = Integer.valueOf(request.getParameter(COST_PARAM));


        try {
            existingTour = tourService.findTourById(idTour);
            System.out.println(existingTour.toString());
            if (country != null && date != null && cost > MIN_COST && dateTime.after(preCurrent)) {
                existingTour.setCountry(country);
                existingTour.setTourType(tourType);
                existingTour.setCost(cost);
                existingTour.setDate(cal);
                existingTour.setHot(isHot);
            }
            tourService.updateTour(existingTour, idTour);
            tours = tourService.viewAllTours();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        Collections.reverse(tours);
        User user = (User) session.getAttribute(USER_ATTR);
        session.setAttribute(IS_ADMIN_ATTR, user.getIsAdmin());
        request.setAttribute(TOURS_ATTR, tours);
        request.setAttribute(TOUR_ID, idTour);
        request.setAttribute("tourUpdated", messageManager.getProperty("message.tourUpdated", locale));
        return pathPageManager.getProperty("path.page.tours");
    }
}
