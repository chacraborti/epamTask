package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.TourService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ilona on 07.05.15.
 */
public class CreateTourCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CreateTourCommand.class);
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

    /**
     * Create tour, if country, date and cost>0 are entered, today or future date return tours page
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        TourService tourService = new TourService();
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
            if (country != null && date != null && cost > MIN_COST && dateTime.after(preCurrent)) {
                tourService.createTour(cal, isHot, tourType, cost, country);
            }
            LOG.info("Not valid tour");
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        List<Tour> tours = null;
        try {
            tours = tourService.viewAllTours();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        Collections.reverse(tours);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTR);
        session.setAttribute(IS_ADMIN_ATTR, user.getIsAdmin());
        request.setAttribute(TOURS_ATTR, tours);

        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.tours");
    }
}
