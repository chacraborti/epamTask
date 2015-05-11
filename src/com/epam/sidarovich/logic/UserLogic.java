package com.epam.sidarovich.logic;

import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;

import java.util.List;


/**
 * Created by ilona on 21.04.15.
 */
public class UserLogic {

    /**
     * Create user
     * @param email
     * @param password
     * @param name
     * @throws LogicException
     */
    public void createUser(String email, String password, String name) throws LogicException{
        User user = new User(name, false, false, email, password,0);
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.create(user);
        } catch (DAOException e) {
            throw new LogicException();
        }
    }

    /**
     * View all Users
     * @return
     * @throws LogicException
     */
    public List<User> viewAllUsers() throws LogicException{
        UserDAO userDAO = new UserDAO();
        List<User> users;
        try {
            users = userDAO.findAll();

        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return users;
    }

    /**
     * Delete user
     * @param email
     * @throws LogicException
     */
    public void deleteUser(String email) throws LogicException{
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.deleteUserByEmail(email);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Make user regular
     * @param email
     * @throws LogicException
     */
    public void makeRegular(String email) throws LogicException{
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.updateAsRegular(email);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Update user discount
     * @param email
     * @param discount
     * @throws LogicException
     */
    public void updateUserDiscount(String email, int discount) throws LogicException{
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.updateUserDiscount(email,discount);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Find User by Email
     * @param email
     * @return
     * @throws LogicException
     */
    public User findUserByEmail(String email)throws LogicException{
        UserDAO userDAO = new UserDAO();
        User user;
        try {
             user = userDAO.findEntityByEmail(email);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return user;
    }

    }

