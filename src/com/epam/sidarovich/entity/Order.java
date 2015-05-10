package com.epam.sidarovich.entity;

/**
 * Created by ilona on 21.03.15.
 */
public class Order extends Entity {
    private int id;
    private Tour tour;
    private String emailUser;
    private OrderStatus orderStatus;

    public Order() {

    }

    public Order(Tour tour, String emailUser, OrderStatus orderStatus) {
        this.tour=tour;
        this.emailUser = emailUser;
        this.orderStatus = orderStatus;
    }

    public Order(int id, Tour tour, String emailUser, OrderStatus orderStatus) {
        this.id = id;
        this.tour = tour;
        this.emailUser = emailUser;
        this.orderStatus = orderStatus;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (!emailUser.equals(order.emailUser)) return false;
        if (orderStatus != order.orderStatus) return false;
        if (!tour.equals(order.tour)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tour.hashCode();
        result = 31 * result + emailUser.hashCode();
        result = 31 * result + orderStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "tour=" + tour +
                ", emailUser='" + emailUser + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
