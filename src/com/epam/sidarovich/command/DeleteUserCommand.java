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
public class DeleteUserCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(DeleteUserCommand.class);

    private static final String USERS_ATTR = "users";
    private static final String USER_EMAIL_PARAM = "userEmail";

    /**
     * Delete user, return users page
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
        List<User> users;
        try {
            user = userService.findUserByEmail(email);
            if (!user.getIsAdmin()) {
                userService.deleteUser(email);
            } else {
                LOG.info("User is admin");
            }
            users = userService.viewAllUsers();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(USERS_ATTR, users);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.admin_users");
    }
}
