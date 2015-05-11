package com.epam.sidarovich.command.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.UserLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilona on 06.05.15.
 */
public class MakeRegularCommand implements ActionCommand{
    private static final Logger LOG = Logger.getLogger(MakeRegularCommand.class);

    /**
     * Make client become regular, if client is not admin, go to users page
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserLogic userLogic = new UserLogic();

        String email = request.getParameter("userEmail");
        User user;
        try {
            user = userLogic.findUserByEmail(email);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        try {
            if(!user.getIsAdmin()){
            userLogic.makeRegular(email);
            }else {
                LOG.info("User is admin");
            }
        } catch (LogicException e) {
            throw new CommandException();
        }
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
