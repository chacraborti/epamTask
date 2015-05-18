package com.epam.sidarovich.logic;

import com.epam.sidarovich.dao.OrderDAO;
import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by ilona on 05.05.15.
 */
public class OrderLogic {
    /**
     * Create order
     * @param tour
     * @param emailUser
     * @param orderStatus
     * @return
     * @throws LogicException
     */
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

    /**
     * Find order by email
     * @param email
     * @return
     * @throws LogicException
     */
    public List<Order> findOrderByEmail(String email) throws LogicException{
        List<Order> orders;
        OrderDAO orderDAO = new OrderDAO();
        try {
            orders=orderDAO.findByEmail(email);
        } catch (DAOException e) {
            throw  new LogicException(e);
        }
        return orders;
    }

    /**
     * Update order status
     * @param order
     * @param orderStatus
     * @throws LogicException
     */
    public void updateOrderStatus(Order order, OrderStatus orderStatus) throws LogicException{
        OrderDAO orderDAO=new OrderDAO();
        try {
            orderDAO.changeOrderStatus(order,orderStatus);
        } catch (DAOException e) {
            throw new LogicException();
        }


    }

    /**
     * Fiind order by id
     * @param id
     * @return
     * @throws LogicException
     */
    public Order findOrderById(int id) throws LogicException {
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
