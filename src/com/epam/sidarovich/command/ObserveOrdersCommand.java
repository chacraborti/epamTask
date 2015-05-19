package com.epam.sidarovich.command;

import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.CommandException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.OrderLogic;
import com.epam.sidarovich.manager.MessageManager;
import com.epam.sidarovich.manager.PathPageManager;
import com.epam.sidarovich.manager.SessionLocaleManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by ilona on 18.05.15.
 */
public class ObserveOrdersCommand implements ActionCommand{


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MessageManager messageManager = new MessageManager();
        SessionLocaleManager sessionLocaleManager = new SessionLocaleManager();
        Locale locale = sessionLocaleManager.receiveLocale(session);
        User user = (User)session.getAttribute("user");
        OrderLogic orderLogic = new OrderLogic();
        String email = user.getEmail();
        List<Order> orderList;
        try {
            orderList = orderLogic.findOrderByEmail(email);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(orderList.isEmpty()){
            request.setAttribute("emptyCart", messageManager.getProperty("message.emptyCart",locale));
        }

        Collections.reverse(orderList);
        request.setAttribute("orders",orderList);
        PathPageManager pathPageManager = new PathPageManager();
        return pathPageManager.getProperty("path.page.cart");
    }


}
