package com.epam.sidarovich.entity;

/**
 * Created by ilona on 21.03.15.
 */
public class User extends Entity {
    private String name;
    private Boolean isAdmin;
    private Boolean isRegular;
    private String email;
    private String password;
    private int discount;


    public User(String name, Boolean isAdmin, Boolean isRegular, String email, String password, int discount) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.isRegular = isRegular;
        this.email = email;
        this.password = password;
        this.discount = discount;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsRegular() {
        return isRegular;
    }

    public void setIsRegular(Boolean isRegular) {
        this.isRegular = isRegular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", isAdmin=" + isAdmin +
                ", isRegular=" + isRegular +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", discount=" + discount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (discount != user.discount) return false;
        if (!email.equals(user.email)) return false;
        if (!isAdmin.equals(user.isAdmin)) return false;
        if (!isRegular.equals(user.isRegular)) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + isAdmin.hashCode();
        result = 31 * result + isRegular.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + discount;
        return result;
    }
}
