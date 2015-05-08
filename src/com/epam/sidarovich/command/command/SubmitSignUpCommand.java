package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ilona on 27.04.15.
 */
public class SubmitSignUpCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserLogic userLogic = new UserLogic();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2=request.getParameter("repite-password");
        String name = request.getParameter("name");
        try {
            if(userLogic.isValidEmailAddress(email) && userLogic.isValidPassword(password,password2)){
            userLogic.createUser(email, password, name);
            }
        } catch (LogicException e) {
            throw new CommandException();
        }
        return ConfigurationManager.getProperty("path.page.login");
    }
}
