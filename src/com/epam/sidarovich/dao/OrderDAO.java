package com.epam.sidarovich.dao;

import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.entity.*;
import com.epam.sidarovich.exception.ConnectionPoolException;
import com.epam.sidarovich.exception.DAOException;

import java.sql.*;
import java.util.List;

/**
 * Created by ilona on 24.04.15.
 */
public class OrderDAO extends AbstractDAO<Order>{
    private static final String SELECT_ALL_FROM_ORDERS = "select idOrder, User.Email, Tour.idTour from Purchase join User On Purchase.emailUser=User.Email join Tour on Purchase.idTour=Tour.idTour";
    private static final String FIND_ORDER_BY_ID = "SELECT * FROM Tour WHERE Tour.idTour = ?";
    private static final String DELETE_ORDER_BY_ID = "DELETE FROM Tour WHERE Tour.idTour = ?";
    private static final String CREATE_ORDER = "INSERT INTO Purchase (idTour, emailUser, Status) VALUES(?, ?, ?)";

    @Override
    public List<Order> findAll() throws DAOException {
        return null;
    }

    public Order findEntityById(int id) throws DAOException {
        return null;
    }


    public int delete(int id) throws DAOException, ConnectionPoolException {
        return 0;
    }
    @Override
    public boolean delete(Order entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Order order) throws DAOException {
        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_ORDER);
            statement.setInt(1,order.getIdTour());
            statement.setString(2,order.getEmailUser());
            OrderStatus orderStatus = order.getOrderStatus();
            statement.setString(3, orderStatus.toString());
            if (statement.executeUpdate() > 0) {
                return true;
            } else {
                throw new DAOException("Update user failed");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {

            close(statement);
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public int update(Order entity) throws DAOException {
        return 0;
    }

    @Override
    public Order createEntity(ResultSet resultSet) throws DAOException {
//        Order order = new Order();
//        try {
//            order.setIdTour(resultSet.getInt("idTour"));
//            order.setEmailUser(resultSet.getString("emailUser"));
//            order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("OrderStatus")));
//        } catch (SQLException e) {
//            throw new DAOException("Sql Exception");
//        }
//        return order;
        Order order = new Order();
        Tour tour = new Tour();
        User user = new User();
        try {
            order.setIdTour(resultSet.getInt("idTour"));
            order.setEmailUser(resultSet.getString("emailUser"));
            order.setOrderStatus(OrderStatus.valueOf(resultSet.getString("OrderStatus")));
        } catch (SQLException e) {
            throw new DAOException("Sql Exception");
        }
        return order;
    }

}
