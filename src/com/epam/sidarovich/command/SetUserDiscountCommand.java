package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.UserLogic;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by ilona on 11.05.15.
 */
public class SetUserDiscountCommand implements ActionCommand{
    private static final Logger LOG = Logger.getLogger(SetUserDiscountCommand.class);

    /**
     * Set discount for user^ if user is not admin & is regular, go to user page
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        int discount = Integer.valueOf(request.getParameter("discount"));
        UserLogic userLogic = new UserLogic();
        String email = request.getParameter("userEmail");
        User user;
        try {
            user = userLogic.findUserByEmail(email);
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        try {
            if(user.getIsRegular() && !user.getIsAdmin() && discount>=0){
            userLogic.updateUserDiscount(email,discount);
            }
            else {
                LOG.info("Not suitable for setting discount");
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
