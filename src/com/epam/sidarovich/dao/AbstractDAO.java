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

    /**
     * Find all entities
     * @return
     * @throws DAOException
     */
    public abstract List<T> findAll() throws DAOException;

    /**
     * Create entity to data base
     * @param entity
     * @return
     * @throws DAOException
     */
    public abstract boolean create(T entity) throws DAOException;

    /**
     * Update entity
     * @param entity
     * @return
     * @throws DAOException
     */
    public abstract int update(T entity) throws DAOException;

    /**
     * Create Entity from data base
     * @param resultSet
     * @return
     * @throws DAOException
     */
    protected abstract T createEntity(ResultSet resultSet) throws DAOException;

    /**
     * Close statement
     * @param st
     */
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