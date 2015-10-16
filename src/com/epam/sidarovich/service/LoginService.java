package com.epam.sidarovich.service;

import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.ServiceException;


/**
 * Created by ilona on 25.03.15.
 */
public class LoginService {

    /**
     * Check user email and password
     *
     * @param email
     * @param password
     * @return user
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public static User checkEmailPassword(String email, String password) throws ServiceException {
        UserDAO dao = new UserDAO();
        User user;
        try {
            user = dao.findUserByEmailPassword(email, password);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

}
