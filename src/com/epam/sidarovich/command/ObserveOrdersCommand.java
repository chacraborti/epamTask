package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.MessageManager;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.manager.SessionLocaleManager;
import com.epam.sidarovich.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by ilona on 18.05.15.
 */
public class ObserveOrdersCommand implements ActionCommand {

    private static final String USER_ATTR = "user";
    private static final String EMPTY_CART = "emptyCart";
    private static final String ORDERS_ATTR = "orders";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MessageManager messageManager = new MessageManager();
        SessionLocaleManager sessionLocaleManager = new SessionLocaleManager();
        Locale locale = sessionLocaleManager.receiveLocale(session);
        User user = (User) session.getAttribute(USER_ATTR);
        OrderService orderService = new OrderService();
        String email = user.getEmail();
        List<Order> orderList = null;
        try {
            if (!user.getIsAdmin())
                orderList = orderService.findOrdersByEmail(email);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (orderList.isEmpty()) {
            request.setAttribute(EMPTY_CART, messageManager.getProperty("message.emptyCart", locale));
        }

        Collections.reverse(orderList);
        request.setAttribute(ORDERS_ATTR, orderList);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.cart");
    }
}
