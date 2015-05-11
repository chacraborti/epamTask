package com.epam.sidarovich.logic;

import com.epam.sidarovich.dao.TourDAO;
import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by ilona on 21.04.15.
 */
public class UserLogic {

    public void createUser(String email, String password, String name) throws LogicException{
        User user = new User(name, false, false, email, password,0);
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.create(user);
        } catch (DAOException e) {
            throw new LogicException();
        }
    }
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

    public void deleteUser(String email) throws LogicException{
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.delete(email);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public void makeRegular(String email) throws LogicException{
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.updateAsRegular(email);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public void updateUserDiscount(String email, int discount) throws LogicException{
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.updateUserDiscount(email,discount);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }
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

