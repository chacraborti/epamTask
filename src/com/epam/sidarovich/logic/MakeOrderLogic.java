package com.epam.sidarovich.logic;

import com.epam.sidarovich.dao.OrderDAO;
import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;

/**
 * Created by ilona on 05.05.15.
 */
public class MakeOrderLogic {
    public void createOrder(int idTour, String emailUser, OrderStatus orderStatus) throws LogicException {
        Order order = new Order(idTour,emailUser,orderStatus);
        OrderDAO orderDAO = new OrderDAO();
        try {
            orderDAO.create(order);
        } catch (DAOException e) {
            throw new LogicException();

        }
    }
}
