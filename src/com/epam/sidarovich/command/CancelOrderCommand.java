package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.ServiceException;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

/**
 * Created by ilona on 10.05.15.
 */
public class CancelOrderCommand implements ActionCommand {

    private static final String USER_ATTR = "user";
    private static final String ORDER_ID_PARAM = "orderId";
    private static final String ORDERS_ATTR = "orders";

    /**
     * User make order, order inserts into database, return cart page
     *
     * @param request
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        User user = (User) session.getAttribute(USER_ATTR);
        String email = user.getEmail();
        Order order;
        List<Order> orderList;
        int id = Integer.valueOf(request.getParameter(ORDER_ID_PARAM));
        try {
            order = orderService.findOrderById(id);
            if (order.getOrderStatus() != OrderStatus.PAID) {
                orderService.updateOrderStatus(order, OrderStatus.CANCELED);
            }
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
