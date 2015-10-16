package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.OrderService;
import com.epam.sidarovich.service.TourService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilona on 05.05.15.
 */
public class MakeOrderCommand implements ActionCommand {

    private static final String TOUR_ID_PARAM = "tourId";
    private static final String USER_ATTR = "user";
    private static final String ORDERS_ATTR = "orders";

    /**
     * User make order, go to cart page
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        TourService tourService = new TourService();

        User user = (User) session.getAttribute(USER_ATTR);
        String email = user.getEmail();
        Tour tour;
        List<Order> orderList;
        int idTour = Integer.valueOf(request.getParameter(TOUR_ID_PARAM));
        try {
            tour = tourService.createTourById(idTour);
            orderService.createOrder(tour, email, OrderStatus.ACTIVE);
            orderList = orderService.findOrdersByEmail(email);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        Collections.reverse(orderList);
        request.setAttribute(ORDERS_ATTR, orderList);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.cart");

    }

}
