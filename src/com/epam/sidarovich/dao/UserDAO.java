package com.epam.sidarovich.dao;

import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilona on 30.03.15.
 */
public class UserDAO extends AbstractDAO<User> {

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    private static final String SELECT_ALL_FROM_USERS="SELECT * FROM User";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM User WHERE User.Email = ?";
    private static final String DELETE_USER_BY_EMAIL = "DELETE  FROM User WHERE User.Email = ?";
    private static final String CREATE_USER = "INSERT INTO User ( Name, Role, isRegular, Email, Password, userDiscount) VALUES( ?, ?, ?, ?, md5(?), ?)";
    private static final String UPDATE_USER = "UPDATE User SET Name = ?, Role = ?, isRegular = ?, Password = ? userDiscount = ? WHERE Email = ?";
    private static final String MAKE_REGULAR_USER = "UPDATE User SET isRegular = 1  WHERE Email = ?";
    private static final String FIND_USER_BY_EMAIL_ADN_PASSWORD = "SELECT User.Email, User.Password, User.Role, User.Name, User.userDiscount, User.isRegular FROM User WHERE User.Email=? AND User.Password = md5(?)";
    private static final String UPDATE_USER_DISCOUNT=  "UPDATE User SET userDiscount=? WHERE Email = ?";

    /**
     * Find all from user
     * @return
     * @throws DAOException
     */
    @Override
    public List<User> findAll() throws DAOException{
        List<User> users = new LinkedList<>();
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
        }
        finally {
                close(statement);
            connectionPool.releaseConnection(connection);
        }
        return users;
    }

    /**
     * Find user by email
     * @param email
     * @return
     * @throws DAOException
     */
    public User findEntityByEmail(String email) throws DAOException {
        User user = null;
            ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
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


    /**
     * Delete user by email
     * @param email
     * @return
     * @throws DAOException
     */
    public int deleteUserByEmail(String email) throws DAOException{
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

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


    /**
     * Create user to database
     * @param user
     * @return
     * @throws DAOException
     */
    @Override
    public boolean create(User user) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();


        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;
            try {
                statement = connection.prepareStatement(CREATE_USER);

                statement.setString(1, user.getName());
                boolean isAdmin = user.getIsAdmin();
                if (isAdmin){
                    statement.setInt(2, 1);
                }
                else {
                    statement.setInt(2, 0);
                }
                boolean isRegular = user.getIsRegular();
                if (isRegular){
                    statement.setInt(3, 1);
                }
                else {
                    statement.setInt(3, 0);
                }
                statement.setString(4, user.getEmail());
                statement.setString(5, user.getPassword());
                statement.setInt(6,user.getDiscount());

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

    /**
     * Update user
     * @param user
     * @return
     * @throws DAOException
     */
    @Override
    public int update(User user) throws DAOException {
            ConnectionPool connectionPool = ConnectionPool.getConnectionPool();


            Connection connection = connectionPool.getConnection();
            PreparedStatement statement = null;

                try {
                    statement = connection.prepareStatement(UPDATE_USER);

                    statement.setString(1, user.getName());
                    boolean isAdmin = user.getIsAdmin();
                    if (isAdmin){
                    statement.setInt(2, 1);
                    }
                    else {
                        statement.setInt(2, 0);
                    }
                    boolean isRegular = user.getIsRegular();
                    if (isRegular){
                        statement.setInt(3, 1);
                    }
                    else {
                        statement.setInt(3, 0);
                    }
                    statement.setString(4, user.getPassword());
                    statement.setInt(5, user.getDiscount());
                    statement.setString(6, user.getEmail());

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

    /**
     * Create user from database
     * @param resultSet
     * @return
     * @throws DAOException
     */
    @Override
     protected User createEntity(ResultSet resultSet) throws DAOException{
        User user = new User();
        try{
        user.setName(resultSet.getString("Name"));
            user.setEmail(resultSet.getString("Email"));
            user.setPassword(resultSet.getString("Password"));
            user.setDiscount(resultSet.getInt("userDiscount"));
            int isAdmin = resultSet.getInt("Role");
            switch (isAdmin){
                case(1):
                user.setIsAdmin(true);
                    break;
                case (0):
                    user.setIsAdmin(false);
                    break;
                default: throw new DAOException("Wrong 'isAdmin' format");
            }
            int isRegular = resultSet.getInt("isRegular");
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

    /**
     * Update user as regular
     * @param email
     * @return
     * @throws DAOException
     */
    public int updateAsRegular(String email) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
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

    /**
     * Update user discount
     * @param email
     * @param discount
     * @return
     * @throws DAOException
     */
    public int updateUserDiscount (String email, int discount) throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

        Connection connection = connectionPool.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(UPDATE_USER_DISCOUNT);
            statement.setInt(1, discount);
            statement.setString(2, email);
            statement.executeUpdate();
            if (statement.executeUpdate() > 0){
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

    /**
     * Find user by email and password
     * @param email
     * @param password
     * @return
     * @throws DAOException
     */
    public User findUserByEmailPassword(String email, String password) throws DAOException{
        User user = null;
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

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
