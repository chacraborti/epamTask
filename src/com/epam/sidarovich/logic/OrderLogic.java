package com.epam.sidarovich.logic;

import com.epam.sidarovich.dao.OrderDAO;
import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;

import java.util.List;

/**
 * Created by ilona on 05.05.15.
 */
public class OrderLogic {
    public Order createOrder(Tour tour, String emailUser, OrderStatus orderStatus) throws LogicException {
        Order order = new Order(tour,emailUser,orderStatus);
        OrderDAO orderDAO = new OrderDAO();
        try {
            orderDAO.create(order);
        } catch (DAOException e) {
            throw new LogicException(e);

        }
        return order;
    }
    public List<Order> findByEmail(String email) throws LogicException{
        List<Order> orders;
        OrderDAO orderDAO = new OrderDAO();
        try {
            orders=orderDAO.findByEmail(email);
        } catch (DAOException e) {
            throw  new LogicException(e);
        }
        return orders;
    }
    public void updateOrderStatus(Order order, OrderStatus orderStatus) throws LogicException{
        OrderDAO orderDAO=new OrderDAO();
        try {
            orderDAO.changeOrderStatus(order,orderStatus);
        } catch (DAOException e) {
            throw new LogicException();
        }


    }
    public Order findById(int id) throws LogicException {
        Order order;
        OrderDAO orderDAO = new OrderDAO();
        try {
            order=orderDAO.selectByIdOrder(id);
        } catch (DAOException e) {
            throw new LogicException(e);

        }
        return order;
    }

}
