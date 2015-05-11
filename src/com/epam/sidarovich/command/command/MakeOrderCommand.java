package com.epam.sidarovich.command.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.OrderLogic;
import com.epam.sidarovich.logic.TourLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilona on 05.05.15.
 */
public class MakeOrderCommand implements ActionCommand{

    /**
     * User make order, go to cart page
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        OrderLogic orderLogic = new OrderLogic();
        TourLogic tourLogic=new TourLogic();

        User user = (User)session.getAttribute("user");
        String email = user.getEmail();
        Tour tour;
        int idTour=Integer.valueOf(request.getParameter("tourId"));
        try {
            tour = tourLogic.createTourById(idTour);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        Order order;
        try {
            order=orderLogic.createOrder(tour, email, OrderStatus.ACTIVE);
        } catch (LogicException e) {
            throw new CommandException();
        }
        List<Order> orderList;
        try {
           orderList = orderLogic.findOrderByEmail(email);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        Collections.reverse(orderList);
        session.setAttribute("orders",orderList);
        return ConfigurationManager.getProperty("path.page.cart");

    }

}
