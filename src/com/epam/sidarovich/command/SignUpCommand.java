package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.UserLogic;
import com.epam.sidarovich.validator.EntranceValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilona on 27.04.15.
 */
public class SignUpCommand implements ActionCommand {
    /**
     * Sign up to the site, if user not exist & email & password are valid, go to login page, show success message
     * Else show error message
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserLogic userLogic = new UserLogic();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2=request.getParameter("repite-password");
        String name = request.getParameter("name");
        MessageManager messageManager = new MessageManager();
        String page=null;
        EntranceValidator entranceValidator =new EntranceValidator();
        ConfigurationManager configurationManager=new ConfigurationManager();
        User user;
        try {
            user=userLogic.findUserByEmail(email);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(user==null){
            if(entranceValidator.isValidEmailAddress(email) && entranceValidator.isValidPassword(password,password2)){
                try {
                    userLogic.createUser(email, password, name);
                } catch (LogicException e) {
                    throw new CommandException(e);
                }
                request.setAttribute("registrationSuccess", messageManager.getProperty("message.registrationSuccess"));
                page=configurationManager.getProperty("path.page.login");
            }
        }else{
            if(password!=password2){
                request.setAttribute("passwordNotMatch", messageManager.getProperty("message.passwordNotMatch"));
            }
            request.setAttribute("errorUserExist", messageManager.getProperty("message.userexist"));

            page = configurationManager.getProperty("path.page.registration");

        }
        return page;
    }
}
