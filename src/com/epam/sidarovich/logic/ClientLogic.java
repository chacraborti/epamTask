package com.epam.sidarovich.logic;


import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.ConnectionPoolException;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;
import org.apache.log4j.Logger;

/**
 * Created by ilona on 12.04.15.
 */
public class ClientLogic {
    private static final Logger LOG = Logger.getLogger(ClientLogic.class);

 public  void makeRegular(String email) throws LogicException{
     ConnectionPool connectionPool = null;
     try {
         connectionPool = ConnectionPool.getConnectionPool();
     } catch (ConnectionPoolException e) {
         e.printStackTrace();
     }
     UserDAO userDAO = new UserDAO();
     User user = null;
     try {
         user = userDAO.findEntityByEmail(email);
         if(user.getIsAdmin()==false && user.getIsRegular()==false){
         }
         else{
             LOG.info("User + "+ user.getName() + " is not suitable for making regular");
         }
     } catch (DAOException e) {
         throw new LogicException();
     }
    }
}
