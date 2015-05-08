package com.epam.sidarovich.dao;

import com.epam.sidarovich.entity.Entity;
import com.epam.sidarovich.exception.ConnectionPoolException;
import com.epam.sidarovich.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<T extends Entity> {
    private static final Logger LOG = Logger.getLogger(AbstractDAO.class);
    protected Connection connection;

    public abstract List<T> findAll() throws DAOException;
    public abstract boolean delete(T entity) throws DAOException;
    public abstract boolean create(T entity) throws DAOException;
    public abstract int update(T entity) throws DAOException;
    public abstract T createEntity(ResultSet resultSet) throws DAOException;

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            LOG.error(e + " Can't close connection");
        }
    }
}