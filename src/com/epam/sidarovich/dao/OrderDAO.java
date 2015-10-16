package com.epam.sidarovich.dao;

import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.entity.Order;
import com.epam.sidarovich.entity.OrderStatus;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
import com.epam.sidarovich.exception.DAOException;

import java.sql.*;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilona on 24.04.15.
 */
public class OrderDAO extends AbstractDAO<Order> {
    private static final String SELECT_ALL_FROM_ORDERS = "SELECT emailUser, OrderStatus, Tour.idTour, Country, Cost, Discount, isHot, Date , TourType.Name FROM Purchase JOIN Tour ON Purchase.idTour=Tour.idTour JOIN TourType ON Tour.idTourType=TourType.idTourType";
    private static final String SELECT_PAID_FROM_ORDERS = "SELECT idOrder, emailUser, OrderStatus, Tour.idTour, Country, Cost, Discount, isHot, Date , TourType.Name FROM Purchase JOIN Tour ON Purchase.idTour=Tour.idTour JOIN TourType ON Tour.idTourType=TourType.idTourType WHERE OrderStatus='PAID'";
    private static final String CREATE_ORDER = "INSERT INTO Purchase (idTour, emailUser, OrderStatus) VALUES(?, ?, ?)";
    private static final String SELECT_ORDER_BY_EMAIL = "SELECT idOrder, emailUser, OrderStatus, Tour.idTour, Country, Cost, Discount, isHot, Date , TourType.Name FROM Purchase JOIN Tour ON Purchase.idTour=Tour.idTour JOIN TourType ON Tour.idTourType=TourType.idTourType WHERE emailUser=? ORDER BY idOrder";
    private static final String SELECT_ORDER_BY_ID = "SELECT idOrder, emailUser, OrderStatus, Tour.idTour, Country, Cost, Discount, isHot, Date , TourType.Name FROM Purchase JOIN Tour ON Purchase.idTour=Tour.idTour JOIN TourType ON Tour.idTourType=TourType.idTourType WHERE idOrder=?";
    private static final String CHANGE_ORDER_STATUS = "UPDATE Purchase  SET OrderStatus=? WHERE idOrder=?";
    private static final String UPDATE_ORDER = "UPDATE Purchase SET idOrder = ?, idTour = ?, emailUser = ?, OrderStatus = ?";


    /**
     * Find all orders
     *
     * @return
     * @throws DAOException
     */
    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new LinkedList<>();
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_FROM_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = createEntity(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            connectionPool.releaseConnection(connection);
        }
        return orders;
    }

    /**
     * Create order to data base
     *
     * @param order
     * @return
     * @throws DAOException
     */
    @Override
    public boolean create(Order order) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        boolean flag;
        try {
            statement = connection.prepareStatement(CREATE_ORDER);
            statement.setInt(1, order.getTour().getId());
            statement.setString(2, order.getEmailUser());
            OrderStatus orderStatus = order.getOrderStatus();
            statement.setString(3, orderStatus.toString());
            if (statement.executeUpdate() > 0) {
                flag = true;
            } else {
                throw new DAOException("Update user failed");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {

            close(statement);
            connectionPool.releaseConnection(connection);
        }
        if (flag) {
            return true;
        }
        return false;
    }

    /**
     * Update order
     *
     * @param order
     * @return
     * @throws DAOException
     */
    @Override
    public void update(Order order, int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_ORDER);

            statement.setInt(1, order.getId());
            statement.setInt(2, order.getTour().getId());
            statement.setString(3, order.getEmailUser());
            statement.setString(4, order.getOrderStatus().toString());

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            connectionPool.releaseConnection(connection);
        }
    }

    /**
     * Create order from data base
     *
     * @param resultSet
     * @return
     * @throws DAOException
     */

    private Order createEntity(ResultSet resultSet) throws DAOException {

        String idOrderParam = "idOrder";
        String emailUserParam = "emailUser";
        String idTourParam = "idTour";
        String dateParam = "Date";
        String isHotParam = "isHot";
        String costParam = "Cost";
        String discountParam = "Discount";
        String countryParam = "Country";
        String tourTypeNameParam = "TourType.Name";
        String orderStatusParam = "OrderStatus";

        Order order = new Order();
        Tour tour = new Tour();

        try {
            order.setId(resultSet.getInt(idOrderParam));
            order.setEmailUser(resultSet.getString(emailUserParam));

            tour.setId(resultSet.getInt(idTourParam));
            Date date = resultSet.getDate(dateParam);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            tour.setDate(calendar);
            int isHot = resultSet.getInt(isHotParam);
            switch (isHot) {
                case (1):
                    tour.setHot(true);
                    break;
                case (0):
                    tour.setHot(false);
                    break;
                default:
                    throw new DAOException("Wrong Data format");
            }
            tour.setCost(resultSet.getInt(costParam));
            tour.setDiscount(resultSet.getInt(discountParam));
            tour.setCountry(resultSet.getString(countryParam));
            String typeOfTour = resultSet.getString(tourTypeNameParam);
            TourType tour_type = TourType.valueOf(typeOfTour.replace("-", "_").toUpperCase());
            tour.setTourType(tour_type);
            String status = resultSet.getString(orderStatusParam);
            OrderStatus orderStatus = OrderStatus.valueOf(status.replace("-", "_").toUpperCase());
            order.setOrderStatus(orderStatus);
            order.setTour(tour);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return order;
    }

    /**
     * Find order by email
     *
     * @param email
     * @return
     * @throws DAOException
     */
    public List<Order> findByEmail(String email) throws DAOException {
        List<Order> orders = new LinkedList<>();
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ORDER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = createEntity(resultSet);
                orders.add(order);

            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            connectionPool.releaseConnection(connection);
        }
        return orders;
    }

    /**
     * Change order status
     *
     * @param order
     * @param orderStatus
     * @return
     * @throws DAOException
     */
    public int changeOrderStatus(Order order, OrderStatus orderStatus) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_ORDER_STATUS);
            statement.setString(1, orderStatus.toString());
            statement.setInt(2, order.getId());
            if (statement.executeUpdate() > 0) {
                return statement.executeUpdate();
            } else {
                throw new DAOException("Update order failed");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            connectionPool.releaseConnection(connection);
        }
    }

    /**
     * Select order by id
     *
     * @param id
     * @return
     * @throws DAOException
     */
    public Order selectByIdOrder(int id) throws DAOException {
        Order order = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = createEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            connectionPool.releaseConnection(connection);
        }
        return order;
    }

    public List<Order> findPaidOrders() throws DAOException {
        List<Order> orders = new LinkedList<>();
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_PAID_FROM_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = createEntity(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            connectionPool.releaseConnection(connection);
        }
        return orders;
    }

}
