package com.epam.sidarovich.entity;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by ilona on 21.03.15.
 */
public class Tour extends Entity {
    private int id;
    private GregorianCalendar date;
    private boolean isHot;
    private TourType tourType;
    private int cost;
    private int discount;
    private String country;

    public Tour() {
    }
    public Tour(int id, GregorianCalendar date,  boolean isHot, TourType tourType, int cost, String country) {
        this.id=id;
        this.date = date;
        this.isHot = isHot;
        this.tourType = tourType;
        this.cost = cost;
        this.country = country;
    }
    public Tour(GregorianCalendar date,  boolean isHot, TourType tourType, int cost, String country) {
        this.date = date;
        this.isHot = isHot;
        this.tourType = tourType;
        this.cost = cost;
        this.country = country;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setHot(boolean isHot) {
        this.isHot = isHot;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }


    @Override
    public String toString() {
        return "Tour{" +
                "date=" + date +
                ", isHot=" + isHot +
                ", tourType=" + tourType +
                ", cost=" + cost +
                ", discount=" + discount +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;

        Tour tour = (Tour) o;

        if (cost != tour.cost) return false;
        if (discount != tour.discount) return false;
        if (id != tour.id) return false;
        if (isHot != tour.isHot) return false;
        if (!country.equals(tour.country)) return false;
        if (!date.equals(tour.date)) return false;
        if (tourType != tour.tourType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + date.hashCode();
        result = 31 * result + (isHot ? 1 : 0);
        result = 31 * result + tourType.hashCode();
        result = 31 * result + cost;
        result = 31 * result + discount;
        result = 31 * result + country.hashCode();
        return result;
    }
}
