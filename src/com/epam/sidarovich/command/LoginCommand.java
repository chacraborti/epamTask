package com.epam.sidarovich.command;



import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.LoginLogic;
import com.epam.sidarovich.logic.TourLogic;
import com.epam.sidarovich.logic.UserLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by ilona on 25.03.15.
 */
public class LoginCommand implements ActionCommand {
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";

    /**
     * User login^ success if user exist, go to tour page, else show error login message
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request)  throws CommandException{
        String page=null;
        String login = request.getParameter(LOGIN_PARAMETER);
        String pass = request.getParameter(PASSWORD_PARAMETER);
        PathPageManager pathPageManager =new PathPageManager();
        HttpSession session = request.getSession();
        MessageManager messageManager = new MessageManager();
        SessionLocaleManager sessionLocaleManager = new SessionLocaleManager();
        Locale locale = sessionLocaleManager.receiveLocale(session);
        if (login != null ){
            User user = null;
            try {
                user = LoginLogic.checkEmailPassword(login, pass);
                if(user!=null){
                    session.setAttribute("user", user);
                    if (!user.getIsAdmin()){
                        TourLogic tourLogic = new TourLogic();
                        List<Tour> tours = null;
                        try {
                            tours = tourLogic.viewAllTours();
                        } catch (LogicException e) {
                            throw new CommandException(e);
                        }
                        Collections.reverse(tours);
                        request.setAttribute("tours", tours);
                        page = pathPageManager.getProperty("path.page.tours");
                    }
                    else{
                        UserLogic userLogic = new UserLogic();
                        List<User> users = null;
                        try {
                            users = userLogic.viewAllUsers();
                        } catch (LogicException e) {
                            throw new CommandException(e);
                        }
                        request.setAttribute("users", users);
                        page = pathPageManager.getProperty("path.page.admin_users");
                    }
                }
                else{
                    page = pathPageManager.getProperty("path.page.index");
                    request.setAttribute("errorLoginPassMessage", messageManager.getProperty("message.loginerror",locale));
                }
            } catch (LogicException e) {
                throw new CommandException(e);
            }
        }
    return page;
}
}
