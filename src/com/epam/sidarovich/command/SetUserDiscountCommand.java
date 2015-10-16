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
 * Created by ilona on 11.05.15.
 */
public class SetUserDiscountCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(SetUserDiscountCommand.class);

    private static final String USER_EMAIL_PARAM = "userEmail";
    private static final String DISCOUNT = "discount";
    private static final String USERS_ATTR = "users";

    /**
     * Set discount for user, if user is not admin & is regular, go to user page
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        int discount = Integer.valueOf(request.getParameter(DISCOUNT));
        UserService userService = new UserService();
        String email = request.getParameter(USER_EMAIL_PARAM);
        User user;
        List<User> users = null;
        try {
            user = userService.findUserByEmail(email);
            if (user.getIsRegular() && !user.getIsAdmin() && discount >= 0) {
                userService.updateUserDiscount(email, discount);
            } else {
                LOG.info("Not suitable for setting discount");
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
