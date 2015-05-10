package com.epam.sidarovich.dao;

import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
import com.epam.sidarovich.exception.ConnectionPoolException;
import com.epam.sidarovich.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ilona on 08.04.15.
 */
public class TourDAO extends AbstractDAO<Tour> {
    private static final Logger LOG = Logger.getLogger(TourDAO.class);

    private static final String SELECT_ALL_FROM_TOUR = "select idTour, Date, isHot, Cost, Discount, Country, TourType.Name from Tour Join TourType on Tour.idTourType=TourType.idTourType";
    private static final String FIND_TOUR_BY_ID = "SELECT idTour, Date, isHot, Cost, Discount, Country, TourType.Name from Tour Join TourType on Tour.idTourType=TourType.idTourType WHERE idTour=?";
    private static final String DELETE_TOUR_BY_ID = "DELETE FROM Tour WHERE Tour.idTour = ?";
    private static final String CREATE_TOUR = "INSERT INTO Tour (Date, isHot, idTourType, Cost, Discount, Country) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TOUR = "UPDATE Tour SET Date = ?, isHot = ?, idTourType = ?, Cost = ?, Discount = ?, Country = ?  WHERE id = ?";
    private static final String TOUR_TYPE = "SELECT TourType.Name FROM TourType WHERE idTourType = ?";
   // private static final String FIND_ORDERED_TOURS = "select Country, Date, isHot, idTourType, Cost, Purchase.emailUser from Tour join Purchase on Purchase.idTour=Tour.idTour";

    @Override
    public List<Tour> findAll() throws DAOException {
        List<Tour> tours = new ArrayList<>();
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_FROM_TOUR);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createEntity(resultSet);
                tours.add(tour);
            }
        } catch (SQLException e) {
            throw new DAOException();
        } catch (ConnectionPoolException e) {
            throw new DAOException();
        } finally {
                close(statement);
            connectionPool.releaseConnection(connection);
        }
        return tours;
    }

    public Tour findEntityById(int id) throws DAOException {
        Tour tour = null;
        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_TOUR_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tour = createEntity(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {

                close(statement);

            connectionPool.releaseConnection(connection);
        }
        return tour;
    }


    public int delete(int id) throws DAOException {
        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(DELETE_TOUR_BY_ID);
                statement.setInt(1, id);
                if (statement.executeUpdate() > 0) {
                    return statement.executeUpdate();
                } else {
                    throw new DAOException("Delete tour failed");
                }

            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {

                    close(statement);

                connectionPool.releaseConnection(connection);
            }
    }
    @Override
    public boolean delete(Tour tour) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Tour tour) throws DAOException {
        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        boolean flag;
            try {
                statement = connection.prepareStatement(CREATE_TOUR);
                GregorianCalendar calendar = tour.getDate();
                Date date = new Date(calendar.getTimeInMillis());
                statement.setDate(1, date);
                Boolean isHot = tour.getIsHot();
                if (isHot) {
                    statement.setInt(2, 1);
                } else {
                    statement.setInt(2, 0);
                }
                TourType tour_type = tour.getTourType();
                switch (tour_type) {
                    case REST:
                        statement.setInt(3, 1);
                        break;
                    case EXCURSION:
                        statement.setInt(3, 2);
                        break;
                    case SHOPPING:
                        statement.setInt(3, 3);
                        break;
                    default:
                        throw new DAOException("Unknown tour type");
                }
                statement.setInt(4, tour.getCost());
                statement.setInt(5, tour.getDiscount());
                statement.setString(6, tour.getCountry());

                if (statement.executeUpdate() > 0) {
                    flag=true;
                } else {
                    throw new DAOException("Update user failed");
                }

            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {

                    close(statement);
                connectionPool.releaseConnection(connection);
            }
        if (flag){
            return true;
        }
        return false;
    }

    @Override
    public int update(Tour tour) throws DAOException {
        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

            try {
                statement = connection.prepareStatement(CREATE_TOUR);
                statement.setInt(1, tour.getId());
                GregorianCalendar calendar = tour.getDate();
                Date date = new Date(calendar.getTimeInMillis());
                statement.setDate(2, date);
                Boolean isHot = tour.getIsHot();
                if (isHot) {
                    statement.setInt(3, 1);
                } else {
                    statement.setInt(3, 0);
                }
                TourType tour_type = tour.getTourType();
                switch (tour_type) {
                    case REST:
                        statement.setInt(4, 1);
                        break;
                    case EXCURSION:
                        statement.setInt(4, 2);
                        break;
                    case SHOPPING:
                        statement.setInt(4, 3);
                        break;
                    default:
                        throw new DAOException("Unknown tour type");
                }
                statement.setInt(5, tour.getCost());
                statement.setInt(6, tour.getDiscount());
                if (statement.executeUpdate() > 0) {
                    return statement.executeUpdate();
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
    public Tour createEntity(ResultSet resultSet) throws DAOException {
        Tour tour = new Tour();
        try {
            tour.setId(resultSet.getInt("idTour"));
            Date date = resultSet.getDate("Date");
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            tour.setDate(calendar);
            Integer isHot = resultSet.getInt("isHot");
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
            tour.setCost(resultSet.getInt("Cost"));
            tour.setDiscount(resultSet.getInt("Discount"));
            tour.setCountry(resultSet.getString("Country"));
            String typeOfTour=resultSet.getString("TourType.Name");
            TourType tour_type=TourType.valueOf(typeOfTour.replace("-", "_").toUpperCase());
            tour.setTourType(tour_type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour;
    }

    @Override
    public void close(Statement st) {
        super.close(st);
    }

//    public List <Tour> findOrderedTours() throws DAOException{
//        List<Tour> tours = new ArrayList<>();
//        ConnectionPool connectionPool = null;
//        PreparedStatement statement = null;
//        Connection connection = null;
//        try {
//            connectionPool = ConnectionPool.getConnectionPool();
//            connection = connectionPool.getConnection();
//            statement = connection.prepareStatement(FIND_ORDERED_TOURS);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Tour tour = createEntity(resultSet);
//                tours.add(tour);
//            }
//        } catch (SQLException e) {
//            throw new DAOException();
//        } catch (ConnectionPoolException e) {
//            throw new DAOException();
//        } finally {
//            close(statement);
//            connectionPool.releaseConnection(connection);
//        }
//        return tours;
//    }

}
