package com.epam.sidarovich.logic;

import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;


/**
 * Created by ilona on 25.03.15.
 *
 */
public class LoginLogic {

    public static User checkEmailPassword(String Email, String password) throws LogicException{
            UserDAO dao = new UserDAO();
            try {
                User user = dao.findUserByEmailPassword(Email, password);
                return user;
            } catch (DAOException e) {
                throw new LogicException(e);
        }
    }

}
