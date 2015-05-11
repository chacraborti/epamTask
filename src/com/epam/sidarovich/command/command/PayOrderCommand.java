package com.epam.sidarovich.command.command;

import com.epam.sidarovich.command.ConfigurationManager;
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
 * Created by ilona on 10.05.15.
 */
public class PayOrderCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderLogic orderLogic = new OrderLogic();
        User user = (User)session.getAttribute("user");
        String email = user.getEmail();
        Order order;
        int id = Integer.valueOf(request.getParameter("orderId"));
        try {
            order=orderLogic.findById(id);
        } catch (LogicException e) {
            throw new CommandException();
        }
        try {
            orderLogic.updateOrderStatus(order,OrderStatus.PAID);
        } catch (LogicException e) {
            throw new CommandException();
        }
        List<Order> orderList;
        try {
            orderList = orderLogic.findByEmail(email);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        Collections.reverse(orderList);
        session.setAttribute("orders",orderList);
        return ConfigurationManager.getProperty("path.page.cart");

    }
}