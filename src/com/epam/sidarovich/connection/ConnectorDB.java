package com.epam.sidarovich.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {

    private static final Logger LOG = Logger.getLogger(ConnectorDB.class);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("No driver");
        }
    }


    /**
     * get connection with db
     *
     * @return Connection connection
     */
    public static Connection getConnection() {
        Connection connection;
        try {
            ResourceBundle resource = ResourceBundle.getBundle("resources.database");
            String url = resource.getString("db.url");
            String user = resource.getString("db.user");
            String pass = resource.getString("db.password");

            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("No connection");
        }
        return connection;
    }
}