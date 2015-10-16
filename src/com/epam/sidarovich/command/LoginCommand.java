package com.epam.sidarovich.command;


import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.MessageManager;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.manager.SessionLocaleManager;
import com.epam.sidarovich.service.LoginService;
import com.epam.sidarovich.service.TourService;
import com.epam.sidarovich.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by ilona on 25.03.15.
 */
public class LoginCommand implements ActionCommand {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String USER_ATTR = "user";
    private static final String TOURS_ATTR = "tours";
    private static final String USERS_ATTR = "users";

    /**
     * User login^ success if user exist, go to tour page, else show error login message
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String login = request.getParameter(LOGIN_PARAM);
        String pass = request.getParameter(PASSWORD_PARAM);
        PathPageManager pathPageManager = new PathPageManager();
        HttpSession session = request.getSession();
        MessageManager messageManager = new MessageManager();
        SessionLocaleManager sessionLocaleManager = new SessionLocaleManager();
        Locale locale = sessionLocaleManager.receiveLocale(session);
        if (login != null) {
            User user = null;
            try {
                user = LoginService.checkEmailPassword(login, pass);
                if (user != null) {
                    session.setAttribute(USER_ATTR, user);
                    if (!user.getIsAdmin()) {
                        TourService tourService = new TourService();
                        List<Tour> tours = null;
                        tours = tourService.viewAllTours();
                        Collections.reverse(tours);
                        request.setAttribute(TOURS_ATTR, tours);
                        page = pathPageManager.getProperty("path.page.tours");
                    } else {
                        UserService userService = new UserService();
                        List<User> users = null;
                        users = userService.viewAllUsers();
                        request.setAttribute(USERS_ATTR, users);
                        page = pathPageManager.getProperty("path.page.admin_users");
                    }
                } else {
                    page = pathPageManager.getProperty("path.page.index");
                    request.setAttribute("errorLoginPassMessage", messageManager.getProperty("message.loginerror", locale));
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return page;
    }
}
