package com.shop.listerner;

import com.shop.bean.BeanFactory;
import com.shop.connection.ConnectionPool;
import com.shop.connection.ConnectionManagement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

    public static ConnectionManagement connectionManagement;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        Utils utils = new Utils();
//        utils.loadConfig();
        ConnectionPool connectionPool = new ConnectionPool();
        connectionManagement = new ConnectionManagement(connectionPool);
        BeanFactory beanFactory = new BeanFactory();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
