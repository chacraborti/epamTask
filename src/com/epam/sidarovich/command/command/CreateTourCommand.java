package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.TourLogic;
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
public class CreateTourCommand implements ActionCommand{

    private static final Logger LOG = Logger.getLogger(CreateTourCommand.class);
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        TourLogic tourLogic = new TourLogic();
        String country = request.getParameter("country");
        String date = request.getParameter("date");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime=null;
        GregorianCalendar cal = new GregorianCalendar();

        try {
            dateTime = df.parse(date);
        } catch (ParseException e) {
            throw new CommandException();
        }
        cal.setTime(dateTime);
        TourType tourType = TourType.valueOf(request.getParameter("tourType"));
        boolean isHot=false;
        if (request.getParameter("isHot")=="isHot"){
            isHot=true;
        }
        int cost = Integer.valueOf(request.getParameter("cost"));

            try {
                if(country!=null && date!=null && cost>0){
                tourLogic.createTour(cal, isHot, tourType, cost,country);
                }
                LOG.info("Not valid tour");
            } catch (LogicException e) {
                throw new CommandException(e);
            }

        List<Tour> tours = null;
        try {
            tours = tourLogic.viewAllTours();
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        Collections.reverse(tours);
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        session.setAttribute("isAdmin", user.getIsAdmin());
        request.setAttribute("tours", tours);
        return ConfigurationManager.getProperty("path.page.tours");
    }
}
