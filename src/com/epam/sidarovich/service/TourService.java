package com.epam.sidarovich.service;

import com.epam.sidarovich.dao.TourDAO;
import com.epam.sidarovich.entity.Tour;
import com.epam.sidarovich.entity.TourType;
import com.epam.sidarovich.exception.DAOException;
import com.epam.sidarovich.exception.ServiceException;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ilona on 21.04.15.
 */
public class TourService {

    public List<Tour> viewAllTours() throws ServiceException {
        TourDAO tourDAO = new TourDAO();
        List<Tour> tours;
        try {
            tours = tourDAO.findAll();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tours;
    }

    /**
     * Create tour
     *
     * @param date
     * @param isHot
     * @param tourType
     * @param cost
     * @param country
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public void createTour(GregorianCalendar date, boolean isHot, TourType tourType, int cost, String country) throws ServiceException {
        Tour tour = new Tour(date, isHot, tourType, cost, country);
        TourDAO tourDAO = new TourDAO();
        try {
            tourDAO.create(tour);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Delete tour
     *
     * @param id
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public void deleteTour(int id) throws ServiceException {
        TourDAO tourDAO = new TourDAO();
        try {
            tourDAO.deleteTourById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void updateTour(Tour tour, int id) throws ServiceException {
        TourDAO tourDAO = new TourDAO();
        try {
            tourDAO.update(tour, id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Create tour by id
     *
     * @param id
     * @return
     * @throws com.epam.sidarovich.exception.ServiceException
     */
    public Tour createTourById(int id) throws ServiceException {
        TourDAO tourDAO = new TourDAO();
        Tour tour;
        try {
            tour = tourDAO.findTourById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tour;
    }

    public Tour findTourById(int id) throws ServiceException {
        TourDAO tourDAO = new TourDAO();
        Tour tour;
        try {
            tour = tourDAO.findTourById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tour;
    }

    public boolean isPaid(int id) throws ServiceException {
        TourDAO tourDAO = new TourDAO();
        boolean flag;
        try {
            flag = tourDAO.isPaid(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return flag;
    }
}
