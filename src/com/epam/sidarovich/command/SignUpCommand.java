package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.MessageManager;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.manager.SessionLocaleManager;
import com.epam.sidarovich.service.UserService;
import com.epam.sidarovich.validator.EntranceValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by ilona on 27.04.15.
 */
public class SignUpCommand implements ActionCommand {

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String REPITE_PASSWORD_PARAM = "repite-password";
    private static final String NAME_PARAM = "name";
    private static final String REGISTRATION_SUCCESS_ATTR = "registrationSuccess";
    private static final String ERROR_USER_EXIST_ATTR = "errorUserExist";
    private static final String PASSWORD_NOT_MATCH_ATTR = "passwordNotMatch";

    /**
     * Sign up to the site, if user not exist & email & password are valid, go to login page, show success message
     * Else show error message
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserService userService = new UserService();
        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String password2 = request.getParameter(REPITE_PASSWORD_PARAM);
        String name = request.getParameter(NAME_PARAM);
        MessageManager messageManager = new MessageManager();
        String page = null;
        EntranceValidator entranceValidator = new EntranceValidator();
        PathPageManager pathPageManager = new PathPageManager();
        User user;
        HttpSession session = request.getSession();
        SessionLocaleManager sessionLocaleManager = new SessionLocaleManager();
        Locale locale = sessionLocaleManager.receiveLocale(session);
        try {
            user = userService.findUserByEmail(email);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (user == null) {
            if (entranceValidator.isValidEmailAddress(email) && entranceValidator.isValidPassword(password, password2)) {
                try {
                    userService.createUser(email, password, name);
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
                request.setAttribute(REGISTRATION_SUCCESS_ATTR, messageManager.getProperty("message.registrationSuccess", locale));
                page = pathPageManager.getProperty("path.page.login");
            }
        } else {
            if (password != password2) {
                request.setAttribute(PASSWORD_NOT_MATCH_ATTR, messageManager.getProperty("message.passwordNotMatch", locale));
            }
            request.setAttribute(ERROR_USER_EXIST_ATTR, messageManager.getProperty("message.userexist", locale));

            page = pathPageManager.getProperty("path.page.registration");

        }
        return page;
    }
}
