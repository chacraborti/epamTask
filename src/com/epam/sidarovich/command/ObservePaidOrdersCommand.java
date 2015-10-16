package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilona on 16.10.15.
 */
public class ObservePaidOrdersCommand implements ActionCommand {

    private static final String ORDERS_ATTR = "orders";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        OrderService orderService = new OrderService();
        List<Order> orderList;
        try {
            orderList = orderService.findPaidOrders();

        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        Collections.reverse(orderList);
        request.setAttribute(ORDERS_ATTR, orderList);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.paid_orders");
    }
}
