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

    private ConnectionPool() throws ConnectionPoolException{
        connections = new ArrayBlockingQueue<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = ConnectorDB.getConnection();
                if(connection!=null){
            connections.offer(connection);
                }
        }
    }

    public static ConnectionPool getConnectionPool() throws ConnectionPoolException{
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

    public Connection getConnection()  {
        Connection connection = null;
        try{
        connection = connections.take();
        }catch (InterruptedException e){
            LOG.error(e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
        connections.offer(connection);
            LOG.info("Connection " + connection +" released");
        }
 }
    public void shutDownConnections(){
        connections.stream().filter(connection -> connection != null).forEach(connections::remove);
    }

}