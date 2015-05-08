package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilona on 06.05.15.
 */
public class DeleteUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserLogic userLogic = new UserLogic();

        String email = request.getParameter("userEmail");

        try {
            userLogic.deleteUser(email);
        } catch (LogicException e) {
            throw new CommandException();
        }
        List<User> users;
        try {
            users = userLogic.viewAllUsers();
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        request.setAttribute("users", users);
        return ConfigurationManager.getProperty("path.page.admin_users");
    }
}
