package com.epam.sidarovich.connection;

import com.epam.sidarovich.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

    private  BlockingQueue<Connection> connections;
    private final int POOL_SIZE = 10;
    private static Lock lock = new ReentrantLock(true);
    private static ConnectionPool connectionPool;
    private static AtomicBoolean flag = new AtomicBoolean(true) ;


    private ConnectionPool() {
        connections = new ArrayBlockingQueue<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = ConnectorDB.getConnection();
            connections.offer(connection);
        }
    }

    /**
     * Getting connection pool
     * @return
     */
    public static ConnectionPool getConnectionPool(){
       if (flag.get()){
        lock.lock();
        try{
        if (connectionPool==null){
            connectionPool = new ConnectionPool();
            flag.set(false);
        }
        }finally {

            lock.unlock();
        }
        }
        return connectionPool;

    }

    /**
     * Taking connection from connection pool
     * @return
     */
    public Connection getConnection()  {
        Connection connection = null;
        try{
        connection = connections.take();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            LOG.error("Tread is interrupted");
        }
        return connection;
    }

    /**
     * Release connection into connection pool
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
        connections.offer(connection);
            LOG.info("Connection " + connection +" released");
        }
 }

    /**
     * Remove all connections from connections queue
     */
    public void shutDownConnections(){
        connections.stream().filter(connection -> connection != null).forEach(connections::remove);
    }

}