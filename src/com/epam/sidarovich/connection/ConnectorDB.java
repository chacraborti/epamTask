package com.epam.sidarovich.connection;

import com.epam.sidarovich.exception.ConnectionPoolException;
import com.epam.sidarovich.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by ilona on 28.03.15.
 */
public class ConnectorDB {
    private static final Logger LOG = Logger.getLogger(ConnectorDB.class);

    public static Connection getConnection() throws ConnectionPoolException{
        Connection connection = null;
        try{
        ResourceBundle resource = ResourceBundle.getBundle("resources.database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection=DriverManager.getConnection(url, user, pass);
        }catch (SQLException e) {
            throw new ExceptionInInitializerError("No connection");
        }
        return connection;
    }
}
