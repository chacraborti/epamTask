package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.MakeOrderLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by ilona on 05.05.15.
 */
public class MakeOrderCommand implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        MakeOrderLogic orderLogic = new MakeOrderLogic();

        User user = (User)session.getAttribute("user");
        String email = user.getEmail();
        int idTour=Integer.valueOf(request.getParameter("tourId"));
        try {
            orderLogic.createOrder(idTour, email, OrderStatus.ACTIVE);
        } catch (LogicException e) {
            throw new CommandException();
        }

        return ConfigurationManager.getProperty("path.page.cart");

    }

}
