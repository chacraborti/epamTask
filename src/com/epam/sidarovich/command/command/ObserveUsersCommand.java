package com.epam.sidarovich.command.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilona on 12.04.15.
 */
public class ObserveUsersCommand implements ActionCommand{
    /**
     * View users
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserLogic userLogic = new UserLogic();

        List<User> users = null;
        try {
            users = userLogic.viewAllUsers();
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        request.setAttribute("users", users);
        return ConfigurationManager.getProperty("path.page.admin_users");
    }
}
