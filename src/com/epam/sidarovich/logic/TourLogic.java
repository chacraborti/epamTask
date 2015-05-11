package com.epam.sidarovich.logic;

import com.epam.sidarovich.dao.TourDAO;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
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

    /**
     * Create tour
     * @param date
     * @param isHot
     * @param tourType
     * @param cost
     * @param country
     * @throws LogicException
     */
    public void createTour(GregorianCalendar date, boolean isHot, TourType tourType, int cost, String country) throws LogicException{
       Tour tour = new Tour(date,isHot,tourType,cost,country);
        TourDAO tourDAO = new TourDAO();
        try {
            tourDAO.create(tour);
        } catch (DAOException e) {
            throw new LogicException();
        }
    }

    /**
     * Delete tour
     * @param id
     * @throws LogicException
     */
    public void deleteTour(int id) throws LogicException{
        TourDAO tourDAO = new TourDAO();
        try {
            tourDAO.deleteTourById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    /**
     * Create tour by id
     * @param id
     * @return
     * @throws LogicException
     */
    public Tour createTourById(int id) throws LogicException{
        TourDAO tourDAO = new TourDAO();
        Tour tour;
        try {
            tour=tourDAO.findTourById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
        return tour;
    }
}
