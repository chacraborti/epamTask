package com.epam.sidarovich.dao;

import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
import com.epam.sidarovich.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ilona on 08.04.15.
 */
public class TourDAO extends AbstractDAO<Tour> {
    private static final Logger LOG = Logger.getLogger(TourDAO.class);

    private static final String SELECT_ALL_FROM_TOUR = "SELECT idTour, Date, isHot, Cost, Discount, Country, TourType.Name FROM Tour JOIN TourType ON Tour.idTourType=TourType.idTourType";
    private static final String FIND_TOUR_BY_ID = "SELECT idTour, Date, isHot, Cost, Discount, Country, TourType.Name FROM Tour JOIN TourType ON Tour.idTourType=TourType.idTourType WHERE idTour=?";
    private static final String DELETE_TOUR_BY_ID = "DELETE FROM Tour WHERE Tour.idTour = ?";
    private static final String CREATE_TOUR = "INSERT INTO Tour (Date, isHot, idTourType, Cost, Discount, Country) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TOUR = "UPDATE Tour SET Date = ?, isHot = ?, idTourType = ?, Cost = ?, Country = ?  WHERE idTour = ?";
    private static final String SELECT_TOUR = "SELECT Date, isHot, Cost, Country, TourType.Name FROM Tour JOIN TourType ON Tour.idTourType=TourType.idTourType WHERE Date=?, isHot=?, Cost=?, Country=?, TourType.Name=?";
    private static final String IS_PAID = "SELECT COUNT(*) FROM Purchase WHERE idTour=? AND OrderStatus='PAID'";
    private static final String SELECT_UNPAID_FROM_TOUR = "SELECT Tour.idTour, Date, isHot, Cost, Discount, Country, TourType.Name, OrderStatus FROM Tour JOIN TourType ON Tour.idTourType=TourType.idTourType LEFT JOIN Purchase ON Purchase.idTour=Tour.idTour WHERE  OrderStatus='ACTIVE' OR OrderStatus IS NULL OR OrderStatus='CANCELED'";

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public List<Tour> findAll() throws DAOException {
        List<Tour> tours = new ArrayList<>();
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_UNPAID_FROM_TOUR);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tour tour = createEntity(resultSet);
                tours.add(tour);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            connectionPool.releaseConnection(connection);
        }
        return tours;
    }

    /**
     * Find tour by id
     *
     * @param id
     * @return
     * @throws DAOException
     */
    public Tour findTourById(int id) throws DAOException {
        Tour tour = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();

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

    /**
     * Create tour
     *
     * @param tour
     * @return
     * @throws DAOException
     */
    @Override
    public boolean create(Tour tour) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        boolean flag;
        try {
            statement = connection.prepareStatement(CREATE_TOUR);
            GregorianCalendar calendar = tour.getDate();
            Date date = new Date(calendar.getTimeInMillis());
            statement.setDate(1, date);
            boolean isHot = tour.getIsHot();
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
     * Delete tour by id
     *
     * @param id
     * @return
     * @throws DAOException
     */
    public int deleteTourById(int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();

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

    /**
     * Update tour
     *
     * @param tour
     * @return
     * @throws DAOException
     */
    @Override
    public void update(Tour tour, int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_TOUR);
            GregorianCalendar calendar = tour.getDate();
            Date date = new Date(calendar.getTimeInMillis());
            statement.setDate(1, date);
            boolean isHot = tour.getIsHot();
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
            statement.setString(5, tour.getCountry());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            connectionPool.releaseConnection(connection);
        }
    }

    public boolean isPaid(int id) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(IS_PAID);
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int quantity = resultSet.getInt(1);
                System.out.println(quantity);
                if (quantity >= 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return false;
    }

    /**
     * Create tour from database
     *
     * @param resultSet
     * @return
     * @throws DAOException
     */


    private Tour createEntity(ResultSet resultSet) throws DAOException {

        String idTourParam = "idTour";
        String dateParam = "Date";
        String isHotParam = "isHot";
        String costParam = "Cost";
        String discountParam = "Discount";
        String countryParam = "Country";
        String tourTypeNameParam = "TourType.Name";

        Tour tour = new Tour();

        try {
            tour.setId(resultSet.getInt(idTourParam));
            Date date = resultSet.getDate(dateParam);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            tour.setDate(calendar);
            Integer isHot = resultSet.getInt(isHotParam);
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
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return tour;
    }
}
