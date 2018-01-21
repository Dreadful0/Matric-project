package com.demianenko.application.app;

import com.demianenko.application.controller.services.implementations.ServiceFactory;
import com.demianenko.application.controller.util.SecurityConstraints;
import com.demianenko.application.controller.util.converters.Sha256Encoder;
import com.demianenko.application.model.dao.connection.MySQLConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.factoryImp.MySqlDaoFactory;
import com.demianenko.application.model.entities.Role;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    private final static Logger LOGGER = Logger.getLogger(AppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.debug("App initializing");
        appInit();
        LOGGER.debug("App initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.debug("App destroyed");
    }

    private void appInit(){
        MySQLConnectionFactory.initFromProperties("db");
        MySqlDaoFactory.init(MySQLConnectionFactory.getInstance());
        ServiceFactory.init(MySqlDaoFactory.getInstance(), new Sha256Encoder());
        initSecurityConstraints();
    }

    private void initSecurityConstraints(){
        SecurityConstraints constraints = SecurityConstraints.getInstance();
        constraints.addConstraint("PERSONAL_INFO", Role.USER,Role.ADMIN);
        constraints.addConstraint("LOGOUT", Role.USER,Role.ADMIN);
        constraints.addConstraint("ADMIN_PAGE_INFO", Role.ADMIN);
        constraints.addConstraint("ADD_COURSE",Role.ADMIN);
    }
}
