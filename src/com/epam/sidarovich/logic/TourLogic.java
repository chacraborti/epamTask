package com.epam.sidarovich.logic;

import com.epam.sidarovich.dao.TourDAO;
import com.epam.sidarovich.dao.UserDAO;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
import com.epam.sidarovich.entity.User;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.LogicException;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ilona on 21.04.15.
 */
public class TourLogic {

public List<Tour> viewAllTours() throws LogicException{
    TourDAO tourDAO = new TourDAO();
    List<Tour> tours;
    try {
        tours = tourDAO.findAll();

    } catch (DAOException e) {
        throw new LogicException(e);
    }
   return tours;
}
    public void createTour(GregorianCalendar date, boolean isHot, TourType tourType, int cost, String country) throws LogicException{
       Tour tour = new Tour(date,isHot,tourType,cost,country);
        TourDAO tourDAO = new TourDAO();
        try {
            tourDAO.create(tour);
        } catch (DAOException e) {
            throw new LogicException();
        }
    }
    public void deleteTour(int id) throws LogicException{
        TourDAO tourDAO = new TourDAO();
        try {
            tourDAO.delete(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }
    public Tour createById(int id) throws LogicException{
        TourDAO tourDAO = new TourDAO();
        Tour tour = new Tour();
        try {
            tour=tourDAO.findEntityById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return tour;
    }
}
