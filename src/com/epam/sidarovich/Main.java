package com.epam.sidarovich;

import com.epam.sidarovich.command.command.ObserveToursCommand;
import com.epam.sidarovich.connection.ConnectionPool;
import com.epam.sidarovich.dao.OrderDAO;
import com.epam.sidarovich.dao.TourDAO;
import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.*;
import com.epam.sidarovich.exception.ConnectionPoolException;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;
import com.epam.sidarovich.logic.ClientLogic;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.GregorianCalendar;
import java.util.List;

/**
* Created by ilona on 07.04.15.
*/
public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class);
    static{
        new DOMConfigurator().configure("data/log4j.xml");
    }
    public static void main(String[] args) throws DAOException {
        UserDAO userDAO = new UserDAO();

        TourDAO tourDAO = new TourDAO();
        List<Tour> tours = null;
        try {
            tours = tourDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
//            User user1 = new User("Инна Макарова", false, false, "Inna9", "5432");
//           userDAO.create(user1);
//           userDAO.updateAsRegular(6);
//        try {
//            for(User user: userDAO.findAll()){
//                System.out.println(user);
//            }
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        GregorianCalendar calendar = new GregorianCalendar(2015,06,15);
//        Tour tour = new Tour(calendar, true, TourType.REST, 5000, 0, "Россия" );
//        try {
//            tourDAO.create(tour);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        try {
//            userDAO.findUserByEmailPassword("Solnus","2345");
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//            for(Tour tour: tours){
//                System.out.println(tour);
//            }
        OrderDAO orderDAO = new OrderDAO();
        Order order = new Order(1,"ghh", OrderStatus.ACTIVE);
        orderDAO.create(order);

        //tourDAO.findAll();

   }
}
