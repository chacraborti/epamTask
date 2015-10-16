package com.epam.sidarovich.service;

import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.ServiceException;

import java.util.List;


/**
 * Created by ilona on 21.04.15.
 */
public class UserService {

    /**
     * Create user
     *
     * @param email
     * @param password
     * @param name
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public void createUser(String email, String password, String name) throws ServiceException {
        User user = new User(name, false, false, email, password, 0);
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.create(user);
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    /**
     * View all Users
     *
     * @return
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public List<User> viewAllUsers() throws ServiceException {
        UserDAO userDAO = new UserDAO();
        List<User> users;
        try {
            users = userDAO.findAll();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Delete user
     *
     * @param email
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public void deleteUser(String email) throws ServiceException {
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.deleteUserByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Make user regular
     *
     * @param email
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public void makeRegular(String email) throws ServiceException {
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.updateAsRegular(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update user discount
     *
     * @param email
     * @param discount
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public void updateUserDiscount(String email, int discount) throws ServiceException {
        UserDAO userDAO = new UserDAO();

        try {
            if (discount > 0) {
                userDAO.updateUserDiscount(email, discount);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    /**
     * Find User by Email
     *
     * @param email
     * @return
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public User findUserByEmail(String email) throws ServiceException {
        UserDAO userDAO = new UserDAO();
        User user;
        try {
            user = userDAO.findEntityByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

}

