package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.OrderLogic;
import com.epam.sidarovich.manager.PathPageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilona on 18.05.15.
 */
public class ObserveOrders implements ActionCommand{


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        OrderLogic orderLogic = new OrderLogic();
        String email = user.getEmail();
        List<Order> orderList;
        try {
            orderList = orderLogic.findOrderByEmail(email);
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        Collections.reverse(orderList);
        session.setAttribute("orders",orderList);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.cart");
    }


}
