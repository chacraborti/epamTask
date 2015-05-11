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

    public static User checkEmailPassword(String email, String password) throws LogicException{
            UserDAO dao = new UserDAO();
        User user;
            try {
               user = dao.findUserByEmailPassword(email, password);

            } catch (DAOException e) {
                throw new LogicException(e);
        }
        return user;
    }

}
