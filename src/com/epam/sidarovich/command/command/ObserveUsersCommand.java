package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;
import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.dao.TourDAO;
import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ConnectionPoolException;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilona on 12.04.15.
 */
public class ObserveUsersCommand implements ActionCommand{
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
