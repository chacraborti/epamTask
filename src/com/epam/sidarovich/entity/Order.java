package com.epam.sidarovich.entity;

/**
 * Created by ilona on 21.03.15.
 */
public class Order extends Entity {
    private int idTour;
    private Tour tour;
    private User user;
    private String emailUser;
    private OrderStatus orderStatus;

    public Order() {

    }

    public Order(Tour tour, User user, OrderStatus orderStatus) {
        this.tour = tour;
        this.user = user;
        this.orderStatus = orderStatus;
    }

    public Order(int idTour, String emailUser, OrderStatus orderStatus) {
        this.idTour = idTour;
        this.emailUser = emailUser;
        this.orderStatus = orderStatus;
    }

    public int getIdTour() {
        return idTour;
    }

    public void setIdTour(int idTour) {
        this.idTour = idTour;
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

    @Override
    public String toString() {
        return "Order{" +
                "idTour=" + idTour +
                ", emailUser='" + emailUser + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (idTour != order.idTour) return false;
        if (!emailUser.equals(order.emailUser)) return false;
        if (orderStatus != order.orderStatus) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTour;
        result = 31 * result + emailUser.hashCode();
        result = 31 * result + orderStatus.hashCode();
        return result;
    }
}
