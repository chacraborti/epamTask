package com.epam.sidarovich.command;

import com.epam.sidarovich.manager.*;
import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilona on 10.05.15.
 */
public class PayOrderCommand implements ActionCommand {
    /**
     * Pay for order, set order status PAID, go to cart page
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderLogic orderLogic = new OrderLogic();
        User user = (User)session.getAttribute("user");
        String email = user.getEmail();
        Order order;
        int id = Integer.valueOf(request.getParameter("orderId"));
        try {
            order=orderLogic.findOrderById(id);
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
            orderList = orderLogic.findOrderByEmail(email);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        Collections.reverse(orderList);
        session.setAttribute("orders",orderList);
        ConfigurationManager configurationManager=new ConfigurationManager();
        return configurationManager.getProperty("path.page.cart");

    }
}