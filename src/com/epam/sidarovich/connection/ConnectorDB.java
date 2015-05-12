package com.epam.sidarovich.connection;

import com.epam.sidarovich.exception.ConnectionPoolException;
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

    static{
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("No driver");
        }
    }


    /**
     * get connection with db
     * @return Connection connection
     * @throws ConnectionPoolException
     */
    public static Connection getConnection(){
        Connection connection;
        try{
        ResourceBundle resource = ResourceBundle.getBundle("resources.database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");

            connection=DriverManager.getConnection(url, user, pass);
        }catch (SQLException e) {
            throw new ExceptionInInitializerError("No connection");
        }
        return connection;
    }
}
