package com.epam.sidarovich.dao;

import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.ConnectionPoolException;
import com.epam.sidarovich.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilona on 30.03.15.
 */
public class UserDAO extends AbstractDAO<User> {

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    private static final String SELECT_ALL_FROM_USERS="SELECT * FROM User";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM User WHERE User.Email = ?";
    private static final String DELETE_USER_BY_EMAIL = "DELETE  FROM User WHERE User.Email = ?";
    private static final String CREATE_USER = "INSERT INTO User ( Name, Role, isRegular, Email, Password) VALUES( ?, ?, ?, ?, md5(?))";
    private static final String UPDATE_USER = "UPDATE User SET Name = ?, Role = ?, isRegular = ?, Email = ?, Password = ?  WHERE id = ?";
    private static final String MAKE_REGULAR_USER = "UPDATE User SET isRegular = 1  WHERE Email = ?";
    private static final String FIND_USER_BY_EMAIL_ADN_PASSWORD = "SELECT User.Email, User.Password, User.Role, User.Name, User.Email, User.isRegular FROM User WHERE User.Email=? AND User.Password = md5(?)";


    @Override
    public List<User> findAll() throws DAOException{
        List<User> users = new ArrayList<>();
        ConnectionPool connectionPool=null;
        PreparedStatement statement = null;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
            Connection connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_FROM_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createEntity(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error("SQL exception (request or table failed): " + e);
        } catch (ConnectionPoolException e) {
            throw new DAOException();

        } finally {
                close(statement);
            connectionPool.releaseConnection(connection);
        }
        return users;
    }

    public User findEntityByEmail(String email) throws DAOException {
        User user = null;
            ConnectionPool connectionPool = null;
            try{
                connectionPool = ConnectionPool.getConnectionPool();
            } catch (ConnectionPoolException e){
                throw new DAOException(e);
            }
            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;
                try{
                    statement = connection.prepareStatement(FIND_USER_BY_EMAIL);
                    statement.setString(1, email);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        user = createEntity(resultSet);
                    }

                } catch (SQLException e){
                    throw new DAOException(e);
                } finally {
                            close(statement);
                    connectionPool.releaseConnection(connection);
                }
        return user;
    }


    public int delete(String email) throws DAOException{
        ConnectionPool connectionPool = null;
        try{
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        }
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

            try {
                statement = connection.prepareStatement(DELETE_USER_BY_EMAIL);
                statement.setString(1, email);
                if (statement.executeUpdate() > 0){
                    return statement.executeUpdate();
                } else{
                    throw new DAOException("Delete user failed");
                }

            } catch (SQLException e) {
                throw new DAOException(e);
            } finally {
                        close(statement);

                connectionPool.releaseConnection(connection);
            }

    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean create(User user) throws DAOException {
        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(CREATE_USER);

                statement.setString(1, user.getName());
                Boolean isAdmin = user.getIsAdmin();
                if (isAdmin){
                    statement.setInt(2, 1);
                }
                else {
                    statement.setInt(2, 0);
                }
                Boolean isRegular = user.getIsRegular();
                if (isRegular){
                    statement.setInt(3, 1);
                }
                else {
                    statement.setInt(3, 0);
                }
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPassword());

                if (statement.executeUpdate() > 0){
                    LOG.info(user.getName() + " has been created");
                    return true;
                } else{
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
    public int update(User user) throws DAOException {
            ConnectionPool connectionPool = null;
            try {
                connectionPool = ConnectionPool.getConnectionPool();
            } catch (ConnectionPoolException e) {
                throw new DAOException(e);
            }

            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;

                try {
                    statement = connection.prepareStatement(UPDATE_USER);

                    statement.setString(1, user.getName());
                    Boolean isAdmin = user.getIsAdmin();
                    if (isAdmin){
                    statement.setInt(2, 1);
                    }
                    else {
                        statement.setInt(2, 0);
                    }
                    Boolean isRegular = user.getIsRegular();
                    if (isRegular){
                        statement.setInt(3, 1);
                    }
                    else {
                        statement.setInt(3, 0);
                    }
                    statement.setString(4, user.getEmail());
                    statement.setString(5, user.getPassword());

                    if (statement.executeUpdate() > 0){
                        return statement.executeUpdate();
                    } else{
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
     public User createEntity(ResultSet resultSet) throws DAOException{
        User user = new User();
        try{
        user.setName(resultSet.getString("Name"));
            user.setEmail(resultSet.getString("Email"));
            user.setPassword(resultSet.getString("Password"));
            Integer isAdmin = resultSet.getInt("Role");
            switch (isAdmin){
                case(1):
                user.setIsAdmin(true);
                    break;
                case (0):
                    user.setIsAdmin(false);
                    break;
                default: throw new DAOException("Wrong 'isAdmin' format");
            }
            Integer isRegular = resultSet.getInt("isRegular");
            switch (isRegular){
                case(1):
                    user.setIsRegular(true);
                    break;
                case (0):
                    user.setIsRegular(false);
                    break;
                default: throw new DAOException("Wrong 'isRegular' format");

            }
        }catch (SQLException e){
            throw new DAOException(e);
        }
        return user;
    }

    public int updateAsRegular(String email) throws DAOException {
        ConnectionPool connectionPool ;
        try {
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(MAKE_REGULAR_USER);
            statement.setString(1, email);
            statement.executeUpdate();
            if (statement.executeUpdate() > 0){
                LOG.info("User with " + email  + " email has become regular");
                return statement.executeUpdate();
            } else{
                throw new DAOException("Make regular user failed");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
                close(statement);
            connectionPool.releaseConnection(connection);
        }
    }

    public User findUserByEmailPassword(String email, String password) throws DAOException{
        User user = null;
        ConnectionPool connectionPool = null;
        try{
            connectionPool = ConnectionPool.getConnectionPool();
        } catch (ConnectionPoolException e){
            throw new DAOException(e);
        }
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(FIND_USER_BY_EMAIL_ADN_PASSWORD);
            statement.setString(1,email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                user = createEntity(resultSet);
            }

        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
                close(statement);
            connectionPool.releaseConnection(connection);
        }
        return user;
    }
}