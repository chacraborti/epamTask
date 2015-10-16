package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilona on 06.05.15.
 */
public class MakeRegularCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(MakeRegularCommand.class);

    private static final String USER_EMAIL_PARAM = "userEmail";
    private static final String USERS_ATTR = "users";

    /**
     * Make client become regular, if client is not admin, go to users page
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserService userService = new UserService();

        String email = request.getParameter(USER_EMAIL_PARAM);
        User user;
        try {
            user = userService.findUserByEmail(email);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        try {
            if (!user.getIsAdmin()) {
                userService.makeRegular(email);
            } else {
                LOG.info("User is admin");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        List<User> users = null;
        try {
            users = userService.viewAllUsers();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(USERS_ATTR, users);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.admin_users");
    }
}
