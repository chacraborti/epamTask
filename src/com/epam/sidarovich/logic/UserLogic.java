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
    public static final String EMAIL_PATTERN="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final String LETTER_UPPER_CASE="[A-Z]";
    public static final String LETTER_LOWER_CASE="[a-z]";
    public static final String NUMBER="\\d";
    public static final String SPECIAL_CHAR="\\p{Punct}";

    public void createUser(String email, String password, String name) throws LogicException{
        User user = new User(name, false, false, email, password);
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

    public boolean isValidEmailAddress(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    public boolean isValidPassword(String pass1, String pass2) {
        Pattern pattern1 = Pattern.compile(LETTER_UPPER_CASE);
        Pattern pattern2 = Pattern.compile(LETTER_LOWER_CASE);
        Pattern pattern3 = Pattern.compile(NUMBER);
        Pattern pattern4 = Pattern.compile(SPECIAL_CHAR);

            if (pass1 == null || pass2 == null) {
                return false;
            }

            if (pass1.isEmpty() || pass2.isEmpty()) {
                return false;
            }

            if (pass1.equals(pass2)) {

                if (pass1.length() < 8) {
                    return false;
                }
                if (!pattern1.matcher(pass1).find()) {
                    return false;
                }
                if (!pattern2.matcher(pass1).find()) {
                    return false;
                }
                if (!pattern3.matcher(pass1).find()) {
                    return false;
                }
                if (pattern4.matcher(pass1).find()) {
                    return false;
                }
            } else {
                return true;
            }
            if (pass1.length()!=pass2.length()){
                return false;
            }
            return true;
        }
    }

