package com.epam.sidarovich.service;

import com.epam.sidarovich.dao.OrderDAO;
import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.ServiceException;

import java.util.List;

/**
 * Created by ilona on 05.05.15.
 */
public class OrderService {
    /**
     * Create order
     *
     * @param tour
     * @param emailUser
     * @param orderStatus
     * @return
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public Order createOrder(Tour tour, String emailUser, OrderStatus orderStatus) throws ServiceException {
        Order order = new Order(tour, emailUser, orderStatus);
        OrderDAO orderDAO = new OrderDAO();
        try {
            orderDAO.create(order);
        } catch (DAOException e) {
            throw new ServiceException(e);

        }
        return order;
    }

    /**
     * Find order by email
     *
     * @param email
     * @return
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public List<Order> findOrdersByEmail(String email) throws ServiceException {
        List<Order> orders;
        OrderDAO orderDAO = new OrderDAO();
        try {
            orders = orderDAO.findByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    /**
     * Update order status
     *
     * @param order
     * @param orderStatus
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public void updateOrderStatus(Order order, OrderStatus orderStatus) throws ServiceException {
        OrderDAO orderDAO = new OrderDAO();
        try {
            orderDAO.changeOrderStatus(order, orderStatus);
        } catch (DAOException e) {
            throw new ServiceException();
        }


    }

    /**
     * Fiind order by id
     *
     * @param id
     * @return
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public Order findOrderById(int id) throws ServiceException {
        Order order;
        OrderDAO orderDAO = new OrderDAO();
        try {
            order = orderDAO.selectByIdOrder(id);
        } catch (DAOException e) {
            throw new ServiceException(e);

        }
        return order;
    }

    public List<Order> findPaidOrders() throws ServiceException {
        List<Order> orders;
        OrderDAO orderDAO = new OrderDAO();
        try {
            orders = orderDAO.findPaidOrders();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

}
