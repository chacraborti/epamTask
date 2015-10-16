package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilona on 12.04.15.
 */
public class ObserveUsersCommand implements ActionCommand {

    private static final String USERS_ATTR = "users";

    /**
     * View users
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserService userService = new UserService();

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
