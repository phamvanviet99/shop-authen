package com.shop.connection;

import java.sql.Connection;

public class ConnectionManagement {
    private ConnectionPool connectionPool;

    public ConnectionManagement(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public Connection connection(){
        try {
            return connectionPool.getDataSource().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
